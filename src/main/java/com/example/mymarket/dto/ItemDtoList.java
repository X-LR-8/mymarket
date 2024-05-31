package com.example.mymarket.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ItemDtoList {
    private List<ItemDto> itemDtoList;
    private Integer quantity;
}
