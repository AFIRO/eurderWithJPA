package com.switchfully.eurder.dto.item;

import com.switchfully.eurder.entities.Item;

public class ItemWithStockDTO {

    private String itemId;
    private String name;
    private String Description;
    private double price;
    private double stockAmount;
    private Item.StockUrgencyIndicator urgency;

    public String getName() {
        return name;
    }

    public ItemWithStockDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return Description;
    }

    public ItemWithStockDTO setDescription(String description) {
        Description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public ItemWithStockDTO setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getItemId() {
        return itemId;
    }

    public ItemWithStockDTO setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public double getStockAmount() {
        return stockAmount;
    }

    public ItemWithStockDTO setStockAmount(double stockAmount) {
        this.stockAmount = stockAmount;
        return this;
    }

    public Item.StockUrgencyIndicator getUrgency() {
        return urgency;
    }

    public ItemWithStockDTO setUrgency(Item.StockUrgencyIndicator urgency) {
        this.urgency = urgency;
        return this;
    }
}
