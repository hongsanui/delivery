package com.week5.delivery.controller;

import com.week5.delivery.domain.Food;
import com.week5.delivery.domain.UserRoleEnum;
import com.week5.delivery.dto.FoodRequestDto;
import com.week5.delivery.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    //음식점에 메뉴 추가하기
    @Secured(UserRoleEnum.Authority.OWNER)
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void addRestaurantFood(
//          //{}형식으로 된 파라미터를 받기위해 Path 사용
            @PathVariable Long restaurantId,
            //form태그로 데이터 전달
            @RequestBody List<FoodRequestDto> requestDtoList
            ){
        //서비스의 add~~ 메소드를 키와 값을 넣어서 실행
        foodService.addRestaurantFoods(restaurantId,requestDtoList);
    }

    //음식점의 메뉴 조회하기
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> findAllRestaurantFoods(
            @PathVariable Long restaurantId
    ){
        return foodService.findAllRestaurantFoods(restaurantId);
    }

    @GetMapping("/restaurant/foods/deleteAll")
    public void deleteAll() {
        foodService.deleteAll();
    }
}
