import com.yemekstes.Customer;
import com.yemekstes.DrinkItem;
import com.yemekstes.MenuItem;
import com.yemekstes.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class OrderTest {

    @Test
    void emptyOrder_cannotBePlaced() {
        Customer customer = new Customer(
                "testUser",
                "1234",
                "Ali",
                "Istanbul",
                "05555555555",
                "Kadikoy"
        );

        Order order = new Order(customer);

        // Sepet boşken sipariş verilmemeli
        order.placeOrder();

        assertEquals(0, order.getTotalAmount());
        assertTrue(order.getItems().isEmpty());
    }

    @Test
    void orderTotalAmount_isCalculatedCorrectly() {
        Customer customer = new Customer(
                "testUser",
                "1234",
                "Ali",
                "Istanbul",
                "05555555555",
                "Kadikoy"
        );

        Order order = new Order(customer);

        MenuItem item1 = new MenuItem("Burger", 120);
        MenuItem item2 = new DrinkItem("Cola", 30, "Medium");

        order.addItem(item1);
        order.addItem(item2);

        order.placeOrder();

        assertEquals(150, order.getTotalAmount());
    }

    @Test
    void couponDiscount_reflectsItemsAddedAfterCoupon() {
        Customer customer = new Customer(
                "testUser",
                "1234",
                "Ali",
                "Istanbul",
                "05555555555",
                "Kadikoy"
        );

        Order order = new Order(customer);

        order.addItem(new MenuItem("Burger", 100));
        order.applyCoupon("INDIRIM10");

        // Kupondan SONRA eklenen ürün de indirime dahil olmalı
        order.addItem(new MenuItem("Pizza", 100));

        assertEquals(200, order.getTotalAmount());
        assertEquals(20, order.getDiscount());
    }
}

