import java.util.ArrayList;

public class RealLibrarySystem implements ILibrarySystem {
    public ArrayList<Book> books;
    public int curId;

    public RealLibrarySystem(){
        this.books = new ArrayList<Book>();
        this.curId = 0;
    }

    public Boolean checkAllBookIsAvailable(ArrayList<Book> books){
        for(Book b : books){
            if(b.canBeCheckedout() == false)
                return false;
        }
        return true;
    }

    public void checkout(ArrayList<Book> books, Borrower borrower){
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

    public ArrayList<Book> getBookByAuthor(String author){
        ArrayList<Book> result = new ArrayList<Book>();
        for(Book b : this.books){
            if(b.author.equals(author))
                result.add(b);
        }
        return result;
    }

    public ArrayList<Book> getBookBySubject(String subject){
        ArrayList<Book> result = new ArrayList<Book>();
        for(Book b : this.books){
            if(b.subject.equals(subject))
                result.add(b);
        }
        return result;
    }

    public ArrayList<Book> getBookByBorrower(Borrower borrower){
        return borrower.checkoutBook;
    }

    public Borrower getLastBorrowerByBook(Book book){
        if(book.borrower == null)
            System.out.println("Error");
        return book.borrower;
    }

    public int getCurId(){
        return this.curId;
    }

    public Book getBookById(int target){
        for(Book b : this.books){
            if(b.id == target)
                return b;
        }

        return null;
    }
}
