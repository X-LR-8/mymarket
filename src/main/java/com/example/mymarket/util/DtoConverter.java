package com.example.mymarket.util;

import com.example.mymarket.dto.ItemDto;
import com.example.mymarket.dto.ItemDtoList;
import com.example.mymarket.model.Item;
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
}
