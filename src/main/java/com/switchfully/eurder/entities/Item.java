package com.switchfully.eurder.entities;

import java.util.UUID;

public class Item {
    public enum StockUrgencyIndicator {STOCK_LOW,STOCK_MEDIUM,STOCK_HIGH}
    private final String id;
    private String name;
    private String Description;
    private double price;
    private int amountInStock;
    private StockUrgencyIndicator stockUrgencyIndicator;
    private static final int HIGH_STOCK_THRESHOLD = 10;
    private static final int MEDIUM_STOCK_THRESHOLD = 5;

    public Item() {
        this.id = UUID.randomUUID().toString();
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public Item setDescription(String description) {
        Description = description;
        return this;
    }

    public Item setPrice(double price) {
        this.price = price;
        return this;
    }

    public Item setAmountInStock(int amountInStock) {
        this.amountInStock = amountInStock;
        setStockUrgencyIndicator();
        return this;
    }

    private void setStockUrgencyIndicator() {
        if (this.amountInStock <= MEDIUM_STOCK_THRESHOLD)
            this.stockUrgencyIndicator = StockUrgencyIndicator.STOCK_LOW;
        if (this.amountInStock > MEDIUM_STOCK_THRESHOLD && amountInStock < HIGH_STOCK_THRESHOLD)
            this.stockUrgencyIndicator = StockUrgencyIndicator.STOCK_MEDIUM;
        if (this.amountInStock >= HIGH_STOCK_THRESHOLD)
            this.stockUrgencyIndicator = StockUrgencyIndicator.STOCK_HIGH;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return Description;
    }

    public double getPrice() {
        return price;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public String getId() {
        return id;
    }

    public StockUrgencyIndicator getStockUrgencyIndicator() {
        return stockUrgencyIndicator;
    }
}
