package com.week5.delivery.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Builder
@Getter     //get함수를 일괄적으로 만들어줌
@NoArgsConstructor      //기본 생성자를 자동으로 만들어줌
@AllArgsConstructor
@Entity     //주문서 테이블 지정
public class Orders {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String restaurantName;      //주문할 음식점의 이름

    @Column(nullable = false)
    private int totalPrice;         //주문할 총 금액

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "orders_id")
    private List<FoodOrder> foods;
}
