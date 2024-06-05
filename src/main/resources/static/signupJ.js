
async function showLoginForm() {
    document.getElementById('signupForm').classList.add('hidden');
    document.getElementById('loginForm').classList.remove('hidden');
}

async function showSignupForm() {
    document.getElementById('loginForm').classList.add('hidden');
    document.getElementById('signupForm').classList.remove('hidden');
}

async function submitSignupForm() {
    const username = document.getElementById('signupUsername').value;
    const email = document.getElementById('signupEmail').value;
    const password = document.getElementById('signupPassword').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    const birthday = document.getElementById('signupBirthday').value;
    const errorElement = document.getElementById('signupError');
    const usernameRegex = /^[a-zA-Z]{2,50}$/;
    var body=false;
    if(email!==""){
        console.log(email);
        var response = await fetch(`/user/email?email=${email}`, {method: 'PUT'});
        body = await response.json();
        console.log(body+"esari 23 xazis")
    }
    if (!usernameRegex.test(username)) {
        errorElement.classList.remove('hidden');
        errorElement.innerText = "Username is not valid"
    }else if(!body){
        errorElement.classList.remove('hidden');
        errorElement.innerText = "email is not valid!"
    }else if(password==="" && confirmPassword===""){
        errorElement.classList.remove('hidden');
        errorElement.innerText = "password cant be empty"
    } else if (password !== confirmPassword) {
        errorElement.classList.remove('hidden');
        errorElement.innerText = "confirm password incorrect!"
    }else if(birthday===""){
        errorElement.classList.remove('hidden');
        errorElement.innerText = "birthday cant be empty!"
    }else{
        console.log("43 modis");
        errorElement.classList.add('hidden');
        var response2 = await fetch(`/user?username=${username}&email=${email}&password=${password}&instantbirthday=${birthday}`, {method: 'POST'});
        var response3=await fetch(`/user?email=${email}`, {method: 'GET'});
        const body2=await response3.json();
        localStorage.setItem("username",body2.username);
        window.open("index.html");
    }
}
async function submitLoginForm() {
    const errorElement = document.getElementById('loginError');
    const email = document.getElementById('loginEmail').value;
    const password = document.getElementById('loginPassword').value;
    if(email!=="" && password!==""){
        var response=await fetch(`/user/checkuser
            ?email=${email}&
            ?password=${password}`, {method: 'PUT'});
        const body=await response.json();
        if(body){
            var response=await fetch(`/user?email=${email}`, {method: 'GET'});
            const body2=await response.json();
            localStorage.setItem("username",body2.username);
            window.open("index.html");
        }
        errorElement.classList.add('hidden');
    }else{
        errorElement.classList.remove('hidden');
    }
}