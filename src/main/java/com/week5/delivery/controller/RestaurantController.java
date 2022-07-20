package com.week5.delivery.controller;

import com.week5.delivery.domain.Restaurant;
import com.week5.delivery.domain.UserRoleEnum;
import com.week5.delivery.dto.RestaurantRequestDto;
import com.week5.delivery.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class RestaurantController {

    //Service 가 꼭 들어가야 하지요.
    private final RestaurantService restaurantService;

    //음식점 추가하기
    @Secured(UserRoleEnum.Authority.OWNER)
    @PostMapping("/restaurant/register")
    // public 반환타입 메소드명 : addRestaurant ( 재료 : 사용자가 보낸 정보 dto) {
    // 명령 모음
    // return 결과값; }
    public Restaurant addRestaurant(@RequestBody RestaurantRequestDto requestDto){
        return restaurantService.addRestaurant(requestDto);
    }

    //모든 음식점 조회
    @GetMapping("/restaurants")
    public List<Restaurant> findAllRestaurant(){
        return restaurantService.findAllRestaurant();
    }
}
