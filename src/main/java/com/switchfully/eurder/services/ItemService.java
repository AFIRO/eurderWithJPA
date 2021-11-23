package com.switchfully.eurder.services;

import com.switchfully.eurder.dto.CreateItemDTO;
import com.switchfully.eurder.dto.ItemDTO;
import com.switchfully.eurder.dto.ItemWithStockDTO;
import com.switchfully.eurder.dto.UpdateItemDTO;
import com.switchfully.eurder.entities.Item;
import com.switchfully.eurder.exceptions.UrgencyException;
import com.switchfully.eurder.mappers.ItemMapper;
import com.switchfully.eurder.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;
    private final ValidationService validationService;
    private final Logger logger = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    public ItemService(ItemMapper itemMapper, ItemRepository itemRepository, ValidationService validationService) {
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
        this.validationService = validationService;
    }


    public ItemWithStockDTO createNewItem(String authorisationId, CreateItemDTO dto) {
        validationService.assertAdmin(authorisationId);
        if (validationService.isValidCreateItemDTO(dto)) {
            Item newItem = itemMapper.toItem(dto);
            itemRepository.saveNewItem(newItem);
            logger.info("Item with ID " + newItem.getId() + " created");
            return itemMapper.toItemWithStockDTO(newItem);
        } else
            throw new IllegalArgumentException("Your parameters for the new item are not valid");
    }

    public ItemWithStockDTO updateItem(String authorisationId,String itemId,  UpdateItemDTO dto) {
        validationService.assertAdmin(authorisationId);
        Item toUpdate;
        Item updatedItem;

        if (validationService.isValidUpdateItemDTO(dto) && itemRepository.getAllItems().containsKey(itemId)) {
            toUpdate = itemRepository.getItem(itemId);
            updatedItem = itemMapper.updateItem(toUpdate, dto);
            itemRepository.updateItem(updatedItem);
            logger.info("Item with ID " + updatedItem.getId() + " updated");
            return itemMapper.toItemWithStockDTO(updatedItem);
        }
        else
            throw new IllegalArgumentException("Your parameters for the new item are not valid");
    }

    public List<ItemWithStockDTO> getAllItemsByStock(String authorisationId) {
        validationService.assertAdmin(authorisationId);
        logger.info("Stock info called by admin " + authorisationId);

        List<ItemWithStockDTO> stockLow = itemRepository.getAllItems().values().stream()
                .filter((e) -> e.getStockUrgencyIndicator().equals(Item.StockUrgencyIndicator.STOCK_LOW))
                .map(itemMapper::toItemWithStockDTO)
                .collect(Collectors.toList());
        List<ItemWithStockDTO> stockMedium = itemRepository.getAllItems().values().stream()
                .filter((e) -> e.getStockUrgencyIndicator().equals(Item.StockUrgencyIndicator.STOCK_MEDIUM))
                .map(itemMapper::toItemWithStockDTO)
                .collect(Collectors.toList());
        List<ItemWithStockDTO> stockHight = itemRepository.getAllItems().values().stream()
                .filter((e) -> e.getStockUrgencyIndicator().equals(Item.StockUrgencyIndicator.STOCK_HIGH))
                .map(itemMapper::toItemWithStockDTO)
                .collect(Collectors.toList());
        stockLow.addAll(stockMedium);
        stockLow.addAll(stockHight);
        return stockLow;
    }

    public List<ItemWithStockDTO> getItemsByUrgency(String authorisationId, String urgency) {
        validationService.assertAdmin(authorisationId);
        urgency = urgency.toLowerCase();
        logger.info("Stock info of urgency " + urgency +  " called by admin " + authorisationId);

        List<ItemWithStockDTO> toReturn;

        switch (urgency) {
            case "low": return itemRepository.getAllItems().values()
                    .stream()
                    .filter((e) -> e.getStockUrgencyIndicator().equals(Item.StockUrgencyIndicator.STOCK_LOW))
                    .map(itemMapper::toItemWithStockDTO)
                    .collect(Collectors.toList());
            case "medium": return itemRepository.getAllItems().values()
                    .stream()
                    .filter((e) -> e.getStockUrgencyIndicator().equals(Item.StockUrgencyIndicator.STOCK_MEDIUM))
                    .map(itemMapper::toItemWithStockDTO)
                    .collect(Collectors.toList());
            case "high": return itemRepository.getAllItems().values()
                    .stream()
                    .filter((e) -> e.getStockUrgencyIndicator().equals(Item.StockUrgencyIndicator.STOCK_HIGH))
                    .map(itemMapper::toItemWithStockDTO)
                    .collect(Collectors.toList());
            default: throw new UrgencyException();
        }

    }


}
