import java.util.Date;

public class Librarian extends User{
    private Date employmentDate;

    public Librarian(int userID, String name, String email, String password, Date dateJoined, Date employmentDate) {
        super(userID, name, email, password, dateJoined);
        this.employmentDate = employmentDate;
    }

    public void addBook(Book book){
        data.dbAddBook(book); //need this implemented -> Munkh
    }

    public void removeBook(Book book){
        data.dbRemoveBook(book); //need this implemented -> Munkh
    }

    public void removeUser(User user){
        data.dbRemoveUser(user); //need this implemented -> Munkh
    }

}
