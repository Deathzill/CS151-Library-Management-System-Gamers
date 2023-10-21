package librarysystem;

import java.util.Date;
import java.util.List;

public class Patron extends User{
    private String libraryCardID;
    private List<Book> borrowedBooks;
    private static final int maxBookLimit = 5;
    private List<Book> overdueBooks;

    public Patron(String userID, String name, String email, String password, Date dateJoined, String libraryCardID) {
        super(userID, name, email, password, dateJoined);
        this.libraryCardID = libraryCardID;
    }

    public void borrowBook(int bookIndex){

    }

    //Will move on from here after we figure out the issues presented in the Searchable interface

}
