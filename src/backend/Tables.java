package backend;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Tables {
    private Map<Integer, Book> books; // Using ISBN as the key for the backend.Book map
    private Map<Integer, User> users; // Storing all users
    private Map<Integer, Patron> patrons; // Specifically for Patrons
    private Map<Integer, Librarian> librarians; // Specifically for Librarians

    public Tables() {
        books = new HashMap<>();
        users = new HashMap<>();
        patrons = new HashMap<>();
        librarians = new HashMap<>();
    }

    public String getUserType(){ //return what type of User it is
        return String.valueOf(users.getClass());
    }

    public User getUser(int userID){
        return users.get(userID);
    }

    public Map<Integer, Book> getBooks() {
        return books;
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    // Method to add a backend.Book
    public void dbAddBook(Book book) {
        books.put(book.getISBN(), book);
    }

    // Method to remove a backend.Book by ISBN
    public boolean dbRemoveBook(int isbn) {
        Book book = books.get(isbn);
        if (book != null) {
            books.remove(isbn);
            return true; // Book successfully removed
        }
        return false; // Book not removed (doesn't exist or is checked out)
    }


    // Method to add a backend.User
    public void dbAddUser(User user) {
        users.put(user.getUserID(), user);
    }

    // Method to remove a backend.User by userID
    public void dbRemoveUser(User user) {
        users.remove(user.getUserID());
    }

    // Search for a backend.Book by Title
    public boolean searchBookByTitle(String title) {
        for (Book book : books.values()) {
            if (book.getTitle().equals(title)) {
                return true; // backend.Book found
            }
        }
        return false; // backend.Book not found
    }

    // Search for a backend.Book by Author
    public boolean searchBookByAuthor(String author) {
        for (Book book : books.values()) {
            if (book.getAuthor().equals(author)) {
                return true; // backend.Book found
            }
        }
        return false; // backend.Book not found
    }

    // Search for a backend.Book by ISBN
    public boolean searchBookByISBN(int ISBN) {
        return books.containsKey(ISBN); // Returns true if the ISBN exists in the map, false otherwise
    }

    public boolean isCheckedOut(int ISBN){
        return books.get(ISBN).isCheckedOut();
    }


    // Method to check out a book by ISBN
    public boolean checkOutBook(Book ISBN) {
        Book book = books.get(ISBN.getISBN());
        if (book != null && book.getStatus() == Book.BookStatus.AVAILABLE) {
            book.checkOut();
            return true;
        }
        else return false;
    }

    // Method to check in a book by ISBN
    public boolean checkInBook(Book ISBN) {
        Book book = books.get(ISBN.getISBN());
        if (book != null && book.getStatus() == Book.BookStatus.CHECKED_OUT) {
            book.checkIn();
            return true;
        }
        else return false;
    }

    // Method to save data to JSON file
    public void saveToJSON(String filename) {
        JSONObject data = new JSONObject();
        JSONArray usersArray = new JSONArray();
        JSONArray booksArray = new JSONArray();

        for (User user : users.values()) {
            JSONObject userJson = user.toJSON();
            userJson.put("userType", user.getClass().getSimpleName());
            usersArray.put(userJson);
        }

        for (Book book : books.values()) {
            booksArray.put(book.toJSON());
        }

        data.put("users", usersArray);
        data.put("books", booksArray);

        try (FileWriter file = new FileWriter(filename)) {
            file.write(data.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load data from JSON file
    public void loadFromJSON(String filename) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filename)));
            if (content.trim().isEmpty()) {
                throw new JSONException("File is empty");
            }

            JSONObject data = new JSONObject(content);
            JSONArray usersArray = data.getJSONArray("users");
            JSONArray booksArray = data.getJSONArray("books");

            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userJson = usersArray.getJSONObject(i);
                String userType = userJson.getString("userType");
                User user;

                if ("Patron".equals(userType)) {
                    user = Patron.fromJSON(userJson);
                    patrons.put(user.getUserID(), (Patron) user);
                } else if ("Librarian".equals(userType)) {
                    user = Librarian.fromJSON(userJson);
                    librarians.put(user.getUserID(), (Librarian) user);
                } else {
                    user = User.fromJSON(userJson);
                }

                users.put(user.getUserID(), user);
            }

            for (int i = 0; i < booksArray.length(); i++) {
                JSONObject bookJson = booksArray.getJSONObject(i);
                Book book = Book.fromJSON(bookJson);
                books.put(book.getISBN(), book);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
