package com.switchfully.eurder.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "item_group")
public class ItemGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_id_sequence")
    @SequenceGenerator(name = "group_id_sequence", sequenceName = "group_id_sequence", allocationSize = 1)
    @Column(name = "group_id", nullable = false)
    private int itemGroupId;
    @OneToOne
    @JoinColumn(name = "item_fk")
    private Item item;
    @Column(name = "group_amount_to_order")
    private int amountToOrder;
    @Column(name = "group_cost")
    private double costForItemGroup;
    @Column(name = "group_shipping_date", columnDefinition = "DATE")
    private LocalDate shippingDate;
    private static final Long DAYS_SHIPPING_EXTRA_IF_INSUFFICIENT_STOCK = 7L;

    public ItemGroup() {

    }


    public ItemGroup(Item item, int amountToOrder) {
        this.item = item;
        this.amountToOrder = amountToOrder;
        this.costForItemGroup = calculatePriceForItemGroup();
        shippingDate = calculateShippingDate();

    }

    private double calculatePriceForItemGroup() {
        return this.amountToOrder * item.getPrice();
    }

    public LocalDate calculateShippingDate() {
        if (item.getAmountInStock() >= amountToOrder)
            return LocalDate.now();
        else
            return LocalDate.now().plusDays(DAYS_SHIPPING_EXTRA_IF_INSUFFICIENT_STOCK);
    }

    public Item getItem() {
        return item;
    }

    public int getAmountToOrder() {
        return amountToOrder;
    }

    public double getCostForItemGroup() {
        return costForItemGroup;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }


    public int getItemGroupId() {
        return itemGroupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemGroup)) return false;
        ItemGroup itemGroup = (ItemGroup) o;
        return getItemGroupId() == itemGroup.getItemGroupId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemGroupId());
    }
}



