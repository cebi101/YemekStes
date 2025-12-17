package com.yemekstess;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private String name;

    private List<MenuItem> yemekler = new ArrayList<>();
    private List<MenuItem> aperatifler = new ArrayList<>();
    private List<MenuItem> tatlilar = new ArrayList<>();
    private List<MenuItem> icecekler = new ArrayList<>();

    private List<MenuItem> fullMenu = new ArrayList<>();

    public Restaurant(String name) {
        this.name = name;
    }

    public void addFood(MenuItem item) {
        yemekler.add(item);
        fullMenu.add(item);
    }

    public void addSnack(MenuItem item) {
        aperatifler.add(item);
        fullMenu.add(item);
    }

    public void addDessert(MenuItem item) {
        tatlilar.add(item);
        fullMenu.add(item);
    }

    public void addDrink(MenuItem item) {
        icecekler.add(item);
        fullMenu.add(item);
    }

    public void showMenu() {
        System.out.println("\n===== " + name + " MENÜ =====");

        int i = 1;

        System.out.println("\n--- YEMEKLER ---");
        for (MenuItem m : yemekler)
            System.out.println(i++ + ") " + m.getName() + " - " + m.getPrice() + " TL");

        System.out.println("\n--- APERATİFLER ---");
        for (MenuItem m : aperatifler)
            System.out.println(i++ + ") " + m.getName() + " - " + m.getPrice() + " TL");

        System.out.println("\n--- İÇECEKLER ---");
        for (MenuItem m : icecekler)
            System.out.println(i++ + ") " + m.getName() + " - " + m.getPrice() + " TL");

        System.out.println("\n--- TATLILAR ---");
        for (MenuItem m : tatlilar)
            System.out.println(i++ + ") " + m.getName() + " - " + m.getPrice() + " TL");
    }

    public List<MenuItem> getFullMenu() {
        return fullMenu;
    }
}
