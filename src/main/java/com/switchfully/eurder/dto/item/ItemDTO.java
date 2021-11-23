package com.switchfully.eurder.dto.item;

public class ItemDTO {

    private Integer itemId;
    private String name;
    private String Description;
    private double price;

    public String getName() {
        return name;
    }

    public ItemDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return Description;
    }

    public ItemDTO setDescription(String description) {
        Description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public ItemDTO setPrice(double price) {
        this.price = price;
        return this;
    }

    public Integer getItemId() {
        return itemId;
    }

    public ItemDTO setItemId(Integer itemId) {
        this.itemId = itemId;
        return this;
    }

}
