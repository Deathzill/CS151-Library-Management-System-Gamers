import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patron extends User{
    private List<Book> borrowedBooks;
    private static final int maxBookLimit = 5;
    private List<Book> overdueBooks;

    public Patron(int userID, String name, String email, String password, Date dateJoined) {
        super(userID, name, email, password, dateJoined);
        borrowedBooks = new ArrayList<>();
        overdueBooks = new ArrayList<Book>();
    }

    public void borrowBook(int ISBN){
        getData().checkOutBook(ISBN); //Use the Book.CheckOut() method for this in Table
    }

    public void returnBook(int ISBN){
        getData().checkInBook(ISBN); //Use the Book.CheckIn() method for this in Table
    }

    public List<Book> getOverdueBooks(){

        Date currentDate = new Date();

        for(int i = 0; i < borrowedBooks.size(); i++){
            if(currentDate.compareTo(borrowedBooks.get(i).getDueDate()) > 0){
                overdueBooks.add(borrowedBooks.get(i));
            }
        }

        return overdueBooks;
    }

    public boolean hasOverdueBooks(){

        if(overdueBooks.isEmpty()){
            return false;
        }

        return true;
    }

}
