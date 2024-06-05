package com.example.mymarket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ItemDto {
    private String name;
    private int price;
    private String description;
    private String submittionTime;
    private String photo;
}
