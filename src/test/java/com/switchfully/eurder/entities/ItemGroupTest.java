package com.switchfully.eurder.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ItemGroupTest {

    @Test
    void whenAmountAndPriceIsSet_calculateCostCorrectly(){
        ItemGroup toTest = new ItemGroup(new Item().setPrice(5.5), 5);

        assertEquals(27.5, toTest.getCostForItemGroup());
    }

    @Test
    void whenSufficientStock_shippingDateNow(){
        ItemGroup toTest = new ItemGroup(new Item().setPrice(5.5).setAmountInStock(5), 5);

        assertEquals(LocalDate.now(), toTest.getShippingDate());

    }

    @Test
    void whenInsufficientStock_shippingDateInSevenDays(){
        ItemGroup toTest = new ItemGroup(new Item().setPrice(5.5).setAmountInStock(3), 5);
        assertEquals(LocalDate.now().plusDays(7L), toTest.getShippingDate());


    }

}