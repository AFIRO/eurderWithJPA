package com.switchfully.eurder.entities;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void WhenAmountInStockChanges_urgencyChangesAccordingly() {
        Item toTest = new Item().setAmountInStock(5);

        Assertions.assertThat(toTest.getStockUrgencyIndicator()).isEqualTo(Item.StockUrgencyIndicator.STOCK_LOW);
    }
}