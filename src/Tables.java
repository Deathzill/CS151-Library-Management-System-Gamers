import java.util.HashMap;
import java.util.Map;

public class Tables {
    private Map<Integer, Book> books; // Using ISBN as the key for the Book map
    private Map<Integer, User> users; // Using userID as the key for the User map

    public Tables() {
        books = new HashMap<>();
        users = new HashMap<>();
    }

    // Method to add a Book
    public void dbAddBook(Book book) {
        books.put(book.getISBN(), book);
    }

    // Method to remove a Book by ISBN
    public void dbRemoveBook(Book book) {
        books.remove(book.getISBN());
    }

    // Method to add a User
    public void dbAddUser(User user) {
        users.put(user.getUserID(), user);
    }

    // Method to remove a User by userID
    public void dbRemoveUser(User user) {
        users.remove(user.getUserID());
    }

    // Search for a Book by Title
    public boolean searchBookByTitle(String title) {
        for (Book book : books.values()) {
            if (book.getTitle().equals(title)) {
                return true; // Book found
            }
        }
        return false; // Book not found
    }

    // Search for a Book by Author
    public boolean searchBookByAuthor(String author) {
        for (Book book : books.values()) {
            if (book.getAuthor().equals(author)) {
                return true; // Book found
            }
        }
        return false; // Book not found
    }

    // Search for a Book by ISBN
    public boolean searchBookByISBN(int ISBN) {
        return books.containsKey(ISBN); // Returns true if the ISBN exists in the map, false otherwise
    }


    // Method to check out a book by ISBN
    public void checkOutBook(int ISBN) {
        Book book = books.get(ISBN);
        if (book != null && book.getStatus() == Book.BookStatus.AVAILABLE) {
            book.checkOut();
        }
    }

    // Method to check in a book by ISBN
    public void checkInBook(int ISBN) {
        Book book = books.get(ISBN);
        if (book != null && book.getStatus() == Book.BookStatus.CHECKED_OUT) {
            book.checkIn();
        }
    }
}
