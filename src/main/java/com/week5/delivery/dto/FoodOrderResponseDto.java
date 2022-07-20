package com.week5.delivery.dto;

import com.week5.delivery.domain.FoodOrder;
import lombok.Getter;

@Getter
public class FoodOrderResponseDto {
    private final String name;        //주문 음식명
    private final int quantity;       //주문 수량
    private final int price;          //주문 음식의 가격

    public FoodOrderResponseDto(FoodOrder foodOrder){
        this.name= foodOrder.getName();
        this.quantity = foodOrder.getQuantity();
        this.price = foodOrder.getPrice();
    }
}
