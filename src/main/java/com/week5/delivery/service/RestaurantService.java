package com.week5.delivery.service;

import com.week5.delivery.domain.Restaurant;
import com.week5.delivery.dto.RestaurantRequestDto;
import com.week5.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.week5.delivery.exception.ExceptionMessages.*;

//서비스는 repo와 contoller 를 연결해주는 계층.
//html 에서 받은 데이터 -> controller ->
@Service
@RequiredArgsConstructor        //final로 선언된것이 생성될때 같이 넣어줌
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    //addRestaurant 메소드 생성
    //음식점 추가하는 메소드
    @Transactional
    public Restaurant addRestaurant(RestaurantRequestDto requestDto) {
        //2.최소 주문비와 배달비를 가져와서
        int minOrderPrice = requestDto.getMinOrderPrice();
        int deliveryFee = requestDto.getDeliveryFee();
        //검증메소드 만들기 (조건이 있으니까)
        //최소 주문가격 검증 메소드
        checkMinOrderPrice(minOrderPrice);
        //기본 배달가격 검증 메소드
        checkDeliveryFee(deliveryFee);

        //1.생성자 대신 builder사용      각각 받은 내용을 가져와서 값을 넣고
        Restaurant restaurant = Restaurant.builder()
                .name(requestDto.getName())
                .minOrderPrice(requestDto.getMinOrderPrice())
                .deliveryFee(requestDto.getDeliveryFee())
                .build();
        //레포지토리에 저장
        restaurantRepository.save(restaurant);
        //restaurant 리턴
        return restaurant;
    }

    //최소 주문가격 검증하기
    private void checkMinOrderPrice(int minOrderPrice) {     //설정한 최소 주문금액을 재료로 검증
        if (!(1000 <= minOrderPrice && minOrderPrice <= 100000)) {   //min이 1000~100000 사이에 있어야함
            throw new IllegalArgumentException(ILLEGAL_MIN_ORDER_PRICE_RANGE);  //주문금액 범위 밖에 있을때 오류
        }
        if (minOrderPrice % 100 > 0) {                                  //최소 주문금액을 100으로 나누었을때 나머지가 0보다 커야함. 예) 1250 % 100 = 50 이므로 X 1300% 100 = 0 이므로 O
            throw new IllegalArgumentException(ILLEGAL_MIN_ORDER_PRICE_UNIT);   //100으로 안나누어지면 오류
        }
    }

    private void checkDeliveryFee(int deliveryFee) {         //설정한 기본 배달비를 재료로 검증
        if (!(0 <= deliveryFee && deliveryFee <= 10000)) {        //delivery 의 허용값은 0~10000
            throw new IllegalArgumentException(ILLEGAL_DELIVERY_FEE_RANGE);
        }
        if (deliveryFee % 500 > 0) {     //500 단위로 입력하게끔
            throw new IllegalArgumentException(ILLEGAL_DELIVERY_FEE_UNIT);
        }
    }

    //findAllRestaurant 메소드 생성
    //모든 음식점 조회하는 메소드
    @Transactional
    public List<Restaurant> findAllRestaurant(){
        return restaurantRepository.findAll();
    }
}