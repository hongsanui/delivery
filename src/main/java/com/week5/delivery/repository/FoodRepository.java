package com.week5.delivery.repository;


import com.week5.delivery.domain.Food;
import com.week5.delivery.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//자바와db를 연결하는 계층
public interface FoodRepository extends JpaRepository<Food,Long> {
    List<Food> findFoodsByRestaurant(Restaurant restaurant);
    Optional<Food> findFoodByRestaurantAndName(Restaurant restaurant, String foodName);
}
