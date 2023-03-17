package com.hsoft.practice;


import com.hsoft.api.MarketDataListener;
import com.hsoft.api.PricingDataListener;
import com.hsoft.api.VwapTriggerListener;

/**
 * Entry point for the candidate to resolve the exercise
 */
public class VwapTrigger implements PricingDataListener, MarketDataListener {

  private final VwapTriggerListener vwapTriggerListener;

  /**
   * This constructor is mainly available to ease unit test by not having to provide a VwapTriggerListener
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
    // And, if matching the requirement, notify the event via 'this.vwapTriggerListener.vwapTriggered(xxx);'
  }

  @Override
  public void fairValueChanged(String productId, double fairValue) {
    // This method will be called when a new fair value is received
    // You can then perform your check
    // And, if matching the requirement, notify the event via 'this.vwapTriggerListener.vwapTriggered(xxx);'
  }
}