package backend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Patron extends User{
    private List<Integer> borrowedBooks;
    private static final int maxBookLimit = 5;
    private List<String> overdueBooks;

    public Patron(int userID, String name, String email, String password, Date dateJoined) {
        super(userID, name, email, password, dateJoined);
        borrowedBooks = new ArrayList<Integer>();
        overdueBooks = new ArrayList<String>();
    }

    public void borrowBook(Map<Integer, Book> books, int isbn){
        //getData().checkOutBook(book); //Use the backend.Book.CheckOut() method for this in Table
        //for(int i = 0; i < isbn.size(); i++){
        books.get(isbn).checkOut();
        borrowedBooks.add(isbn);
        //}
    }

    public String returnBook(Map<Integer, Book> books, int isbn) {
        // Check if the book is actually borrowed
        if (borrowedBooks.contains(isbn)) {
            Book book = books.get(isbn);
            if (book != null) {
                book.checkIn(); // Mark the book as returned
                borrowedBooks.remove(Integer.valueOf(isbn)); // Remove the book from the borrowed list

                return book.getTitle(); // Return the title of the returned book
            }
        }
        return null; // Return null if the book was not found or not borrowed
    }

    public List<String> getOverdueBooks(Map<Integer, Book> books){

        Date currentDate = new Date();
        overdueBooks.clear();

        for(int i = 0; i < borrowedBooks.size(); i++){
            if(currentDate.compareTo(books.get(borrowedBooks.get(i)).getDueDate()) > 0){
                overdueBooks.add(books.get(borrowedBooks.get(i)).getTitle());
            }
        }

        return overdueBooks;
    }

    public List<Book> getCheckedOutBooks(Map<Integer, Book> books) {
        List<Book> checkedOutBooks = new ArrayList<>();
        for (Integer isbn : borrowedBooks) {
            Book book = books.get(isbn);
            if (book != null) {
                checkedOutBooks.add(book);
            }
        }
        return checkedOutBooks;
    }

    public boolean hasOverdueBooks(){

        if(overdueBooks.isEmpty()){
            return false;
        }

        return true;
    }

    // Override toJSON method from User class for Patron
    @Override
    public JSONObject toJSON() {
        // Call toJSON from the User class to get basic user details
        JSONObject jsonObject = super.toJSON();

        // Add Patron-specific details: list of borrowed and overdue books
        // Convert the list of borrowed books to a JSON array
        jsonObject.put("borrowedBooks", new JSONArray(borrowedBooks));

        // Convert the list of overdue books to a JSON array
        jsonObject.put("overdueBooks", new JSONArray(overdueBooks));

        // Return the extended JSON object with Patron details
        return jsonObject;
    }


    // Static method to create a Patron object from a JSON object
    public static Patron fromJSON(JSONObject jsonObject) {
        // First, construct the User part of the Patron using User.fromJSON
        User user = User.fromJSON(jsonObject);

        // Create a new Patron using the User part
        Patron patron = new Patron(user.getUserID(), user.getName(), user.getEmail(), user.getPassword(), user.getDateJoined());

        // Extract and process the array of borrowedBooks IDs
        JSONArray borrowedBooksArray = jsonObject.getJSONArray("borrowedBooks");
        for (int i = 0; i < borrowedBooksArray.length(); i++) {
            patron.borrowedBooks.add(borrowedBooksArray.getInt(i)); // Add each borrowed book ID to Patron's list
        }

        // Extract and process the array of overdueBooks titles
        JSONArray overdueBooksArray = jsonObject.getJSONArray("overdueBooks");
        for (int i = 0; i < overdueBooksArray.length(); i++) {
            patron.overdueBooks.add(overdueBooksArray.getString(i)); // Add each overdue book title to Patron's list
        }

        // Return the fully constructed Patron object
        return patron;
    }

}
