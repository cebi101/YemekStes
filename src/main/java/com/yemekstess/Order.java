package com.yemekstess;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private Customer customer;
    private List<MenuItem> items = new ArrayList<>();
    private double totalAmount = 0;
    private double discount = 0;

    public Order(Customer customer) {
        this.customer = customer;
    }

    public void addItem(MenuItem item) {
        items.add(item);
        totalAmount += item.getPrice();
        System.out.println(item.getName() + " sepete eklendi.");
    }

    public void applyCoupon(String code) {
        if (code.equalsIgnoreCase("INDIRIM10")) {
            discount = totalAmount * 0.10;
            System.out.println("%10 indirim uygulandı!");
        } else if (code.equalsIgnoreCase("INDIRIM20")) {
            discount = totalAmount * 0.20;
            System.out.println("%20 indirim uygulandı!");
        } else {
            System.out.println("Geçersiz kupon kodu!");
        }
    }

    public void placeOrder() {

        // 🔴 KRİTİK KURAL
        if (items.isEmpty()) {
            System.out.println("\n⚠️ Sepetiniz boş. Sipariş oluşturulamadı.");
            return;
        }

        System.out.println("\nSipariş başarıyla oluşturuldu.");
        System.out.println("Müşteri: " + customer.getName());
        System.out.println("Adres: " + customer.getAddress());
        System.out.println("Telefon: " + customer.getPhone());
        System.out.println("Şehir: " + customer.getCity());

        double finalPrice = totalAmount - discount;

        System.out.println("\nToplam: " + totalAmount + " TL");
        System.out.println("İndirim: -" + discount + " TL");
        System.out.println("Ödenecek Tutar: " + finalPrice + " TL");
    }

    /* =======================
       GETTER / TEST DESTEK
       ======================= */


    public List<MenuItem> getItems() {
        return items;
    }


    public boolean hasItems() {
        return !items.isEmpty();
    }


    public Customer getCustomer() {
        return customer;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getDiscount() {
        return discount;
    }
}



