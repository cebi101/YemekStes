package com.yemekstess;

public class DrinkItem extends MenuItem {

    private String size;

    public DrinkItem(String name, double price, String size) {
        super(name, price);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    @Override
    public String toString() {
        return getName() + " (" + size + ") - " + getPrice() + " TL";
    }
}
