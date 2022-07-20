package com.week5.delivery.domain;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter     //get함수를 일괄적으로 만들어줌
@NoArgsConstructor      //기본 생성자를 자동으로 만들어줌
@AllArgsConstructor     //모든 필드 값을 파라미터로 받는 생성자를 만듦
@Entity    //음식 테이블 지정
public class Food {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;        //음식의 이름

    @Column(nullable = false)
    private int price;      //음식의 가격

    @ManyToOne      //한 음식점(One)에 여러 음식(Many) 들어가게 연관관계설정
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}
