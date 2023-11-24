package com.shop.shop;
public class Accessories extends Product {
    public Accessories(String name, Double price, int nbItems) {
        super(name, price, nbItems);
    }

    @Override
    public String toString() {
        return "Accessories{" +
                "name=" + this.getName() +
                ", price=" + this.getPrice() +
                ", nbItems=" + this.getNbItems() +
                "}";
    }

    @Override
    public void applyDiscount() {
        // 50% discount
        this.setPrice(this.getPrice() * 0.5);
    }


    @Override
    public int compareTo(Double price) {
        return super.compareTo(price);
    }
}
