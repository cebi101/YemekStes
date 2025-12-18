import com.yemekstess.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void userCreationTest() {
        User user = new User("u1", "123");

        assertNotNull(user);
        assertEquals("u1", user.getUsername());
    }
}
