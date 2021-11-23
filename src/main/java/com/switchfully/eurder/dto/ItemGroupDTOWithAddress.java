package com.switchfully.eurder.dto;

import java.time.LocalDate;

public class ItemGroupDTOWithAddress {
    private ItemDTO item;
    private int amountToOrder;
    private double costForItemGroup;
    private String addressToShipTo;
    private LocalDate shippingDate;

    public ItemGroupDTOWithAddress setItem(ItemDTO item) {
        this.item = item;
        return this;
    }

    public ItemGroupDTOWithAddress setAmountToOrder(int amountToOrder) {
        this.amountToOrder = amountToOrder;
        return this;
    }

    public ItemGroupDTOWithAddress setCostForItemGroup(double costForItemGroup) {
        this.costForItemGroup = costForItemGroup;
        return this;
    }

    public ItemGroupDTOWithAddress setAddressToShipTo(String addressToShipTo) {
        this.addressToShipTo = addressToShipTo;
        return this;
    }

    public ItemGroupDTOWithAddress setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
        return this;
    }

    public ItemDTO getItem() {
        return item;
    }

    public int getAmountToOrder() {
        return amountToOrder;
    }

    public double getCostForItemGroup() {
        return costForItemGroup;
    }

    public String getAddressToShipTo() {
        return addressToShipTo;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }
}
