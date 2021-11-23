package com.switchfully.eurder.repository;

import com.switchfully.eurder.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CustomerRepository {
    private final Map<String, User> savedUsersById;

    public CustomerRepository() {
        savedUsersById = new ConcurrentHashMap();
        User rootAdmin = new User("root", "admin", "admin@admin.admin", "admin", "admin").setAdmin();
        System.out.println("Root admin for testing  = " + rootAdmin.getId());
        savedUsersById.put(rootAdmin.getId(), rootAdmin);
    }

    public void saveUser(User user) {
        savedUsersById.put(user.getId(), user);
    }

    public Map<String, User> getAllUsers() {
        return savedUsersById;
    }

    public User getSpecificUser(String customerId) {
        if (savedUsersById.containsKey(customerId))
            return savedUsersById.get(customerId);
        else throw new NoSuchElementException("The customer linked to this order does not exist");
    }
}
