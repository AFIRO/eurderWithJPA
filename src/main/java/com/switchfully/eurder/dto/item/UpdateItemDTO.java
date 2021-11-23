package com.switchfully.eurder.dto.item;

public class UpdateItemDTO {
    private String name;
    private String Description;
    private String price;
    private String amountInStock;

    public String getName() {
        return name;
    }

    public UpdateItemDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return Description;
    }

    public UpdateItemDTO setDescription(String description) {
        Description = description;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public UpdateItemDTO setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getAmountInStock() {
        return amountInStock;
    }

    public UpdateItemDTO setAmountInStock(String amountInStock) {
        this.amountInStock = amountInStock;
        return this;
    }
}
