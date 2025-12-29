
package com.yemekstess;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("======= YEMEK SİPARİŞ SİTESİNE HOŞ GELDİNİZ =======\n");

        // Kullanıcı bilgileri
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

        // Restoran ve menü
        Restaurant restoran = new Restaurant("YemekSitesi");

        // Yemekler
        restoran.addFood(new MenuItem("Burger", 80));
        restoran.addFood(new MenuItem("Pizza", 100));
        restoran.addFood(new MenuItem("Lahmacun", 65));
        restoran.addFood(new MenuItem("Döner", 120));
        restoran.addFood(new MenuItem("Kebap", 150));
        restoran.addFood(new MenuItem("Mantı", 90));

        // Atıştırmalıklar
        restoran.addSnack(new MenuItem("Patates Kızartması", 35));
        restoran.addSnack(new MenuItem("Soğan Halkası", 30));
        restoran.addSnack(new MenuItem("Mozzarella Stick", 45));
        restoran.addSnack(new MenuItem("Sigara Böreği", 40));

        // İçecekler
        restoran.addDrink(new DrinkItem("Kola", 15, "Küçük"));
        restoran.addDrink(new DrinkItem("Kola", 18, "Orta"));
        restoran.addDrink(new DrinkItem("Kola", 22, "Büyük"));

        restoran.addDrink(new DrinkItem("Ayran", 12, "Küçük"));
        restoran.addDrink(new DrinkItem("Ayran", 15, "Orta"));
        restoran.addDrink(new DrinkItem("Ayran", 18, "Büyük"));

        restoran.addDrink(new DrinkItem("Ice Tea", 20, "Küçük"));
        restoran.addDrink(new DrinkItem("Ice Tea", 24, "Orta"));
        restoran.addDrink(new DrinkItem("Ice Tea", 28, "Büyük"));

        // Tatlılar
        restoran.addDessert(new MenuItem("Sufle", 45));
        restoran.addDessert(new MenuItem("Magnolia", 50));
        restoran.addDessert(new MenuItem("Profiterol", 48));
        restoran.addDessert(new MenuItem("Cheesecake", 55));
        restoran.addDessert(new MenuItem("Baklava", 60));

        Order siparis = new Order(musteri);

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

            if (kategori == 0) {
                devam = false;
                break;
            }

            restoran.showMenu(); // Mevcut menüyü gösteriyoruz

            System.out.print("Eklemek istediğiniz ürün numarası: ");
            int urunNo = scanner.nextInt();

            List<MenuItem> menu = restoran.getFullMenu();

            if (urunNo >= 1 && urunNo <= menu.size()) {
                siparis.addItem(menu.get(urunNo - 1));
            } else {
                System.out.println("Geçersiz ürün numarası!");
            }
        }

        scanner.nextLine(); // buffer temizliği

        System.out.print("\nKupon kodunuz var mı? Yoksa 'yok' yazın: ");
        String kupon = scanner.nextLine();

        if (!kupon.equalsIgnoreCase("yok")) {
            siparis.applyCoupon(kupon);
        }

        System.out.println("\nSipariş onaylandı!");
        System.out.println("Kullanıcı adı: " + musteri.getUsername());
        siparis.placeOrder();

        scanner.close();
    }
}


