package com.switchfully.eurder.api;

import com.switchfully.eurder.dto.CreateItemDTO;
import com.switchfully.eurder.dto.ItemDTO;
import com.switchfully.eurder.dto.ItemWithStockDTO;
import com.switchfully.eurder.dto.UpdateItemDTO;
import com.switchfully.eurder.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;
    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json", params = "authorisationId")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemWithStockDTO createItem(@RequestParam(value = "authorisationId") String authorisationId, @RequestBody CreateItemDTO dto) {
        logger.info("Item creation called by user " + authorisationId);
        return itemService.createNewItem(authorisationId,dto);
    }

    @GetMapping(produces = "application/json", params = "authorisationId")
    @ResponseStatus(HttpStatus.OK)
    public List<ItemWithStockDTO> getAllItemsByStock(@RequestParam(value = "authorisationId") String authorisationId) {
        logger.info("Get all items by stock urgency called by user " + authorisationId);
        return itemService.getAllItemsByStock(authorisationId);
    }

    @GetMapping(produces = "application/json", params = {"authorisationId","urgency"})
    @ResponseStatus(HttpStatus.OK)
    public List<ItemWithStockDTO> getItemsByUrgency(@RequestParam(value = "authorisationId") String authorisationId, @RequestParam(value = "urgency", required = false) String urgency) {
        logger.info("Get item filtered by urgency called by user " + authorisationId);
        return itemService.getItemsByUrgency(authorisationId, urgency);
    }

    @PutMapping(produces = "application/json", consumes = "application/json", path = "/{itemId}", params = "authorisationId")
    @ResponseStatus(HttpStatus.OK)
    public ItemWithStockDTO updateItem(@RequestParam(value = "authorisationId") String authorisationId,@PathVariable("itemId") String itemId, @RequestBody UpdateItemDTO dto){
        logger.info("Item update called by user " + authorisationId);
        return itemService.updateItem(authorisationId,itemId,dto);
    }

}
