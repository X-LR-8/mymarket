package com.example.mymarket.util;

import com.example.mymarket.dto.ItemDto;
import com.example.mymarket.dto.ItemDtoList;
import com.example.mymarket.dto.UserDto;
import com.example.mymarket.dto.UserDtoCollection;
import com.example.mymarket.model.Item;
import com.example.mymarket.model.User;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DtoConverter {
    public ItemDto convert(Item item){
        return ItemDto.builder()
                .name(item.getName())
                .price(item.getPrice())
                .description(item.getDescription())
                .submittionTime(item.getSubmittionTime())
                .photo(item.getPhoto())
                .build();
    }
    public Item convert(ItemDto itemDto){
        return Item.builder()
                .name(itemDto.getName())
                .price(itemDto.getPrice())
                .description(itemDto.getDescription())
                .submittionTime(itemDto.getSubmittionTime())
                .photo(itemDto.getPhoto())
                .build();
    }
    public ItemDtoList convert(List<Item> itemList){
        return ItemDtoList.builder()
                .itemDtoList(itemList.stream().map(this::convert).toList())
                .quantity(itemList.size())
                .build();
    }
    public UserDto userconvert(User user){
        return UserDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .instantbirthday(user.getInstantbirthday())
                .build();
    }
    public User userconvert(UserDto userDto){
        return User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .instantbirthday(userDto.getInstantbirthday())
                .build();
    }
    public UserDtoCollection userconvert(List<User> users) {
        return UserDtoCollection.builder()
                .userDtoList(users.stream().map(this::userconvert).toList())
                .build();
    }
}
