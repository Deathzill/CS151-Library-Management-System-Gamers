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


    public void removeBook(Book book){
        this.getData().dbRemoveBook(book); //need this implemented -> Munkh
    }

    public void removeUser(User user){
        this.getData().dbRemoveUser(user); //need this implemented -> Munkh
    }

    // Override toJSON method
    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();  // Call User's toJSON
        jsonObject.put("employmentDate", employmentDate.getTime()); // Store employmentDate as long
        return jsonObject;
    }

    // Add static fromJSON method
    public static Librarian fromJSON(JSONObject jsonObject) {
        User user = User.fromJSON(jsonObject); // Construct User part
        Date employmentDate = new Date(jsonObject.getLong("employmentDate")); // Convert long back to Date

        return new Librarian(user.getUserID(), user.getName(), user.getEmail(), user.getPassword(), user.getDateJoined(), employmentDate);
    }

}
