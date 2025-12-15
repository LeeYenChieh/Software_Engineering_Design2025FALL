package midterm;

import java.util.ArrayList;

public class Borrower extends User {
    public int maxNum;
    public ArrayList<Book> checkoutBook;

    public Borrower(String name, int maxNum){
        super(name);
        this.maxNum = maxNum;
        this.checkoutBook = new ArrayList<Book>();
    }

    public Boolean canCheckoutBook(int num){
        if(num <= this.maxNum)
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
