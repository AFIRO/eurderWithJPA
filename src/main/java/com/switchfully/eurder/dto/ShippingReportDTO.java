package com.switchfully.eurder.dto;

import java.util.List;

public class ShippingReportDTO {
    private List<ItemGroupDTOWithAddress> itemsToShip;

    public ShippingReportDTO setItemsToShip(List<ItemGroupDTOWithAddress> itemsToShip) {
        this.itemsToShip = itemsToShip;
        return this;
    }

    public List<ItemGroupDTOWithAddress> getItemsToShip() {
        return itemsToShip;
    }

}
