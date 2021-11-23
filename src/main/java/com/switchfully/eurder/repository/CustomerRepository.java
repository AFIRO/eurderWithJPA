package com.switchfully.eurder.repository;

import com.switchfully.eurder.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<User, String> {


    List<User> findAll();

    User findByUserId(String customerId);

    int countAllByUserId(String userId);

}
