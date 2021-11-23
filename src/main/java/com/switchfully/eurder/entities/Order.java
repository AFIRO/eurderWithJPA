package com.switchfully.eurder.entities;

import com.switchfully.eurder.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class Order {
    private final ItemRepository itemRepository;
    private final String id;
    private final List<ItemGroup> orderedItems;
    private double totalPrice;
    private User customer;

    @Autowired
    public Order(ItemRepository itemRepository) {
        id = UUID.randomUUID().toString();
        this.itemRepository = itemRepository;
        orderedItems = new ArrayList();
    }

    public Order calculateTotalPrice() {
        this.totalPrice = orderedItems.stream().map(ItemGroup::getCostForItemGroup).reduce(0.0, Double::sum);
        return this;
    }

    public Order addItemToOrder(String itemId, int numberOfItems){
        Item itemToAdd = itemRepository.getItem(itemId);
        orderedItems.add(new ItemGroup(itemToAdd, numberOfItems));
        calculateTotalPrice();
        return this;
    }

    public Order addItemGroupToOrder(ItemGroup itemGroup){
        orderedItems.add(itemGroup);
        calculateTotalPrice();
        return this;
    }

    public Order addListOfItemGroupToOrder(List<ItemGroup> itemGroups){
        orderedItems.addAll(itemGroups);
        calculateTotalPrice();
        return this;
    }



    public Order setCustomer(User customer) {
        this.customer = customer;
        return this;
    }

    public List<ItemGroup> getOrderedItems() {
        return orderedItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public User getCustomer() {
        return customer;
    }

    public String getId() {
        return id;
    }


}
