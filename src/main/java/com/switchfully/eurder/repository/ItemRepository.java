package com.switchfully.eurder.repository;

import com.switchfully.eurder.entities.Item;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ItemRepository {
    Map<String, Item> itemsById;

    public ItemRepository() {
        itemsById = new ConcurrentHashMap<>();
    }


    public void saveNewItem(Item item) {
        itemsById.put(item.getId(), item);
    }


    public Map<String, Item> getAllItems() {
        return itemsById;
    }

    public Item getItem(String itemId) {
        if (itemsById.containsKey(itemId))
            return itemsById.get(itemId);
        else
            throw new NoSuchElementException("The ordered Item does not exist");
    }

    public void updateItem(Item updatedItem) {
        itemsById.replace(updatedItem.getId(), updatedItem);
    }
}
