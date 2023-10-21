package librarysystem;

import java.util.Date;

public class Librarian extends User{
    private String librarianID;
    private Date employmentDate;

    public Librarian(String userID, String name, String email, String password, Date dateJoined, String librarianID, Date employmentDate) {
        super(userID, name, email, password, dateJoined);
        this.librarianID = librarianID;
        this.employmentDate = employmentDate;
    }

    //Will move on from here after we figure out the issues presented in the Searchable interface
    //Dont think we'll need the registerNewUser(User user) and removeUser(User user) methods as they don't really make sense

}
