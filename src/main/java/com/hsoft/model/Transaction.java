package com.hsoft.model;

/**
 * This class allow to store the data of a transaction
 */
public class Transaction {

    private long quantity;
    private double price;

    public Transaction(long quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Getter of the quantity
     * 
     * @return quantity
     */
    public long getQuantity() {
        return this.quantity;
    }

    /**
     * Getter of the price
     * 
     * @return price
     */
    public double getPrice() {
        return this.price;
    }

}
