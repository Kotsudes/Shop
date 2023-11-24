package com.shop.shop;
public class Shoes extends Product {
    private int shoeSize;
    public Shoes(String name, Double price, int nbItems, int shoeSize) {
        super(name, price, nbItems);
        if(shoeSize < 36 || shoeSize > 50) throw new IllegalArgumentException("Wrong shoe size!\n" +
                "shoeSize must be between [36, 50] included.");
        this.shoeSize = shoeSize;
    }

    public int getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(int shoeSize) {
        if(shoeSize < 36 || shoeSize > 50) throw new IllegalArgumentException("Wrong shoe size!\n" +
                "shoeSize must be between [36, 50] included.");
        this.shoeSize = shoeSize;
    }

    @Override
    public String toString() {
        return "Shoes{" +
                "name=" + this.getName() +
                ", price=" + this.getPrice() +
                ", nbItems=" + this.getNbItems() +
                ", shoeSize=" + shoeSize +
                '}';
    }

    @Override
    public void applyDiscount() {
        // 20% discount
        this.setPrice(this.getPrice() * 0.8);
    }

    @Override
    public int compareTo(Double price) {
        return super.compareTo(price);
    }
}
