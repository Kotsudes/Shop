package com.shop.shop;

// 5. An untyped product cannot be instantiated. Modify your code to deal with that new assumption
// -> We make the class "Product" abstract, so it serves as a template for its children (inheriting
// methods, properties etc...) without being initialized itself.
public abstract class Product implements Discount, Comparable<Double> {
    private int number;
    private int nbItems;
    private String name;
    private Double price;
    private static Double income = 0.0;

    public Product(String name, Double price, int nbItems) {
        if(price < 0) throw new IllegalArgumentException("price must be positive or zero.");
        if(nbItems < 0) throw new IllegalArgumentException("nbItems must be positive or zero.");
        this.number++;
        this.name = name;
        this.price = price;
        this.nbItems = nbItems;
    }

    //region Properties
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        if(number < 0) throw new IllegalArgumentException("Negative number of product sold!");
        this.number = number;
    }

    public int getNbItems() {
        return nbItems;
    }

    public void setNbItems(int nbItems) {
        if(nbItems < 0) throw new IllegalArgumentException("Negative number of items is illegal!");
        this.nbItems = nbItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        if(price < 0) throw new IllegalArgumentException("Negative price!");
        this.price = price;
    }

    public static Double getIncome() {
        return income;
    }

    public static void setIncome(Double income) {
        if(income < 0) throw new IllegalArgumentException("Negative income!");
        Product.income += income;
    }
    //endregion

    //region Methods
    public void sell(int nbItems) {
        if (this.nbItems - nbItems < 0) {
            throw new IllegalArgumentException(String.format("Can't sell more than %d items of %s", this.getNbItems(), this.getName()));
        }
        if (this.nbItems == 0) {
            throw new IllegalArgumentException("Product Unavailable");
        }
        this.nbItems -= nbItems;
        setIncome(nbItems * getPrice());
    }

    public void purchase(int nbItems) {
        if (nbItems <= 0) throw new IllegalArgumentException("Can't buy negative or null amounts.");
        this.nbItems += nbItems;
    }

    public String toString() {
        return "Product{" +
                "number=" + number +
                ", nbItems=" + nbItems +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Double price) {
        return Double.compare(this.getPrice(), price);
    }
    //endregion
}
