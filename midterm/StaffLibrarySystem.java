import java.util.ArrayList;

public class StaffLibrarySystem implements ILibrarySystem {
    public RealLibrarySystem realSystem;
    public Staff staff;

    public StaffLibrarySystem(RealLibrarySystem realSystem, Staff staff){
        this.realSystem = realSystem;
        this.staff = staff;
    }

    public void checkout(ArrayList<Book> books, Borrower borrower){
        this.realSystem.checkout(books, borrower);
    }

    public void returnBook(Book book){
        this.realSystem.returnBook(book);
    }

    public void addBook(Book book){
        this.realSystem.addBook(book);
    }

    public void removeBook(Book book){
        this.realSystem.removeBook(book);
    }

    public ArrayList<Book> getBookByAuthor(String author){
        return this.realSystem.getBookByAuthor(author);
    }

    public ArrayList<Book> getBookBySubject(String subject){
        return this.realSystem.getBookBySubject(subject);
    }

    public ArrayList<Book> getBookByBorrower(Borrower borrower){
        return this.realSystem.getBookByBorrower(borrower);
    }

    public Borrower getLastBorrowerByBook(Book book){
        return this.realSystem.getLastBorrowerByBook(book);
    }
}
