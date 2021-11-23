package com.switchfully.eurder.dto;

public class ItemDTO {

    private String itemId;
    private String name;
    private String Description;
    private double price;


    public ItemDTO setName(String name) {
        this.name = name;
        return this;
    }

    public ItemDTO setDescription(String description) {
        Description = description;
        return this;
    }

    public ItemDTO setPrice(double price) {
        this.price = price;
        return this;
    }


    public ItemDTO setItemId(String itemId) {
        this.itemId = itemId;
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

}
