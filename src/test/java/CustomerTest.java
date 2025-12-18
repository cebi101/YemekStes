import com.yemekstess.Customer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void customerCreationTest() {
        Customer customer =
                new Customer("c1", "123", "Veli", "Ankara", "5555", "X");

        assertNotNull(customer);
        assertEquals("Ankara", customer.getCity());
    }
}
