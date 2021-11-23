package com.switchfully.eurder.api;

import com.switchfully.eurder.dto.ItemDTO;
import com.switchfully.eurder.dto.ShippingReportDTO;
import com.switchfully.eurder.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipping")
public class ShippingController {
    private final OrderService orderService;
    private final Logger logger = LoggerFactory.getLogger(ShippingController.class);

    @Autowired
    public ShippingController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(produces = "application/json", params = {"authorisationId"})
    @ResponseStatus(HttpStatus.OK)
    public ShippingReportDTO getReportOfOutgoingShippingForToday(@RequestParam(value = "authorisationId") String authorisationId) {

        logger.info("Shipping report called by user " + authorisationId);
        return orderService.getReportOfOutgoingShippingForToday(authorisationId);

    }
}
