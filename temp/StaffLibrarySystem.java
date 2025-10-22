import java.util.ArrayList;

public class StaffLibrarySystem implements ILibrarySystem{
    public RealLibrarySystem realLibrarySystem;
    public Staff staff;

    public StaffLibrarySystem(RealLibrarySystem r, Staff staff){
        this.realLibrarySystem = r;
        this.staff = staff;
    }

    public void checkoutBook(ArrayList<Book> books, Borrower borrower){
        this.realLibrarySystem.checkoutBook(books, borrower);
    }

    public void returnBook(Book book){
        this.realLibrarySystem.returnBook(book);
    }

    public void addBook(Book book){
        this.realLibrarySystem.addBook(book);
    }

    public void removeBook(Book book){
        this.realLibrarySystem.removeBook(book);
    }

    public ArrayList<Book> getBooksByAuthor(String author){
        return this.realLibrarySystem.getBooksByAuthor(author);
    }

    public ArrayList<Book> getBooksBySubject(String subject){
        return this.realLibrarySystem.getBooksBySubject(subject);
    }

    public ArrayList<Book> getCheckedoutBookByBorrower(Borrower borrower){
        return this.realLibrarySystem.getCheckedoutBookByBorrower(borrower);
    }

    public Borrower getBorrowerByBook(Book book){
        return this.realLibrarySystem.getBorrowerByBook(book);
    }
}
