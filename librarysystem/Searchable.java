package librarysystem;

import java.util.List;

public interface Searchable {
    //These should be return bool, int for index of book, or book itself
    //Int would be the best course of action in my opinion
    public List<Book> searchByTitle(String title);
    public List<Book> searchByAuthor(String author);
    public Book searchByISBN(String ISBN);
}
