package com.shop.shop;
public class Clothes extends Product {
    private int size;

    public Clothes(String name, Double price, int nbItems, int size) {
        super(name, price, nbItems);
        if(size % 2 == 0 && size < 34 || size > 54)
            throw new IllegalArgumentException("Wrong size!\n" +
                    "Size must be between [34, 54] included and be an even number.");
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if(size % 2 != 0 && (size < 34 || size > 54))
            throw new IllegalArgumentException("Wrong size!\n" +
                "Size must be between [34, 54] included and be an even number.");
        this.size = size;
    }

    @Override
    public String toString() {
        return "Clothes{" +
                "name=" + this.getName() +
                ", price=" + this.getPrice() +
                ", nbItems=" + this.getNbItems() +
                ", size=" + size +
                '}';
    }

    @Override
    public void applyDiscount() {
        // 30% discount
        this.setPrice(this.getPrice() * 0.7);
    }

    @Override
    public int compareTo(Double price) {
        return super.compareTo(price);
    }
}
