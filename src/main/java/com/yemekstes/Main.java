package com.yemekstes;

import com.yemekstes.persistence.csv.OrderCsvStore;

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

        // Menü Restaurant sınıfında kurulur
        Restaurant restoran = Restaurant.createDefault();

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
                    secilenMenu = restoran.getFoods();
                    kategoriAdi = "YEMEKLER";
                }
                case 2 -> {
                    secilenMenu = restoran.getSnacks();
                    kategoriAdi = "ATIŞTIRMALIKLAR";
                }
                case 3 -> {
                    secilenMenu = restoran.getDrinks();
                    kategoriAdi = "İÇECEKLER";
                }
                case 4 -> {
                    secilenMenu = restoran.getDesserts();
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



