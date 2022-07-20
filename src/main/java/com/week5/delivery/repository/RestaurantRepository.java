package com.week5.delivery.repository;

import com.week5.delivery.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//레포지토리가 Jpa repo를 가져다가 쓸것이다. <A,B> A라는 클래스에 쓸것이고 id는 B(타입)이다.
//find 관련 함수를 설정
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Restaurant findByName(String name);
}
//repository 설정 끝나면 다음으로 -> RequestDto
