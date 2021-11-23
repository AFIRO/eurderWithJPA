package com.switchfully.eurder.dto.order;

import com.switchfully.eurder.dto.itemgroup.ItemGroupDTO;

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

    public List<ItemGroupDTO> getOrderedItems() {
        return orderedItems;
    }

    public OrderDTO setOrderedItems(List<ItemGroupDTO> orderedItems) {
        this.orderedItems = orderedItems;
        return this;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public OrderDTO setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public String getCustomer() {
        return customerId;
    }

    public String getId() {
        return orderId;
    }
}
