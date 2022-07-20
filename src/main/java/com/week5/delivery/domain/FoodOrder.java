package com.week5.delivery.domain;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter     //get함수를 일괄적으로 만들어줌
@NoArgsConstructor      //기본 생성자를 자동으로 만들어줌
@AllArgsConstructor
@Entity     //음식 주문 테이블 지정
public class FoodOrder {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;        //주문 음식명

    @Column(nullable = false)
    private int quantity;       //주문 수량

    @Column(nullable = false)
    private int price;          //주문 음식의 가격

    @ManyToOne(cascade = CascadeType.ALL)
    private Food food;

    @ManyToOne
    private Orders orders;
}
