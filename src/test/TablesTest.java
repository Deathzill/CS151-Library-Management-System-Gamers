import backend.Book;
import backend.Patron;
import backend.Tables;
import backend.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class TablesTest {
    private Tables tables;
    private Book book;
    private User user;

    @Before
    public void setUp() {
        tables = new Tables();
        book = new Book(Book.BookStatus.AVAILABLE, "Test Book", "Author", 12345, false, null);
        user = new Patron(1, "Test User", "test@example.com", "password", new Date());
        tables.dbAddBook(book);
        tables.dbAddUser(user);
    }

    @Test
    public void testAddAndRetrieveBook() {
        assertEquals(book, tables.getBooks().get(book.getISBN()));
    }

    @Test
    public void testAddAndRetrieveUser() {
        assertEquals(user, tables.getUsers().get(user.getUserID()));
    }

    @Test
    public void testSearchBookByTitle() {
        assertTrue(tables.searchBookByTitle("Test Book"));
        assertFalse(tables.searchBookByTitle("Nonexistent Book"));
    }

    // Additional tests for removing books/users, searching by author/ISBN...
}
