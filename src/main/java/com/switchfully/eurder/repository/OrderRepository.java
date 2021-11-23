package com.switchfully.eurder.repository;

import com.switchfully.eurder.entities.Order;
import com.switchfully.eurder.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {


    Order findByOrderId(String orderId);

    List<Order> findAllByCustomer(User customer);

    List<Order> findAll();


}
