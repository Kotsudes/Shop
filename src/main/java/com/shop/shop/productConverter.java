package com.shop.shop;

import com.shop.shop.shopclass.Accessories;
import com.shop.shop.shopclass.Clothes;
import com.shop.shop.shopclass.Product;
import com.shop.shop.dao.AccessoriesDAO;
import com.shop.shop.dao.ClothesDAO;
import com.shop.shop.dao.ShoesDAO;
import com.shop.shop.shopclass.Shoes;

public class productConverter extends Product {
    private int size;
    private int shoeSize;
    private String type;

    public int getSize() {
        return size;
    }

    public int getShoeSize() {
        return shoeSize;
    }
    public String getType() {
        return type;
    }

    public productConverter(int id, String name, double price, int nbItems, int size, int shoeSize, String type) {
        super(id, name, price, nbItems);
        this.size = size;
        this.shoeSize = shoeSize;
        this.type = type;
    }

    @Override
    public void applyDiscount() {
    }

    public void addItems(int nbItems) {
        if(nbItems < 0){
            throw new IllegalArgumentException("Negative number of items");
        }
        setNbItems(getNbItems() + nbItems);
        action();
    }

    public void removeItems(int nbItems) {
        if(nbItems < 0){
            throw new IllegalArgumentException("Negative number of items");
        }
        if(getNbItems() - nbItems <0){
            throw new IllegalArgumentException("Not enough items");
        }
        setNbItems(getNbItems() - nbItems);
        action();
    }

    private void action() {
        switch (type) {
            case "Clothes":
                ClothesDAO.update(new Clothes(getId(), getName(), getPrice(), getNbItems(),getSize()));
                break;
            case "Shoes":
                ShoesDAO.update(new Shoes(getId(), getName(), getPrice(), getNbItems(),getShoeSize()));
                break;
            case "Accessories":
                AccessoriesDAO.update(new Accessories(getId(), getName(), getPrice(), getNbItems()));
                break;
        }
    }
}