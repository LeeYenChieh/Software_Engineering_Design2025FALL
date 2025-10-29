import java.util.ArrayList;

public interface ILibrarySystem {
    
    public void checkout(ArrayList<Book> books, Borrower borrower);

    public void returnBook(Book book);

    public void addBook(Book book);

    public void removeBook(Book book);

    public ArrayList<Book> getBookByAuthor(String author);

    public ArrayList<Book> getBookBySubject(String subject);

    public ArrayList<Book> getBookByBorrower(Borrower borrower);

    public Borrower getLastBorrowerByBook(Book book);
}
