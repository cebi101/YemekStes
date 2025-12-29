package com.yemekstess;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("======= YEMEK SİPARİŞ SİTESİNE HOŞ GELDİNİZ =======\n");

        System.out.print("Kullanıcı adı: ");
        String username = scanner.nextLine();

        System.out.print("Şifre: ");
        String password = scanner.nextLine();

        System.out.print("Adınız: ");
        String name = scanner.nextLine();

        System.out.print("Şehriniz: ");
        String city = scanner.nextLine();

        System.out.print("Telefon Numaranız: ");
        String phone = scanner.nextLine();

        System.out.print("Adresiniz: ");
        String address = scanner.nextLine();

        Customer musteri = new Customer(username, password, name, city, phone, address);
        Order siparis = new Order(musteri);

        // MENÜLER
        List<MenuItem> foods = new ArrayList<>();
        foods.add(new MenuItem("Burger", 80));
        foods.add(new MenuItem("Pizza", 100));
        foods.add(new MenuItem("Lahmacun", 65));
        foods.add(new MenuItem("Döner", 120));

        List<MenuItem> snacks = new ArrayList<>();
        snacks.add(new MenuItem("Patates Kızartması", 35));
        snacks.add(new MenuItem("Soğan Halkası", 30));

        List<MenuItem> drinks = new ArrayList<>();
        drinks.add(new DrinkItem("Kola", 15, "Küçük"));
        drinks.add(new DrinkItem("Ayran", 12, "Küçük"));

        List<MenuItem> desserts = new ArrayList<>();
        desserts.add(new MenuItem("Sufle", 45));
        desserts.add(new MenuItem("Cheesecake", 55));

        boolean devam = true;

        while (devam) {
            System.out.println("\nKategori seçiniz:");
            System.out.println("1 - Yemekler");
            System.out.println("2 - Atıştırmalıklar");
            System.out.println("3 - İçecekler");
            System.out.println("4 - Tatlılar");
            System.out.println("0 - Siparişi Bitir");

            System.out.print("Seçim: ");
            int kategori = scanner.nextInt();

            if (kategori == 0) break;

            List<MenuItem> secilenMenu = null;

            switch (kategori) {
                case 1 -> secilenMenu = foods;
                case 2 -> secilenMenu = snacks;
                case 3 -> secilenMenu = drinks;
                case 4 -> secilenMenu = desserts;
                default -> {
                    System.out.println("❌ Geçersiz kategori!");
                    continue;
                }
            }

            System.out.println("\n--- SEÇİLEN KATEGORİ ---");
            for (int i = 0; i < secilenMenu.size(); i++) {
                System.out.println((i + 1) + ") " + secilenMenu.get(i));
            }

            System.out.print("Ürün numarası: ");
            int secim = scanner.nextInt();

            if (secim < 1 || secim > secilenMenu.size()) {
                System.out.println("❌ Bu kategoriye ait olmayan ürün!");
            } else {
                siparis.addItem(secilenMenu.get(secim - 1));
            }
        }

        scanner.nextLine();

        System.out.print("\nKupon kodunuz var mı? Yoksa 'yok' yazın: ");
        String kupon = scanner.nextLine();
        if (!kupon.equalsIgnoreCase("yok")) {
            siparis.applyCoupon(kupon);
        }

        System.out.println("\nSipariş onaylandı!");
        siparis.placeOrder();

        scanner.close();
    }
}


