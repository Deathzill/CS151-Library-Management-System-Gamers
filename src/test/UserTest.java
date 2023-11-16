import backend.Book;
import backend.Tables;
import backend.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class UserTest {
    private User user;
    private Tables tables;

    @Before
    public void setUp() {
        tables = new Tables();
        tables.dbAddBook(new Book(Book.BookStatus.AVAILABLE, "Test Book", "Author", 12345, false, null));
        user = new User(1, "Test User", "test@example.com", "password", new Date());
        user.setData(tables);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1, user.getUserID());
        assertEquals("Test User", user.getName());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertNotNull(user.getDateJoined());

        // Testing setters
        user.setUserID(2);
        assertEquals(2, user.getUserID());

        user.setName("New Name");
        assertEquals("New Name", user.getName());

        user.setEmail("newemail@example.com");
        assertEquals("newemail@example.com", user.getEmail());

        user.setPassword("newpassword");
        assertEquals("newpassword", user.getPassword());

        Date newDate = new Date();
        user.setDateJoined(newDate);
        assertEquals(newDate, user.getDateJoined());
    }

    @Test
    public void testSearchByTitle() {
        assertTrue(user.searchByTitle("Test Book"));
        assertFalse(user.searchByTitle("Nonexistent Book"));
    }

    @Test
    public void testSearchByAuthor() {
        assertTrue(user.searchByAuthor("Author"));
        assertFalse(user.searchByAuthor("Nonexistent Author"));
    }

    @Test
    public void testSearchByISBN() {
        assertTrue(user.searchByISBN(12345));
        assertFalse(user.searchByISBN(99999));
    }
}
