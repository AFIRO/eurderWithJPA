package com.switchfully.eurder.entities;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item")
public class Item {
    private static final int HIGH_STOCK_THRESHOLD = 10;
    private static final int MEDIUM_STOCK_THRESHOLD = 5;
    @Id
    @SequenceGenerator(name = "item_id_seq", sequenceName = "item_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_seq")
    @Column(name = "item_id", nullable = false)
    private String itemId;
    @Column(name = "item_name")
    private String name;
    @Column(name = "item_description")
    private String Description;
    @Column(name = "item_price")
    private double price;
    @Column(name = "item_stock")
    private int amountInStock;
    @Column(name = "item_urgency")
    @Enumerated(EnumType.STRING)
    private StockUrgencyIndicator stockUrgencyIndicator;
    public Item() {
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

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return Description;
    }

    public Item setDescription(String description) {
        Description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Item setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public Item setAmountInStock(int amountInStock) {
        this.amountInStock = amountInStock;
        setStockUrgencyIndicator();
        return this;
    }

    public String getItemId() {
        return itemId;
    }

    public StockUrgencyIndicator getStockUrgencyIndicator() {
        return stockUrgencyIndicator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(getItemId(), item.getItemId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemId());
    }

    public enum StockUrgencyIndicator {STOCK_LOW, STOCK_MEDIUM, STOCK_HIGH}
}
