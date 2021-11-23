package com.switchfully.eurder.dto.item;

public class CreateItemDTO {
    private String name;
    private String Description;
    private String price;
    private String amountInStock;

    public String getName() {
        return name;
    }

    public CreateItemDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return Description;
    }

    public CreateItemDTO setDescription(String description) {
        Description = description;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public CreateItemDTO setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getAmountInStock() {
        return amountInStock;
    }

    public CreateItemDTO setAmountInStock(String amountInStock) {
        this.amountInStock = amountInStock;
        return this;
    }
}
