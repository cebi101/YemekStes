package com.yemekstess;

import com.yemekstess.persistence.csv.OrderCsvStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // CSV için sipariş listesi
        List<Order> orders = new ArrayList<>();

        // Program kapanırken CSV'ye kaydet
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            OrderCsvStore.saveOrders(orders);
        }));

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

        // Siparişi CSV listesine ekle
        orders.add(siparis);

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
        drinks.add(new DrinkItem("Kola", 20, "Büyük"));
        drinks.add(new DrinkItem("Ayran", 15, "Büyük"));
        drinks.add(new DrinkItem("Ice Tea", 15, "Küçük"));

        List<MenuItem> desserts = new ArrayList<>();
        desserts.add(new MenuItem("Sufle", 45));
        desserts.add(new MenuItem("Cheesecake", 55));

        while (true) {
            System.out.println("\nKategori seçiniz:");
            System.out.println("1 - Yemekler");
            System.out.println("2 - Atıştırmalıklar");
            System.out.println("3 - İçecekler");
            System.out.println("4 - Tatlılar");
            System.out.println("0 - Siparişi Bitir");

            System.out.print("Seçim: ");
            int kategori = scanner.nextInt();

            if (kategori == 0) {
                break;
            }

            List<MenuItem> secilenMenu;
            String kategoriAdi;

            switch (kategori) {
                case 1 -> {
                    secilenMenu = foods;
                    kategoriAdi = "YEMEKLER";
                }
                case 2 -> {
                    secilenMenu = snacks;
                    kategoriAdi = "ATIŞTIRMALIKLAR";
                }
                case 3 -> {
                    secilenMenu = drinks;
                    kategoriAdi = "İÇECEKLER";
                }
                case 4 -> {
                    secilenMenu = desserts;
                    kategoriAdi = "TATLILAR";
                }
                default -> {
                    System.out.println("❌ Geçersiz kategori!");
                    continue;
                }
            }

            System.out.println("\n--- SEÇİLEN KATEGORİ: " + kategoriAdi + " ---");
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

        scanner.nextLine(); // buffer temizliği

        // ✅ SADECE SEPET DOLUYSA KUPON SOR
        if (siparis.hasItems()) {
            System.out.print("\nKupon kodunuz var mı? Yoksa 'yok' yazın: ");
            String kupon = scanner.nextLine();
            if (!kupon.equalsIgnoreCase("yok")) {
                siparis.applyCoupon(kupon);
            }
        }

        siparis.placeOrder();

        scanner.close();
    }
}



