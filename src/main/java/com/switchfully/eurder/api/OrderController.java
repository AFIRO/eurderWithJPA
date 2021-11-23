package com.switchfully.eurder.api;

import com.switchfully.eurder.dto.CreateOrderDTO;
import com.switchfully.eurder.dto.OrderDTO;
import com.switchfully.eurder.dto.OrderReportDTO;
import com.switchfully.eurder.dto.ShippingReportDTO;
import com.switchfully.eurder.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json", params = "customerId")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO createOrder(@RequestParam(value = "customerId") String customerId, @RequestBody CreateOrderDTO dto) {
        logger.info("Create order called by user " + customerId);
        return orderService.createNewOrder(customerId,dto);
    }

    @PostMapping(produces = "application/json", params = {"customerId","orderId"})
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO reorderExistingOrder(@RequestParam(value = "customerId") String customerId,@RequestParam(value = "orderId", required = false)  String orderId) {
        logger.info("Reorder existing order called by user " + customerId);
        return orderService.reorderExistingOrder(customerId,orderId);
    }

    @GetMapping(produces = "application/json", params = {"customerId"})
    @ResponseStatus(HttpStatus.OK)
    public OrderReportDTO getReportOfCustomerOrders(@RequestParam(value = "customerId") String customerId) {
        logger.info("Order report called by user " + customerId);
        return orderService.getReportOfCustomerOrders(customerId);
    }


}
