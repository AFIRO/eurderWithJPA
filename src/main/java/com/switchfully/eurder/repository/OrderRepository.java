package com.switchfully.eurder.repository;

import com.switchfully.eurder.dto.OrderDTO;
import com.switchfully.eurder.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {
    private final Map<String,Order> ordersByOrderId;

    @Autowired
    public OrderRepository() {
        this.ordersByOrderId = new ConcurrentHashMap<>();
    }


    public void saveOrder(Order newOrder) {
        ordersByOrderId.put(newOrder.getId(),newOrder);
    }

    public Order getSpecificOrder(String orderId) {
        if (ordersByOrderId.containsKey(orderId))
            return ordersByOrderId.get(orderId);
        else
            throw new NoSuchElementException("The order requested does not exist");
    }

    public List<Order> getAllOrdersOfSpecificUser(String customerId) {
        return ordersByOrderId.values()
                .stream()
                .filter(order -> order.getCustomer().getId().equals(customerId))
                .collect(Collectors.toList());
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(ordersByOrderId.values());
    }


}
