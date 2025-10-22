import java.util.ArrayList;

public class BorrowerLibrarySystem implements ILibrarySystem{
    public RealLibrarySystem realLibrarySystem;
    public Borrower borrower;

    public BorrowerLibrarySystem(RealLibrarySystem r, Borrower borrower){
        this.realLibrarySystem = r;
        this.borrower = borrower;
    }

    public void checkoutBook(ArrayList<Book> books, Borrower borrower){
        System.out.println("Borrower can not check out the books");
    }

    public void returnBook(Book book){
        System.out.println("Borrower can not return book");
    }

    public void addBook(Book book){
        System.out.println("Borrower can not add book");
    }

    public void removeBook(Book book){
        System.out.println("Borrower can not remove book");
    }

    public ArrayList<Book> getBooksByAuthor(String author){
        return this.realLibrarySystem.getBooksByAuthor(author);
    }

    public ArrayList<Book> getBooksBySubject(String subject){
        return this.realLibrarySystem.getBooksBySubject(subject);
    }

    public ArrayList<Book> getCheckedoutBookByBorrower(Borrower borrower){
        if(borrower == this.borrower)
            return this.realLibrarySystem.getCheckedoutBookByBorrower(borrower);
        else
            System.out.println("Borrower can not find books checked out by other users");
        return null;
    }

    public Borrower getBorrowerByBook(Book book){
        System.out.println("Borrower can not find borrower");
        return null;
    }
}