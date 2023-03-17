package com.hsoft.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    public void testGetCalculatedVwapOneTransaction() {
        Product product = new Product("P1");
        product.addTransaction(1000, 10);
        assertEquals(10, product.getCalculatedVwap());
    }

    @Test
    public void testGetCalculatedVwapTwoTransactions() {
        Product product = new Product("P1");
        product.addTransaction(1000, 10);
        product.addTransaction(2000, 11);
        assertEquals(10.666666666666666, product.getCalculatedVwap());
    }

    @Test
    public void testGetCalculatedVwapSixTransactions() {
        Product product = new Product("P1");
        product.addTransaction(1000, 10);
        product.addTransaction(2000, 11);
        product.addTransaction(2000, 11);
        product.addTransaction(1000, 12.5);
        product.addTransaction(3000, 13);
        product.addTransaction(5000, 10);
        assertEquals(11.192307692307692, product.getCalculatedVwap());
    }

    @Test
    public void testisVwapGreaterThanFairValueTrue() {
        Product product = new Product("P1");
        product.addTransaction(1000, 10);
        product.setFairValue(9);
        assertTrue(product.isVwapGreaterThanFairValue());
    }

    @Test
    public void testisVwapGreaterThanFairValueFalse() {
        Product product = new Product("P1");
        product.addTransaction(1000, 10);
        product.setFairValue(11);
        assertFalse(product.isVwapGreaterThanFairValue());
    }
    
}
