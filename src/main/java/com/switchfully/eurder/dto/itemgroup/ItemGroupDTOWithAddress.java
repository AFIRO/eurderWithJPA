package com.switchfully.eurder.dto.itemgroup;

import com.switchfully.eurder.dto.item.ItemDTO;

import java.time.LocalDate;

public class ItemGroupDTOWithAddress {
    private ItemDTO item;
    private int amountToOrder;
    private double costForItemGroup;
    private String addressToShipTo;
    private LocalDate shippingDate;

    public ItemDTO getItem() {
        return item;
    }

    public ItemGroupDTOWithAddress setItem(ItemDTO item) {
        this.item = item;
        return this;
    }

    public int getAmountToOrder() {
        return amountToOrder;
    }

    public ItemGroupDTOWithAddress setAmountToOrder(int amountToOrder) {
        this.amountToOrder = amountToOrder;
        return this;
    }

    public double getCostForItemGroup() {
        return costForItemGroup;
    }

    public ItemGroupDTOWithAddress setCostForItemGroup(double costForItemGroup) {
        this.costForItemGroup = costForItemGroup;
        return this;
    }

    public String getAddressToShipTo() {
        return addressToShipTo;
    }

    public ItemGroupDTOWithAddress setAddressToShipTo(String addressToShipTo) {
        this.addressToShipTo = addressToShipTo;
        return this;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public ItemGroupDTOWithAddress setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
        return this;
    }
}
