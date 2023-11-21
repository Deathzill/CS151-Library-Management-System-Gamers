import backend.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class LibrarianTest {
    private Librarian librarian;
    private Tables tables;
    private Book book;
    private User user;

    @Before
    public void setUp() {
        tables = new Tables();
        book = new Book(Book.BookStatus.AVAILABLE, "Librarian Test Book", "Author", 78910, false, null);
        user = new Patron(2, "Test Patron", "patron@example.com", "password", new Date());
        librarian = new Librarian(1, "Test Librarian", "librarian@example.com", "password", new Date(), new Date());
        librarian.setData(tables);
    }

    @Test
    public void testAddBook() {
        librarian.addBook(book);
        assertTrue(tables.getBooks().containsValue(book));
    }

    @Test
    public void testRemoveBook() {
        librarian.addBook(book);
//        librarian.removeBook(book);
//        assertFalse(tables.getBooks().containsValue(book));
    }
}
