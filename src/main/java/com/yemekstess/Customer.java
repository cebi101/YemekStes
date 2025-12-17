package com.yemekstess;
// Customer entity class

public class Customer extends User {
    private String name;
    private String city;
    private String phone;
    private String address;

    public Customer(String username, String password, String name, String city, String phone, String address) {
        super(username, password); // User sınıfından gelenler
        this.name = name;
        this.city = city;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
