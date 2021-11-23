package com.switchfully.eurder.mappers;

import com.switchfully.eurder.dto.itemgroup.CreateItemGroupDTO;
import com.switchfully.eurder.dto.itemgroup.ItemGroupDTO;
import com.switchfully.eurder.dto.itemgroup.ItemGroupDTOWithAddress;
import com.switchfully.eurder.dto.order.CreateOrderDTO;
import com.switchfully.eurder.dto.order.OrderDTO;
import com.switchfully.eurder.entities.Item;
import com.switchfully.eurder.entities.ItemGroup;
import com.switchfully.eurder.entities.Order;
import com.switchfully.eurder.entities.User;
import com.switchfully.eurder.repository.CustomerRepository;
import com.switchfully.eurder.repository.ItemRepository;
import com.switchfully.eurder.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    private final ItemRepository itemRepository;
    private final CustomerRepository customerRepository;
    private final ItemMapper itemMapper;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderMapper(ItemRepository itemRepository, CustomerRepository customerRepository, ItemMapper itemMapper, OrderRepository orderRepository) {
        this.itemRepository = itemRepository;
        this.customerRepository = customerRepository;
        this.itemMapper = itemMapper;
        this.orderRepository = orderRepository;
    }

    public Order toOrder(int customerId, CreateOrderDTO dto) {
        List<ItemGroup> orderedItems = dto.getOrderedItems().stream()
                .map(this::toItemGroup)
                .collect(Collectors.toList());

        return new Order()
                .setCustomer(customerRepository.findByUserId(customerId))
                .addListOfItemGroupToOrder(orderedItems);
    }

    public OrderDTO toOrderDTO(Order order) {
        List<ItemGroupDTO> orderedItems = order.getOrderedItems()
                .stream()
                .map(this::toItemGroupDTO)
                .collect(Collectors.toList());

        return new OrderDTO()
                .setOrderId(order.getOrderId())
                .setCustomerId(order.getCustomer().getUserId())
                .setOrderedItems(orderedItems)
                .setTotalPrice(order.getTotalPrice());
    }


    public ItemGroup toItemGroup(CreateItemGroupDTO dto) {
        Item itemToAdd = itemRepository.findItemByItemId(dto.getItemId());
        return new ItemGroup(itemToAdd, Integer.parseInt(dto.getAmountToOrder()));
    }

    private ItemGroupDTO toItemGroupDTO(ItemGroup itemGroup) {
        return new ItemGroupDTO()
                .setItem(itemMapper.toItemDTO(itemGroup.getItem()))
                .setAmountToOrder(itemGroup.getAmountToOrder())
                .setCostForItemGroup(itemGroup.getCostForItemGroup())
                .setShippingDate(itemGroup.getShippingDate());
    }

    public ItemGroupDTOWithAddress toItemGroupDTOWithAddress(ItemGroup itemGroup) {
        String address = orderRepository.findAll()
                .stream()
                .filter(order -> order.getOrderedItems().contains(itemGroup))
                .map(Order::getCustomer)
                .map(User::getAddress)
                .collect(Collectors.joining());

        return new ItemGroupDTOWithAddress()
                .setItem(itemMapper.toItemDTO(itemGroup.getItem()))
                .setCostForItemGroup(itemGroup.getCostForItemGroup())
                .setAmountToOrder(itemGroup.getAmountToOrder())
                .setShippingDate(itemGroup.getShippingDate())
                .setAddressToShipTo(address);

    }
}