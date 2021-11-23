package com.switchfully.eurder.services;

import com.switchfully.eurder.dto.item.CreateItemDTO;
import com.switchfully.eurder.dto.item.ItemWithStockDTO;
import com.switchfully.eurder.dto.item.UpdateItemDTO;
import com.switchfully.eurder.entities.Item;
import com.switchfully.eurder.exceptions.UrgencyException;
import com.switchfully.eurder.mappers.ItemMapper;
import com.switchfully.eurder.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
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
            itemRepository.save(newItem);
            logger.info("Item with ID " + newItem.getItemId() + " created");
            return itemMapper.toItemWithStockDTO(newItem);
        } else
            throw new IllegalArgumentException("Your parameters for the new item are not valid");
    }

    public ItemWithStockDTO updateItem(String authorisationId, String itemId, UpdateItemDTO dto) {
        validationService.assertAdmin(authorisationId);
        Item toUpdate;
        Item updatedItem;


        if (validationService.isValidUpdateItemDTO(dto) && itemRepository.existsByItemId(itemId)) {
            toUpdate = itemRepository.findItemByItemId(itemId);
            updatedItem = itemMapper.updateItem(toUpdate, dto);
            itemRepository.save(updatedItem);
            logger.info("Item with ID " + updatedItem.getItemId() + " updated");
            return itemMapper.toItemWithStockDTO(updatedItem);
        } else
            throw new IllegalArgumentException("Your parameters for the new item are not valid");
    }

    public List<ItemWithStockDTO> getAllItemsByStock(String authorisationId) {
        validationService.assertAdmin(authorisationId);
        logger.info("Stock info called by admin " + authorisationId);

        List<ItemWithStockDTO> stockLow = itemRepository.findAll().stream()
                .filter((e) -> e.getStockUrgencyIndicator().equals(Item.StockUrgencyIndicator.STOCK_LOW))
                .map(itemMapper::toItemWithStockDTO)
                .collect(Collectors.toList());
        List<ItemWithStockDTO> stockMedium = itemRepository.findAll().stream()
                .filter((e) -> e.getStockUrgencyIndicator().equals(Item.StockUrgencyIndicator.STOCK_MEDIUM))
                .map(itemMapper::toItemWithStockDTO)
                .collect(Collectors.toList());
        List<ItemWithStockDTO> stockHight = itemRepository.findAll().stream()
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
        logger.info("Stock info of urgency " + urgency + " called by admin " + authorisationId);

        List<ItemWithStockDTO> toReturn;

        switch (urgency) {
            case "low":
                return itemRepository.findAll()
                        .stream()
                        .filter((e) -> e.getStockUrgencyIndicator().equals(Item.StockUrgencyIndicator.STOCK_LOW))
                        .map(itemMapper::toItemWithStockDTO)
                        .collect(Collectors.toList());
            case "medium":
                return itemRepository.findAll()
                        .stream()
                        .filter((e) -> e.getStockUrgencyIndicator().equals(Item.StockUrgencyIndicator.STOCK_MEDIUM))
                        .map(itemMapper::toItemWithStockDTO)
                        .collect(Collectors.toList());
            case "high":
                return itemRepository.findAll()
                        .stream()
                        .filter((e) -> e.getStockUrgencyIndicator().equals(Item.StockUrgencyIndicator.STOCK_HIGH))
                        .map(itemMapper::toItemWithStockDTO)
                        .collect(Collectors.toList());
            default:
                throw new UrgencyException();
        }

    }


}
