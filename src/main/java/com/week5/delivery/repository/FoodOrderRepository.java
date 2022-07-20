package com.week5.delivery.repository;

import com.week5.delivery.domain.FoodOrder;
import com.week5.delivery.domain.Orders;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
    List<FoodOrder> findFoodOrdersByOrders(Orders orders);
}
