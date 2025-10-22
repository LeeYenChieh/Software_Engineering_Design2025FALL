public class Book {
    public String author;
    public String subject;
    public Borrower borrower;
    public Boolean available;
    public int id;

    public Book(String author, String subject, int id){
        this.author = author;
        this.subject = subject;
        this.borrower = null;
        this.available = true;
        this.id = id;
    }

    public void checkedout(Borrower borrower){
        this.available = false;
        this.borrower = borrower;
    }

    public void returnBook(){
        this.available = true;
    }

    public Boolean canBeCheckedout(){
        return this.available;
    }
}
