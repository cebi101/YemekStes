import com.yemekstess.Customer;
import com.yemekstess.DrinkItem;
import com.yemekstess.MenuItem;
import com.yemekstess.Order;
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
}

