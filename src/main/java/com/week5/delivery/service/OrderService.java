package com.week5.delivery.service;

import com.week5.delivery.domain.Food;
import com.week5.delivery.domain.FoodOrder;
import com.week5.delivery.domain.Orders;
import com.week5.delivery.domain.Restaurant;
import com.week5.delivery.dto.FoodOrderResponseDto;
import com.week5.delivery.dto.OrdersRequestDto;
import com.week5.delivery.dto.OrdersResponseDto;
import com.week5.delivery.repository.FoodOrderRepository;
import com.week5.delivery.repository.FoodRepository;
import com.week5.delivery.repository.OrdersRepository;
import com.week5.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.week5.delivery.exception.ExceptionMessages.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrdersRepository ordersRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final FoodOrderRepository foodOrderRepository;

    @Transactional
    public OrdersResponseDto order(OrdersRequestDto ordersRequestDto){
        Restaurant restaurant = getRestaurant(ordersRequestDto);

        int totalPrice = 0;
        List<FoodOrderResponseDto> foodOrderResponseDtoList = new ArrayList<>();
        List<FoodOrder> foodOrders = ordersRequestDto.getFoods();
        List<FoodOrder> foodOrderList = new ArrayList<>();
        for (FoodOrder tempFoodOrder : foodOrders){
            int quantity = tempFoodOrder.getQuantity();
            if (quantity < 1 || quantity > 100){
                throw new IllegalArgumentException(ILLEGAL_FOOD_ORDER_QUANTITY);
            }

            Food food = getFood(tempFoodOrder);

            FoodOrder foodOrder = FoodOrder.builder()
                    .quantity(tempFoodOrder.getQuantity())
                    .name(food.getName())
                    .price(food.getPrice()*quantity)
                    .food(food)
                    .build();
            foodOrderRepository.save(foodOrder);
            FoodOrderResponseDto foodOrderResponseDto = new FoodOrderResponseDto(foodOrder);
            foodOrderResponseDtoList.add(foodOrderResponseDto);
            totalPrice += food.getPrice()*quantity;
            foodOrderList.add(foodOrder);
        }

        if (totalPrice < restaurant.getMinOrderPrice()){
            throw new IllegalArgumentException(ILLEGAL_TOTAL_PRICE);
        }

        int deliveryFee = restaurant.getDeliveryFee();
        totalPrice += deliveryFee;
        Orders orders = Orders.builder()
                .restaurantName(restaurant.getName())
                .totalPrice(totalPrice)
                .foods(foodOrderList)
                .build();
        ordersRepository.save(orders);
        return new OrdersResponseDto(orders,deliveryFee,foodOrderResponseDtoList);
    }

    private Restaurant getRestaurant(OrdersRequestDto ordersRequestDto) {
        return restaurantRepository.findById(ordersRequestDto.getRestaurantId())
                .orElseThrow(
                        () -> new NullPointerException(RESTAURANT_IS_NULL)
                );
    }

    private Food getFood(FoodOrder tempFoodOrder) {
        return foodRepository.findById(tempFoodOrder.getId())
                .orElseThrow(() -> new NullPointerException(CANT_FIND_FOOD));
    }

    @Transactional
    public List<OrdersResponseDto> findAllOrder() {
        List<OrdersResponseDto> ordersResponseDtoList = new ArrayList<>();

        List<Orders> ordersList = ordersRepository.findAll();

        for (Orders orders : ordersList) {
            int deliveryFee = restaurantRepository.findByName(orders.getRestaurantName()).getDeliveryFee();
            List<FoodOrderResponseDto> foodOrderResponseDtoList = new ArrayList<>();

            List<FoodOrder> foodOrderList  = foodOrderRepository.findFoodOrdersByOrders(orders);
            for (FoodOrder foodOrder : foodOrderList) {
                FoodOrderResponseDto foodsResponseDto = new FoodOrderResponseDto(foodOrder);
                foodOrderResponseDtoList.add(foodsResponseDto);
            }

            OrdersResponseDto ordersResponseDto = new OrdersResponseDto(orders, deliveryFee, foodOrderResponseDtoList);
            ordersResponseDtoList.add(ordersResponseDto);
        }

        return ordersResponseDtoList;
    }
}
