package com.switchfully.eurder.repository;

import com.switchfully.eurder.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

    List<Item> findAll();

    int countAllByItemId(String itemId);

    Item findItemByItemId(String itemId);

}
