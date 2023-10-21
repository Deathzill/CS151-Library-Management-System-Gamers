import java.util.Date;
import java.util.List;

public class User implements Searchable{
    private String userID;
    private String name;
    private String email;
    private String password;
    private Date dateJoined;
    private boolean logged;
    private Database data; //I feel that we should create the database in main or something and then put the database object
                           //into the User object for us to do search operations. Either we do it this way or we can pass the DataBase
                           //object to the search method everytime we use it. That is up to you

    public User(String userID, String name, String email, String password, Date dateJoined) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.dateJoined = dateJoined;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public DataBase getData(){
        return data;
    }

    public void setData(DataBase data){
        this.data = data;
    }

    public boolean login(){
        //logged = true;

        //Not sure what we're doing here. I think we're going to be connecting this to java swing.
        //If that's the case I think we should first figure out java swing and then come back to this method
        //as we may not even need it

        return true; //temp return
    }

    public void logout(){
        //logged = false;

        //Same case here as the login() method

    }

    public void updateProfile(){
        //???

        //This method had no parameters so im a bit confused but I'm guessing that it was a typo.
        //Either way, will be a simple method to implement later on.

    }

    public Book searchByTitle(String title) { //change. Refer to Searchable interface

        data.searchBookByTitle(title);

        return null;
    }
}
