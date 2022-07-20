package com.week5.delivery.dto;

import com.week5.delivery.domain.Orders;
import lombok.Getter;

import java.util.List;

@Getter
public class OrdersResponseDto {
    private final String restaurantName; //주문할 음식점 이름
    private final List<FoodOrderResponseDto> foods;    //주문할 음식 리스트
    private final int deliveryFee;        //기본 배달비
    private final int totalPrice;         //총금액

    public OrdersResponseDto(Orders orders, int deliveryFee, List<FoodOrderResponseDto> foods){
        this.restaurantName = orders.getRestaurantName();
        this.foods = foods;
        this.deliveryFee = deliveryFee;
        this.totalPrice = orders.getTotalPrice();
    }
}
