package com.switchfully.eurder.entities;

import com.switchfully.eurder.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_sequence")
    @SequenceGenerator(name = "order_id_sequence", sequenceName = "order_id_sequence", allocationSize = 1)
    @Column(name = "order_id", nullable = false)
    private Integer orderId;
    @OneToMany
    @JoinColumn(name = "order_fk")
    private List<ItemGroup> orderedItems;
    @Column(name = "order_price")
    private double totalPrice;
    @OneToOne
    @JoinColumn(name = "user_fk")
    private User customer;
    @Transient
    private ItemRepository itemRepository;


    public Order() {
    }

    public Order calculateTotalPrice() {
        this.totalPrice = orderedItems.stream().map(ItemGroup::getCostForItemGroup).reduce(0.0, Double::sum);
        return this;
    }

    public Order addItemToOrder(Integer itemId, int numberOfItems) {
        Item itemToAdd = itemRepository.findItemByItemId(itemId);
        orderedItems.add(new ItemGroup(itemToAdd, numberOfItems));
        calculateTotalPrice();
        return this;
    }

    public Order addItemGroupToOrder(ItemGroup itemGroup) {
        orderedItems.add(itemGroup);
        calculateTotalPrice();
        return this;
    }

    public Order addListOfItemGroupToOrder(List<ItemGroup> itemGroups) {
        orderedItems.addAll(itemGroups);
        calculateTotalPrice();
        return this;
    }

    public List<ItemGroup> getOrderedItems() {
        return orderedItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public User getCustomer() {
        return customer;
    }

    public Order setCustomer(User customer) {
        this.customer = customer;
        return this;
    }

    public Integer getOrderId() {
        return orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getOrderId(), order.getOrderId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId());
    }
}
