package com.yemekstes;

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

    // Varsayılan menü tek yerde kurulur; Main sadece akışı yönetir.
    public static Restaurant createDefault() {
        Restaurant r = new Restaurant("YemekStes");

        r.addFood(new MenuItem("Burger", 80));
        r.addFood(new MenuItem("Pizza", 100));
        r.addFood(new MenuItem("Lahmacun", 65));
        r.addFood(new MenuItem("Döner", 120));

        r.addSnack(new MenuItem("Patates Kızartması", 35));
        r.addSnack(new MenuItem("Soğan Halkası", 30));

        r.addDrink(new DrinkItem("Kola", 15, "Küçük"));
        r.addDrink(new DrinkItem("Ayran", 12, "Küçük"));
        r.addDrink(new DrinkItem("Kola", 20, "Büyük"));
        r.addDrink(new DrinkItem("Ayran", 15, "Büyük"));
        r.addDrink(new DrinkItem("Ice Tea", 15, "Küçük"));

        r.addDessert(new MenuItem("Sufle", 45));
        r.addDessert(new MenuItem("Cheesecake", 55));

        return r;
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

    public List<MenuItem> getFoods() {
        return yemekler;
    }

    public List<MenuItem> getSnacks() {
        return aperatifler;
    }

    public List<MenuItem> getDrinks() {
        return icecekler;
    }

    public List<MenuItem> getDesserts() {
        return tatlilar;
    }
}
