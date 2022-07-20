package com.week5.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FoodRequestDto {
    private String name;        //음식의 이름

    private int price;      //음식의 가격
}
