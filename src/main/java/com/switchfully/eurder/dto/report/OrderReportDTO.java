package com.switchfully.eurder.dto.report;

import com.switchfully.eurder.dto.order.OrderDTO;

import java.util.List;

public class OrderReportDTO {
    private List<OrderDTO> orders;
    private double totalCost;

    private void calculateCost() {
        double calculatedCost = orders.stream()
                .map(OrderDTO::getTotalPrice)
                .reduce(0.0, Double::sum);
        this.totalCost = calculatedCost;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public OrderReportDTO setOrders(List<OrderDTO> orders) {
        this.orders = orders;
        calculateCost();
        return this;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
