package com.yemekstess;

public class DrinkItem extends MenuItem {

    private String size;

    public DrinkItem(String name, double price, String size) {
        super(name + " (" + size + ")", price);
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}
