import java.util.ArrayList;

public class BorrowerLibrarySystem implements ILibrarySystem {
    public RealLibrarySystem realSystem;
    public Borrower borrower;

    public BorrowerLibrarySystem(RealLibrarySystem realSystem, Borrower borrower){
        this.realSystem = realSystem;
        this.borrower = borrower;
    }

    public void checkout(ArrayList<Book> books, Borrower borrower){
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

    public ArrayList<Book> getBookByAuthor(String author){
        return this.realSystem.getBookByAuthor(author);
    }

    public ArrayList<Book> getBookBySubject(String subject){
        return this.realSystem.getBookBySubject(subject);
    }

    public ArrayList<Book> getBookByBorrower(Borrower borrower){
        if(this.borrower != borrower){
            System.out.println("Borrower can not find books checked out by other users");
            return null;
        }
        return this.realSystem.getBookByBorrower(borrower);
    }

    public Borrower getLastBorrowerByBook(Book book){
        System.out.println("Borrower can not find borrower");
        return null;
    }
}
