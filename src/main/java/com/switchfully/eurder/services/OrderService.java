package com.switchfully.eurder.services;

import com.switchfully.eurder.dto.*;
import com.switchfully.eurder.entities.ItemGroup;
import com.switchfully.eurder.entities.Order;
import com.switchfully.eurder.exceptions.AuthorisationException;
import com.switchfully.eurder.mappers.ItemMapper;
import com.switchfully.eurder.mappers.OrderMapper;
import com.switchfully.eurder.repository.CustomerRepository;
import com.switchfully.eurder.repository.ItemRepository;
import com.switchfully.eurder.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final CustomerRepository customerRepository;
    private final ValidationService validationService;
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository, ItemRepository itemRepository, CustomerRepository customerRepository, ValidationService validationService) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.customerRepository = customerRepository;
        this.validationService = validationService;
    }

    public OrderDTO createNewOrder(String customerId, CreateOrderDTO dto) {
        if (validationService.isValidCreateOrderDTO(dto) && customerRepository.getAllUsers().containsKey(customerId)) {
            Order newOrder = orderMapper.toOrder(customerId, dto);
            orderRepository.saveOrder(newOrder);
            logger.info("Order with id " + newOrder.getId() + " saved");
            return orderMapper.toOrderDTO(newOrder);
        } else
            throw new IllegalArgumentException("The parameters for your order are invalid");
    }

    public OrderDTO reorderExistingOrder(String customerId, String orderId) {
        Order existingOrder = orderRepository.getSpecificOrder(orderId);
        Order newOrder = new Order(itemRepository);

        if (existingOrder.getCustomer().getId().equals(customerId)) {
            for (ItemGroup itemGroup : existingOrder.getOrderedItems())
                newOrder.addItemToOrder(itemGroup.getItem().getId(), itemGroup.getAmountToOrder());
            newOrder.setCustomer(existingOrder.getCustomer());
            orderRepository.saveOrder(newOrder);
            logger.info("Order with id " + newOrder.getId() + " saved");
            return orderMapper.toOrderDTO(newOrder);
        } else throw new AuthorisationException("You are not allowed to reorder this specific order.");
    }

    public OrderReportDTO getReportOfCustomerOrders(String customerId) {
        List<OrderDTO> reportedOrders;

        if (customerRepository.getAllUsers().containsKey(customerId)) {
            reportedOrders = orderRepository.getAllOrdersOfSpecificUser(customerId)
                    .stream()
                    .map(orderMapper::toOrderDTO)
                    .collect(Collectors.toList());

            if (reportedOrders.isEmpty())
                throw new NoSuchElementException("You have no orders to report");

            logger.info("Order report of  " + customerId + " generated");
            return new OrderReportDTO().setOrders(reportedOrders);
        }
        else
            throw new NoSuchElementException("This customer is not found in our database.");
    }

    public ShippingReportDTO getReportOfOutgoingShippingForToday(String authorisationId) {
        validationService.assertAdmin(authorisationId);

        List<ItemGroupDTOWithAddress> outgoingItems = orderRepository.getAllOrders()
                .stream()
                .map(Order::getOrderedItems)
                .flatMap(List::stream)
                .filter(itemGroup -> itemGroup.getShippingDate().equals(LocalDate.now()))
                .map(orderMapper::toItemGroupDTOWithAddress)
                .collect(Collectors.toList());
        logger.info("Daily shipping report generated for  " + authorisationId);
        return new ShippingReportDTO().setItemsToShip(outgoingItems);
    }
}
