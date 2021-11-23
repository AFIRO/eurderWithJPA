package com.switchfully.eurder.mappers;

import com.switchfully.eurder.dto.item.CreateItemDTO;
import com.switchfully.eurder.dto.item.ItemDTO;
import com.switchfully.eurder.dto.item.ItemWithStockDTO;
import com.switchfully.eurder.dto.item.UpdateItemDTO;
import com.switchfully.eurder.entities.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public Item toItem(CreateItemDTO dto) {
        return new Item()
                .setName(dto.getName())
                .setDescription(dto.getDescription())
                .setPrice(Double.parseDouble(dto.getPrice()))
                .setAmountInStock(Integer.parseInt(dto.getAmountInStock()));
    }

    public Item updateItem(Item toUpdate, UpdateItemDTO dto) {
        return toUpdate
                .setName(dto.getName())
                .setPrice(Double.parseDouble(dto.getPrice()))
                .setAmountInStock(Integer.parseInt(dto.getAmountInStock()))
                .setDescription(dto.getDescription());
    }


    public ItemDTO toItemDTO(Item item) {
        return new ItemDTO()
                .setItemId(item.getItemId())
                .setName(item.getName())
                .setDescription(item.getDescription())
                .setPrice(item.getPrice());
    }

    public ItemWithStockDTO toItemWithStockDTO(Item item) {
        return new ItemWithStockDTO()
                .setItemId(item.getItemId())
                .setName(item.getName())
                .setDescription(item.getDescription())
                .setPrice(item.getPrice())
                .setStockAmount(item.getAmountInStock())
                .setUrgency(item.getStockUrgencyIndicator());
    }


}
