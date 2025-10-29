public class Book {
    public String author;
    public String subject;
    public int id;
    public Boolean available;
    public Borrower borrower;

    public Book(String author, String subject, int id){
        this.author = author;
        this.subject = subject;
        this.id = id;
        this.available = true;
        this.borrower = null;
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
