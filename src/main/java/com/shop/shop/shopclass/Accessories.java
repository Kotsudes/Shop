package com.shop.shop.shopclass;

import com.shop.shop.Discount;

public class Accessories extends Product{

  public Accessories(int id, String name, double price, int nbItems) {
    super(id, name, price, nbItems);
  }

  @Override
  public String toString() {
    return super.toString();
  }

  @Override
  public void applyDiscount() {
    this.setPrice(this.getPrice()*(1- Discount.DISCOUNT_ACCESSORIES));

  }
}
