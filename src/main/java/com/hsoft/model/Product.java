package com.hsoft.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class allow to store the data of a Product
 */
public class Product {

    private String productId;
    private double fairValue;
    ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    public Product(String productId) {
        this.productId = productId;
    }

    /**
     * Getter of the product Id
     * @return
     */
    public String getProductId() {
        return this.productId;
    }

    /**
     * Getter of the fairValue
     * @return
     */
    public double getFairValue() {
        return this.fairValue;
    }

    /**
     * Setter of the fairValue
     * @param fairValue
     */
    public void setFairValue(double fairValue) {
        this.fairValue = fairValue;
    }

    /**
     * Add a transaction to the collection
     * Use the key word synchronized to be sure one thread per instance of the class can execute this method.
     * @param quantity
     * @param price
     */
    public synchronized void addTransaction(long quantity, double price) {
        transactions.add(new Transaction(quantity, price));
    }

    /**
     * Check if the vwap is greater than the fair value
     * Use the key word synchronized to be sure one thread per instance of the class can execute this method.
     * @return
     */
    public synchronized boolean isVwapGreaterThanFairValue() {
        return !this.transactions.isEmpty() && this.fairValue > 0 && this.getCalculatedVwap() > this.fairValue;
    }

    /**
     * Get the calculated Wrap based on the last 5 transactions
     * If less than 5 transactions the calculation is based on all
     * Use the key word synchronized to be sure one thread per instance of the class can execute this method.
     * @return the calculated Vwap
     */
    public synchronized double getCalculatedVwap() {
        List<Transaction> lastFiveTransactions = this.transactions.size() > 5
                ? this.transactions.subList(this.transactions.size() - 5, this.transactions.size())
                : this.transactions;
        return lastFiveTransactions.stream().mapToDouble(t -> t.getQuantity() * t.getPrice()).sum()
                / lastFiveTransactions.stream().mapToLong(t -> t.getQuantity()).sum();
    }

}
