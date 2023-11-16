import backend.Book;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {
    private Book book;

    @Before
    public void setUp() {
        book = new Book(Book.BookStatus.AVAILABLE, "Test Title", "Test Author", 123456, false, null);
    }

    @Test
    public void testCheckOut() {
        book.checkOut();
        assertTrue(book.isCheckedOut());
        assertNotNull(book.getDueDate());
        assertEquals(Book.BookStatus.CHECKED_OUT, book.getStatus());
    }

    @Test
    public void testCheckIn() {
        book.checkOut();
        book.checkIn();
        assertFalse(book.isCheckedOut());
        assertNull(book.getDueDate());
        assertEquals(Book.BookStatus.AVAILABLE, book.getStatus());
    }

    // Additional tests for getters and setters...
}
