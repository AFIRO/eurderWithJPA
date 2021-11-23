package com.switchfully.eurder.repository;

import com.switchfully.eurder.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<User, Integer> {
    boolean existsByUserId(int userId);
    User findByUserId(int customerId);

}
