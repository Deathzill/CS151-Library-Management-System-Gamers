package backend;

import org.json.JSONObject;

import java.util.Date;

public class Librarian extends User{
    private Date employmentDate;

    public Librarian(int userID, String name, String email, String password, Date dateJoined, Date employmentDate) {
        super(userID, name, email, password, dateJoined);
        this.employmentDate = employmentDate;
    }

    public void addBook(Book book){
        this.getData().dbAddBook(book); // Using the getter method to access data
    }


    public void removeBook(int book){
        this.getData().dbRemoveBook(book); //need this implemented -> Munkh
    }

    public void removeUser(User user){
        this.getData().dbRemoveUser(user); //need this implemented -> Munkh
    }

    // Override toJSON method from User class for Librarian
    @Override
    public JSONObject toJSON() {
        // Call toJSON from the User class to get basic user details
        JSONObject jsonObject = super.toJSON();

        // Add Librarian-specific detail: employmentDate
        // Convert the employmentDate from Date to long for JSON representation
        jsonObject.put("employmentDate", employmentDate.getTime());

        // Return the extended JSON object with Librarian details
        return jsonObject;
    }


    // Static method to create a Librarian object from a JSON object
    public static Librarian fromJSON(JSONObject jsonObject) {
        // First, construct the User part of the Librarian using User.fromJSON
        User user = User.fromJSON(jsonObject);

        // Convert the employmentDate from long format back to Date object
        Date employmentDate = new Date(jsonObject.getLong("employmentDate"));

        // Create and return a new Librarian object using the User part and the employmentDate
        return new Librarian(user.getUserID(), user.getName(), user.getEmail(), user.getPassword(), user.getDateJoined(), employmentDate);
    }


}
