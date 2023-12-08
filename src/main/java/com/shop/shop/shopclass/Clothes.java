package com.shop.shop.shopclass;

import com.shop.shop.Discount;

public class Clothes extends Product{

  private int size;

  public Clothes(int id, String name, double price, int nbItems, int size) {
    super(id, name, price, nbItems);
    setSize(size);
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) throws IllegalArgumentException {
    if(size>=36 && size <=50){
      this.size = size;
    }else throw new IllegalArgumentException("Size is not valid");
  }

  @Override
  public String toString() {
    return super.toString()+
            " " + size;
  }

  @Override
  public void applyDiscount() {
    this.setPrice(this.getPrice()*(1- Discount.DISCOUNT_CLOTHES));
  }


}
