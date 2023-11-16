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

    @Test
    public void testReturnBook() {
        patron.borrowBook(tables.getBooks(), 12345);
        assertTrue(book1.isCheckedOut());
        patron.returnBook(tables.getBooks());
        assertFalse(book1.isCheckedOut());
    }

    @Test
    public void testGetOverdueBooks() {
        // Set due date of book1 in the past to simulate overdue
        book1.checkOut();
        book1.setDueDate(new Date(System.currentTimeMillis() - 100000)); // Set to past date
        patron.borrowBook(tables.getBooks(), 12345);

        List<String> overdueBooks = patron.getOverdueBooks(tables.getBooks());
        assertTrue(overdueBooks.contains("Test Book 1"));
    }
}
