package com.example.mymarket.service;

import com.example.mymarket.dto.ItemDto;
import com.example.mymarket.dto.UserDto;
import com.example.mymarket.dto.UserDtoCollection;
import com.example.mymarket.model.User;
import com.example.mymarket.repository.UserRepository;
import com.example.mymarket.util.DtoConverter;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.persistence.EntityNotFoundException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final DtoConverter converter;
    @Autowired
    public UserService(UserRepository userRepository, DtoConverter converter) {
        this.userRepository = userRepository;
        this.converter = converter;
    }
    public UserDtoCollection getAllUsers(){
        val users=userRepository.findAll();
        return converter.userconvert(users);
    }
    public UserDto getUser(String name){
        val user=userRepository.findByUsername(name).orElseThrow(() -> new EntityNotFoundException("User was not found"));
        return converter.userconvert(user);
    }
    public UserDto getEmail(String email){
        val user=userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User was not found"));
        return converter.userconvert(user);
    }
    public UserDto addUser(UserDto userDto){
        userRepository.save(converter.userconvert(userDto));
        return userDto;
    }
    public boolean checkUser(String email, String password){
        boolean answer=false;
        val user=userRepository.findByEmailAndPassword(email,password);
        if(user!=null){
            answer=true;
        }
        return answer;
    }
    public boolean checkEmail(String email){
        try {
            new InternetAddress(email).validate();
            return true;
        } catch (AddressException ex) {
            return false;
        }
    }
}
