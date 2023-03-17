package com.hsoft.practice;

import java.util.HashMap;

import com.hsoft.api.MarketDataListener;
import com.hsoft.api.PricingDataListener;
import com.hsoft.api.VwapTriggerListener;
import com.hsoft.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Entry point for the candidate to resolve the exercise
 */
public class VwapTrigger implements PricingDataListener, MarketDataListener {

  private final VwapTriggerListener vwapTriggerListener;

  private static final Logger log = LogManager.getLogger(VwapTrigger.class);

  /**
   * Collection of products
   */
  private HashMap<String, Product> products = new HashMap<String, Product>();

  /**
   * This constructor is mainly available to ease unit test by not having to
   * provide a VwapTriggerListener
   */
  protected VwapTrigger() {
    this.vwapTriggerListener = (productId, vwap, fairValue) -> {
      // ignore
    };
  }

  public VwapTrigger(VwapTriggerListener vwapTriggerListener) {
    this.vwapTriggerListener = vwapTriggerListener;
  }

  @Override
  public void transactionOccurred(String productId, long quantity, double price) {
    // This method will be called when a new transaction is received
    // You can then perform your check
    // And, if matching the requirement, notify the event via
    // 'this.vwapTriggerListener.vwapTriggered(xxx);'
    Product product = products.containsKey(productId)
        ? products.get(productId)
        : new Product(productId);
    product.addTransaction(quantity, price);
    this.applyVwapTriggered(product);

  }

  @Override
  public void fairValueChanged(String productId, double fairValue) {
    // This method will be called when a new fair value is received
    // You can then perform your check
    // And, if matching the requirement, notify the event via
    // 'this.vwapTriggerListener.vwapTriggered(xxx);'
    Product product = products.containsKey(productId)
        ? products.get(productId)
        : new Product(productId);
    product.setFairValue(fairValue);
    this.applyVwapTriggered(product);
  }

  /**
   * Apply the wrap triggered if necessary.
   * In any case save the last update of the product
   * @param product Provided product
   */
  public void applyVwapTriggered(Product product) {
    if (product.isVwapGreaterThanFairValue()) {
      this.vwapTriggerListener.vwapTriggered(product.getProductId(), product.getCalculatedVwap(),
          product.getFairValue());
    }
    products.put(product.getProductId(), product);
  }

}