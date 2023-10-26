import java.util.Date;
import java.util.List;

public class User implements Searchable{
    private int userID;
    private String name;
    private String email;
    private String password;
    private Date dateJoined;
    private boolean logged;
    private Tables data;

    public User(int userID, String name, String email, String password, Date dateJoined) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.dateJoined = dateJoined;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
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

    public Tables getData(){
        return data;
    }

    public void setData(Tables data){
        this.data = data;
    }

    /*
    -------------Left this for future reference if needed
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

    }*/

    public boolean searchByTitle(String title) {
        return data.searchBookByTitle(title);
    }

    public boolean searchByAuthor(String author) {
        return data.searchBookByAuthor(author);
    }

    public boolean searchByISBN(String ISBN){
        return data.searchBookByISBN(ISBN);
    }

}
