import java.util.ArrayList;

public class RealLibrarySystem implements ILibrarySystem{
    public int curId;
    public ArrayList<Book> books;

    public RealLibrarySystem(){
        this.curId = 0;
        this.books = new ArrayList<Book>();
    }


    public Boolean checkAllBookIsAvailable(ArrayList<Book> books){
        for(Book b : books){
            if(b.canBeCheckedout() == false)
                return false;
        }
        return true;
    }

    public void checkoutBook(ArrayList<Book> books, Borrower borrower){
        if(borrower.canCheckoutBook(books.size())){
            if(this.checkAllBookIsAvailable(books)){
                for(Book b : books){
                    borrower.addBook(b);
                    b.checkedout(borrower);
                }
            } else{
                System.out.println("Can not check out since the book is checked out");
            }
        } else{
            System.out.println("Can not check out since the number of books exceed the limitation of user can check-out");
        }
    }

    public void returnBook(Book book){
        if(book.canBeCheckedout()){
            System.out.println("Can not return since the book isn't checked out");
        } else{
            Borrower temp = book.borrower;
            book.returnBook();
            temp.removeBook(book);
        }
    }

    public void addBook(Book book){
        this.books.add(book);
        this.curId += 1;
    }

    public void removeBook(Book book){
        this.books.remove(book);
    }

    public ArrayList<Book> getBooksByAuthor(String author){
        ArrayList<Book> targets = new ArrayList<Book>();
        for(Book b : this.books){
            if(b.author.equals(author))
                targets.add(b);
        }
        return targets;
    }

    public ArrayList<Book> getBooksBySubject(String subject){
        ArrayList<Book> targets = new ArrayList<Book>();
        for(Book b : this.books){
            if(b.subject.equals(subject))
                targets.add(b);
        }
        return targets;
    }

    public ArrayList<Book> getCheckedoutBookByBorrower(Borrower borrower){
        return borrower.checkoutBook;
    }

    public Borrower getBorrowerByBook(Book book){
        return book.borrower;
    }

    public Book getBookById(int id){
        for(Book b : this.books){
            if(b.id == id)
                return b;
        }
        return null;
    }

    public int getCurId(){
        return this.curId;
    }
}
