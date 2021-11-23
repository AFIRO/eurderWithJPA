package com.switchfully.eurder.dto.report;

import com.switchfully.eurder.dto.itemgroup.ItemGroupDTOWithAddress;

import java.util.List;

public class ShippingReportDTO {
    private List<ItemGroupDTOWithAddress> itemsToShip;

    public List<ItemGroupDTOWithAddress> getItemsToShip() {
        return itemsToShip;
    }

    public ShippingReportDTO setItemsToShip(List<ItemGroupDTOWithAddress> itemsToShip) {
        this.itemsToShip = itemsToShip;
        return this;
    }

}
