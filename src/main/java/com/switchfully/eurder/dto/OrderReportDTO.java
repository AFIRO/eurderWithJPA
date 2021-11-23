package com.switchfully.eurder.dto;

import java.util.List;

public class OrderReportDTO {
    private List<OrderDTO> orders;
    private double totalCost;

    public OrderReportDTO setOrders(List<OrderDTO> orders) {
        this.orders = orders;
        calculateCost();
        return this;
    }

    private void calculateCost() {
        double calculatedCost = orders.stream()
                .map(OrderDTO::getTotalPrice)
                .reduce(0.0, Double::sum);
        this.totalCost = calculatedCost;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
