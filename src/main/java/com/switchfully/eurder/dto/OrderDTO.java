package com.switchfully.eurder.dto;

import com.switchfully.eurder.entities.ItemGroup;
import com.switchfully.eurder.entities.User;

import java.util.List;

public class OrderDTO {
    private String orderId;
    private String customerId;
    private List<ItemGroupDTO> orderedItems;
    private double totalPrice;



    public OrderDTO setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public OrderDTO setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public OrderDTO setOrderedItems(List<ItemGroupDTO> orderedItems) {
        this.orderedItems = orderedItems;
        return this;
    }

    public OrderDTO setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public List<ItemGroupDTO> getOrderedItems() {
        return orderedItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getCustomer() {
        return customerId;
    }

    public String getId() {
        return orderId;
    }
}
