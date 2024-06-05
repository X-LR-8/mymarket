async function handleSortChange() {
    const selectedOption = document.getElementById('sortOptions').selectedOptions[0].value;
    return selectedOption;
}

var page = 1;
// window.onload = async function () {
//     var quantity=document.getElementById('total items');
//     document.getElementById('backbutton').classList.add('hidebutt');
//     const option=await handleSortChange() ;
//     var url=`/market/${page}/${option}`;
//     console.log(option);
//     var response = await fetch(url, {method: "GET"});
//     const body = await response.json();
//     console.log(body);
//     var url2=`http://localhost:8080/market/total`;
//     var response2=await fetch(url2,{method: "GET"})
//     const body2=await response2.json();
//     quantity.innerText="total items: "+body2;
//     fillGridWithItems(body.itemDtoList);
//     console.log(page);
// }

async function loadContent() {
    var quantity = document.getElementById('total items');
    document.getElementById('backbutton').classList.add('hidebutt');
    const option = await handleSortChange();
    var url = `/market/${page}/${option}`;
    console.log(option);
    var response = await fetch(url, { method: "GET" });
    const body = await response.json();
    var url2 = `http://localhost:8080/market/total`;
    var response2 = await fetch(url2, { method: "GET" })
    const body2 = await response2.json();
    quantity.innerText = "total items: " + body2;
    fillGridWithItems(body.itemDtoList);
}
async function deleteusername(){
    localStorage.removeItem('username');
    window.location.reload();
    console.log("clicked");
}

window.onload = async function () {
    var labeltext=localStorage.getItem('username');
    console.log(labeltext);
    const logout=document.getElementById('logout');
    const button = document.getElementById('newbutton');
    const label=document.getElementById('username');
    if(labeltext===null){
        logout.classList.add('hidlabel');
        label.classList.add('hidlabel');
        button.textContent = 'Login';
        button.addEventListener('click', function() {
            window.open('signup.html');

        });
    }else{
        logout.classList.remove('hidlabel');
        label.classList.remove('hidlabel');
        label.innerText=labeltext;
        button.textContent = 'new';
        button.addEventListener('click', function() {
            window.open('new-item.html');
        });
    }
    await loadContent();
    document.getElementById('sortOptions').addEventListener('change', async function () {
        await loadContent();
    });
}

async function nextpage() {
    page++;
    const option = await handleSortChange();
    var url = `/market/${page}/${option}`;
    var response = await fetch(url, {method: "GET"});
    const body = await response.json();
    if (body.quantity!=0) {
        console.log("modis");
        document.getElementById('pagenumber').innerText = page;
        document.getElementById('backbutton').classList.remove('hidebutt');
        fillGridWithItems(body.itemDtoList);
    }else{
        page--;
    }
    console.log(page+" page number 63 xazi");
    document.getElementById('sortOptions').addEventListener('change', async function () {
        await loadContent();
    });
}

async function backpage() {
    page--;
    if (page == 1) {
        document.getElementById('backbutton').classList.add('hidebutt');
    }
    document.getElementById('pagenumber').innerText = page;
    const option = await handleSortChange();
    var url = `/market/${page}/${option}`;
    var response = await fetch(url, {method: "GET"});
    const body = await response.json();
    console.log(body)
    fillGridWithItems(body.itemDtoList);
    console.log(page+" page number 78 xazi");
    document.getElementById('sortOptions').addEventListener('change', async function () {
        await loadContent();
    });
}

document.addEventListener('DOMContentLoaded', (event) => {
    const gridItems = document.querySelectorAll('.grid-item');

    gridItems.forEach(item => {
        item.addEventListener('click', () => {
            const label = item.querySelector('label');
            handleClick(label.textContent);
        });
    });
});
async function clearGrid(){

}
async function fillGridWithItems(itemList) {
    const gridItems = document.querySelectorAll('.grid-item');
    for (let i = 0; i < 6; i++) {
        const gridItem = gridItems[i];
        if(itemList.length===0){
            gridItem.style.display = 'none';
        }else{
            const item = itemList[i];
            if (item) {
                if (gridItem.style.display === 'none') {
                    gridItem.style.display='block';
                }
                console.log(item+"this is fillgridwithitems")
                const img = gridItem.querySelector('img');

                img.src =item.photo+".jpg";

                const nameLabel = gridItem.querySelector('label:not(.forprice)');
                nameLabel.textContent = item.name;

                const priceLabel = gridItem.querySelector('.forprice');
                priceLabel.textContent = item.price+"$";

            } else {
                gridItem.style.display = 'none';
            }
        }
    }
}

function handleClick(labeltext) {
    window.open("item.html");
    localStorage.setItem('labeltext', labeltext);
}