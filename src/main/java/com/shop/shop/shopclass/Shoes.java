package com.shop.shop.shopclass;

public class Shoes extends Product {

  private int shoeSize;

  public Shoes(int id, String name, double price, int nbItems, int shoeSize) {
    super(id, name, price, nbItems);
    this.shoeSize = shoeSize;
  }

  public int getShoeSize() {
    return shoeSize;
  }

  public void setShoeSize(int shoeSize) {
    this.shoeSize = shoeSize;
  }

  @Override
  public String toString() {
    return super.toString()+" "+shoeSize;
  }

  @Override
  public void applyDiscount() {
    this.setPrice(this.getPrice()*(1-DISCOUNT_SHOES));

  }
}
