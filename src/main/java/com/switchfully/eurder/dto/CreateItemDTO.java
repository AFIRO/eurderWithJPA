package com.switchfully.eurder.dto;

public class CreateItemDTO {
    private String name;
    private String Description;
    private String price;
    private String amountInStock;

    public CreateItemDTO setName(String name) {
        this.name = name;
        return this;
    }

    public CreateItemDTO setDescription(String description) {
        Description = description;
        return this;
    }

    public CreateItemDTO setPrice(String price) {
        this.price = price;
        return this;
    }

    public CreateItemDTO setAmountInStock(String amountInStock) {
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
