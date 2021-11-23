package com.switchfully.eurder.dto;

public class UpdateItemDTO {
    private String name;
    private String Description;
    private String price;
    private String amountInStock;

    public UpdateItemDTO setName(String name) {
        this.name = name;
        return this;
    }

    public UpdateItemDTO setDescription(String description) {
        Description = description;
        return this;
    }

    public UpdateItemDTO setPrice(String price) {
        this.price = price;
        return this;
    }

    public UpdateItemDTO setAmountInStock(String amountInStock) {
        this.amountInStock = amountInStock;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return Description;
    }

    public String getPrice() {
        return price;
    }

    public String getAmountInStock() {
        return amountInStock;
    }
}
