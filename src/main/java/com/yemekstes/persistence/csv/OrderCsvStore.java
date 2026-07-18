package com.yemekstes.persistence.csv;

import com.yemekstes.Order;

import java.io.*;
import java.nio.file.*;
import java.util.List;

public class OrderCsvStore {

    private static final String FILE_PATH = "data/orders.csv";

    public static void saveOrders(List<Order> orders) {
        try {
            Path path = Paths.get(FILE_PATH);
            Files.createDirectories(path.getParent());

            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                writer.write("customer,totalAmount,discount,finalAmount");
                writer.newLine();

                for (Order order : orders) {
                    double finalAmount = order.getTotalAmount() - order.getDiscount();

                    writer.write(
                            order.getCustomer().getName() + "," +
                                    order.getTotalAmount() + "," +
                                    order.getDiscount() + "," +
                                    finalAmount
                    );
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("CSV yazma hatası: " + e.getMessage());
        }
    }
}


