package com.yemekstess;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("======= YEMEK SİPARİŞ SİTESİNE HOŞ GELDİNİZ =======\n");

        // ==============================
        //     GİRİŞ BİLGİLERİ ALINIYOR
        // ==============================

        System.out.print("Kullanıcı adı: ");
        String username = scanner.nextLine();

        System.out.print("Şifre: ");
        String password = scanner.nextLine();

        // ==============================
        //   MÜŞTERİ BİLGİLERİ ALINIYOR
        // ==============================

        System.out.print("Adınız: ");
        String name = scanner.nextLine();

        System.out.print("Şehriniz: ");
        String city = scanner.nextLine();

        System.out.print("Telefon Numaranız: ");
        String phone = scanner.nextLine();

        System.out.print("Adresiniz: ");
        String address = scanner.nextLine();

        // Customer, artık User'dan türediği için hem login hem müşteri bilgilerini alıyor
        Customer musteri = new Customer(username, password, name, city, phone, address);

        Restaurant restoran = new Restaurant("YemekSitesi");

        restoran.addFood(new MenuItem("Burger", 80));
        restoran.addFood(new MenuItem("Pizza", 100));
        restoran.addFood(new MenuItem("Lahmacun", 65));
        restoran.addFood(new MenuItem("Döner", 120));

        restoran.addSnack(new MenuItem("Patates Kızartması", 35));
        restoran.addSnack(new MenuItem("Soğan Halkası", 30));
        restoran.addSnack(new MenuItem("Mozzarella Stick", 45));

        restoran.addDrink(new DrinkItem("Kola", 15, "Küçük"));
        restoran.addDrink(new DrinkItem("Kola", 18, "Orta"));
        restoran.addDrink(new DrinkItem("Kola", 22, "Büyük"));

        restoran.addDrink(new DrinkItem("Ayran", 12, "Küçük"));
        restoran.addDrink(new DrinkItem("Ayran", 15, "Orta"));
        restoran.addDrink(new DrinkItem("Ayran", 18, "Büyük"));

        restoran.addDrink(new DrinkItem("Ice Tea", 20, "Küçük"));
        restoran.addDrink(new DrinkItem("Ice Tea", 24, "Orta"));
        restoran.addDrink(new DrinkItem("Ice Tea", 28, "Büyük"));

        restoran.addDessert(new MenuItem("Sufle", 45));
        restoran.addDessert(new MenuItem("Magnolia", 50));
        restoran.addDessert(new MenuItem("Profiterol", 48));
        restoran.addDessert(new MenuItem("Cheesecake", 55));

        restoran.showMenu();

        Order siparis = new Order(musteri);

        System.out.println("\nSepete ürün eklemek için ürün numarasını girin.");
        System.out.println("Bitirmek için -1 yazın.");

        int secim = 0;

        while (secim != -1) {
            System.out.print("Seçim: ");
            secim = scanner.nextInt();

            if (secim == -1) break;

            if (secim >= 1 && secim <= restoran.getFullMenu().size()) {
                MenuItem item = restoran.getFullMenu().get(secim - 1);
                siparis.addItem(item);
            } else {
                System.out.println("Geçersiz seçim!");
            }
        }

        scanner.nextLine(); // Boş satır temizliği

        System.out.print("\nKupon kodunuz var mı? Yoksa 'yok' yazın: ");
        String kupon = scanner.nextLine();

        if (!kupon.equalsIgnoreCase("yok")) {
            siparis.applyCoupon(kupon);
        }

        System.out.println("\nSipariş onaylandı!");
        System.out.println("Kullanıcı adı: " + musteri.getUsername()); // User bilgisi geldi!
        siparis.placeOrder();

        scanner.close();
    }
}
