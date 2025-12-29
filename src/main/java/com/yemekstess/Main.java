package com.yemekstess;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printWelcome();

        Customer musteri = createCustomer(scanner);
        Restaurant restoran = createRestaurant();

        restoran.showMenu();

        Order siparis = new Order(musteri);
        takeOrder(scanner, restoran, siparis);
        applyCoupon(scanner, siparis);

        finalizeOrder(musteri, siparis);

        scanner.close();
    }

    private static void printWelcome() {
        System.out.println("======= YEMEK SİPARİŞ SİTESİNE HOŞ GELDİNİZ =======\n");
    }

    private static Customer createCustomer(Scanner scanner) {
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

        return new Customer(username, password, name, city, phone, address);
    }

    private static Restaurant createRestaurant() {
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

        return restoran;
    }

    private static void takeOrder(Scanner scanner, Restaurant restoran, Order siparis) {
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
        scanner.nextLine(); // buffer temizleme
    }

    private static void applyCoupon(Scanner scanner, Order siparis) {
        System.out.print("\nKupon kodunuz var mı? Yoksa 'yok' yazın: ");
        String kupon = scanner.nextLine();

        if (!kupon.equalsIgnoreCase("yok")) {
            siparis.applyCoupon(kupon);
        }
    }

    private static void finalizeOrder(Customer musteri, Order siparis) {
        System.out.println("\nSipariş onaylandı!");
        System.out.println("Kullanıcı adı: " + musteri.getUsername());
        siparis.placeOrder();
    }
}

