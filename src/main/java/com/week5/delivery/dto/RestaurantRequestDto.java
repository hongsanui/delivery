package com.week5.delivery.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;


//dto = 값 받아서 던지기

@Getter
public class RestaurantRequestDto {
    private String name;        //음식점 이름

    private int minOrderPrice;      //음식점의 최소 주문가격

    private int deliveryFee;        //음식점의 기본 배달비

//    ******Service로
}
