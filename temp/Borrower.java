import java.util.ArrayList;

public class Borrower extends User {
    public int maxNumCheckoutBook;
    public ArrayList<Book> checkoutBook;

    public Borrower(String name, int maxNumCheckoutBook){
        super(name);
        this.role = "Borrower";
        this.maxNumCheckoutBook= maxNumCheckoutBook;
        this.checkoutBook = new ArrayList<Book>();
    }

    public Boolean canCheckoutBook(int num){
        if(num <= this.maxNumCheckoutBook)
            return true;
        return false;
    }

    public void addBook(Book b){
        this.checkoutBook.add(b);
    }

    public void removeBook(Book b){
        this.checkoutBook.remove(b);
    }
}
