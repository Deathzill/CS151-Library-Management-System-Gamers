import backend.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class PatronTest {
    private Patron patron;
    private Tables tables;
    private Book book1, book2;

    @Before
    public void setUp() {
        tables = new Tables();
        book1 = new Book(Book.BookStatus.AVAILABLE, "Test Book 1", "Author1", 12345, false, null);
        book2 = new Book(Book.BookStatus.AVAILABLE, "Test Book 2", "Author2", 12346, false, null);
        tables.dbAddBook(book1);
        tables.dbAddBook(book2);
        patron = new Patron(1, "Test Patron", "patron@example.com", "password", new Date());
        patron.setData(tables);
    }

    @Test
    public void testBorrowBook() {
        patron.borrowBook(tables.getBooks(), 12345);
        assertTrue(book1.isCheckedOut());
        assertFalse(book2.isCheckedOut());
    }
}
