package backend;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

public class Book implements Borrowable{

    public enum BookStatus{
        AVAILABLE, CHECKED_OUT
    }

    private BookStatus status;
    private String title;
    private String author;
    private int ISBN;
    private String searchBook = null;
    private boolean isCheckedOut;
    private Date dueDate;

    public Book(int ISBN){
        this.ISBN = ISBN;
    }

    public Book(BookStatus status, String title, String author, int ISBN, boolean isCheckedOut, Date dueDate) {
        this.status = status;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isCheckedOut = isCheckedOut;
        this.dueDate = dueDate;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getSearchBook() {
        return searchBook;
    }

    public void setSearchBook(String searchBook){
        this.searchBook = searchBook;
    }

    public boolean isOverdue() {
        if (dueDate == null) {
            // If there is no due date, the book cannot be overdue
            return false;
        }

        // Get the current date
        Date currentDate = new Date();

        // Check if the current date is after the due date
        return currentDate.after(dueDate);
    }

    public void checkIn(){
        status = BookStatus.AVAILABLE;
        this.dueDate = null;
        isCheckedOut = false;
    }

    public void checkOut(){ //I set the return date as 14 days from the time it was checked out
        Date orderDate = new Date();
        Calendar tempCalendar = Calendar.getInstance();

        tempCalendar.setTime(orderDate);
        tempCalendar.add(Calendar.DAY_OF_YEAR, 14);

        status = BookStatus.CHECKED_OUT;
        this.dueDate = tempCalendar.getTime();
        isCheckedOut = true;
    }

    // Method to convert Book object to a JSON object
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        // Add the status of the book (AVAILABLE or CHECKED_OUT) as a string
        jsonObject.put("status", status.toString());

        // Add book details: title, author, and ISBN
        jsonObject.put("title", title);
        jsonObject.put("author", author);
        jsonObject.put("ISBN", ISBN);

        // Add a boolean flag indicating whether the book is checked out
        jsonObject.put("isCheckedOut", isCheckedOut);

        // Convert the dueDate from Date to long for JSON representation
        // If dueDate is null, store null in JSON
        jsonObject.put("dueDate", dueDate != null ? dueDate.getTime() : null);

        // Return the constructed JSON object
        return jsonObject;
    }


    // Static method to create a Book object from a JSON object
    public static Book fromJSON(JSONObject jsonObject) {
        // Convert the status string back to a BookStatus enum
        BookStatus status = BookStatus.valueOf(jsonObject.getString("status"));

        // Extract book details: title, author, and ISBN
        String title = jsonObject.getString("title");
        String author = jsonObject.getString("author");
        int ISBN = jsonObject.getInt("ISBN");

        // Extract the isCheckedOut flag
        boolean isCheckedOut = jsonObject.getBoolean("isCheckedOut");

        // Convert the dueDate from long format back to Date object
        // If dueDate is null in JSON, set it to null in the Book object
        Date dueDate = jsonObject.isNull("dueDate") ? null : new Date(jsonObject.getLong("dueDate"));

        // Create and return a new Book object using the extracted data
        return new Book(status, title, author, ISBN, isCheckedOut, dueDate);
    }


}
