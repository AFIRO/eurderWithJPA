package com.switchfully.eurder.dto;

import com.switchfully.eurder.entities.Item;

public class ItemWithStockDTO {

    private String itemId;
    private String name;
    private String Description;
    private double price;
    private double stockAmount;
    private Item.StockUrgencyIndicator urgency;


    public ItemWithStockDTO setName(String name) {
        this.name = name;
        return this;
    }

    public ItemWithStockDTO setDescription(String description) {
        Description = description;
        return this;
    }

    public ItemWithStockDTO setPrice(double price) {
        this.price = price;
        return this;
    }


    public ItemWithStockDTO setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public ItemWithStockDTO setStockAmount(double stockAmount) {
        this.stockAmount = stockAmount;
        return this;
    }

    public ItemWithStockDTO setUrgency(Item.StockUrgencyIndicator urgency) {
        this.urgency = urgency;
        return this;
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


    public String getItemId() {
        return itemId;
    }

    public double getStockAmount() {
        return stockAmount;
    }

    public Item.StockUrgencyIndicator getUrgency() {
        return urgency;
    }
}
