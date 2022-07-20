package com.week5.delivery.service;


import com.week5.delivery.domain.Food;
import com.week5.delivery.domain.Restaurant;
import com.week5.delivery.dto.FoodRequestDto;
import com.week5.delivery.repository.FoodRepository;
import com.week5.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.week5.delivery.exception.ExceptionMessages.*;

//서비스는 repo와 contoller 를 연결해주는 계층.
//html 에서 받은 데이터 -> controller ->
@Service
@RequiredArgsConstructor

public class FoodService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public void addRestaurantFoods(
            Long restaurantId,
            List<FoodRequestDto> requestDtoList
    ){
        Optional<Restaurant> foundRestaurant = restaurantRepository.findById(restaurantId);

        checkRestaurant(foundRestaurant);
        Restaurant restaurant = foundRestaurant.get();

        for (FoodRequestDto requestDto : requestDtoList){
            String foodName = requestDto.getName();
            int foodPrice = requestDto.getPrice();

            checkDuplicateRestaurantFood(restaurant,foodName);

            checkFoodPrice(foodPrice);

            Food food = Food.builder()
                    .name(foodName)
                    .price(foodPrice)
                    .restaurant(restaurant)
                    .build();

            foodRepository.save(food);
        }
    }

    private void checkRestaurant(Optional<Restaurant> foundRestaurant){
        if (!foundRestaurant.isPresent())
            throw new IllegalArgumentException(RESTAURANT_IS_NULL);
    }

    private void checkDuplicateRestaurantFood(Restaurant restaurant, String foodName){
        Optional<Food> found = foodRepository.findFoodByRestaurantAndName(restaurant,foodName);
        if (found.isPresent())
            throw new IllegalArgumentException(RESTAURANT_FOOD_DUPLICATE);
    }
    private void checkFoodPrice(int foodPrice){
        if (foodPrice <100)
            throw new IllegalArgumentException(FOOD_PRICES_TOO_LOW);
        if (foodPrice > 1_000_000)
            throw new IllegalArgumentException(FOOD_PRICES_TOO_HIGH);

        if (foodPrice % 100 > 0)
            throw new IllegalArgumentException(ILLEGAL_FOOD_PRICES_UNIT);

    }

    @Transactional
    public List<Food> findAllRestaurantFoods(Long restaurantId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(
                        ()-> new NullPointerException(RESTAURANT_IS_NULL)
                );
        return foodRepository.findFoodsByRestaurant(restaurant);
    }

    public void deleteAll() {
        foodRepository.deleteAll();
    }
}

