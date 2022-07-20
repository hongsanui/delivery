package com.week5.delivery.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Builder
@Getter     //get함수를 일괄적으로 만들어줌
@NoArgsConstructor      //기본 생성자를 자동으로 만들어줌
@AllArgsConstructor
@Entity     //음식점 테이블
public class Restaurant {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;        //음식점 이름

    @Column(nullable = false)
    private int minOrderPrice;      //음식점의 최소 주문가격

    @Column(nullable = false)
    private int deliveryFee;        //음식점의 기본 배달비
}
//끝나면 Repository로
