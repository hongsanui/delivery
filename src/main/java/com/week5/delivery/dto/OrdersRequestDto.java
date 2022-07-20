package com.week5.delivery.dto;

import com.week5.delivery.domain.FoodOrder;
import lombok.Getter;

import java.util.List;

@Getter
public class OrdersRequestDto {
    private Long restaurantId;      //주문할 음식점의 아이디값

    private List<FoodOrder> foods;  //주문할 음식 리스트
}
