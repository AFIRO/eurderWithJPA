package com.switchfully.eurder.dto.order;

import com.switchfully.eurder.dto.itemgroup.CreateItemGroupDTO;

import java.util.List;

public class CreateOrderDTO {
    private List<CreateItemGroupDTO> orderedItems;

    public List<CreateItemGroupDTO> getOrderedItems() {
        return orderedItems;
    }

    public CreateOrderDTO setOrderedItems(List<CreateItemGroupDTO> orderedItems) {
        this.orderedItems = orderedItems;
        return this;
    }


}
