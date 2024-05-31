package com.example.mymarket.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDto {
    private String name;
    private int price;
    private String description;
    private String submittionTime;
    private String photo;
}
