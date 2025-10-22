import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class LibrarySystem {

    public static void displayError(){
        System.out.println("Error");
    }

    public static String readNextLine(BufferedReader reader){
        String nextLine = null;
        while(true){
            try{
                nextLine = reader.readLine();
                if(nextLine == null)
                    break;
                String[] parts = nextLine.trim().split("\\s+");
                if(parts.length == 0 || parts[0].equals(""))
                    continue;
                break;
            } catch (Exception e){
                continue;
            }
        }
        return nextLine;
    }

    public static String[] parseStrings(String line){
        return line.trim().split("\\s+");
    }

    public static int readNextNumber(BufferedReader reader){
        String[] parts;
        int number = -1;

        while(true){
            try{
                String line = readNextLine(reader);
                if(line == null)
                    break;
                parts = parseStrings(line);
                if(parts.length != 1){
                    throw new Exception();
                }
                number = Integer.parseInt(parts[0]);
                if(number < 0)
                    throw new Exception();
                break;
            } catch(Exception e){
                displayError();
                continue;
            }
        }

        return number;
    }

    public static Book readBook(BufferedReader reader, int id){
        try{
            String line = readNextLine(reader);
            String[] parts = parseStrings(line);
            if(parts.length != 2)
                throw new Exception();
            Book book = new Book(parts[0], parts[1], id);
            return book;
        } catch(Exception e){
            return null;
        }
    }

    public static User readUser(BufferedReader reader){
        try{
            String line = readNextLine(reader);
            String[] parts = parseStrings(line);
            if(parts.length != 2 && parts.length != 3)
                throw new Exception();
            User user;
            if(parts[0].equals("Staff") && parts.length == 2){
                user = new Staff(parts[1]);
                if(userSystem.get(user.name) != null)
                    throw new Exception();
                userSystem.put(user.name, new StaffLibrarySystem(real, (Staff)user));
                users.put(user.name, user);
            } else if(parts[0].equals("Borrower") && parts.length == 3){
                if(Integer.parseInt(parts[2]) <= 0)
                    throw new Exception();
                user = new Borrower(parts[1], Integer.parseInt(parts[2]));
                if(userSystem.get(user.name) != null)
                    throw new Exception();
                userSystem.put(user.name, new BorrowerLibrarySystem(real, (Borrower)user));
                users.put(user.name, user);
            } else{
                throw new Exception();
            }
            return user;
        } catch(Exception e){
            displayError();
            return null;
        }
    }

    public static ILibrarySystem getSystem(String name){
        return userSystem.get(name);
    }

    public static User getUser(String name){
        return users.get(name);
    }

    public static void addBook(BufferedReader reader, String[] parts){
        try{
            if(parts.length != 2)
                throw new Exception();
            ILibrarySystem system = getSystem(parts[0]);
            if(system == null)
                throw new Exception();

            Book b = readBook(reader, real.getCurId());
            if(b == null)
                throw new Exception();
            system.addBook(b);
        } catch(Exception e){
            displayError();
            return;
        }
    }

    public static void removeBook(BufferedReader reader, String[] parts){
        try{
            if(parts.length != 3)
                throw new Exception();
            ILibrarySystem system = getSystem(parts[0]);
            Book b = real.getBookById(Integer.parseInt(parts[2]));

            if(system == null || b == null)
                throw new Exception();
            if(!b.canBeCheckedout())
                throw new Exception();
            system.removeBook(b);

        } catch(Exception e){
            displayError();
            return;
        }
    }

    public static void checkout(BufferedReader reader, String[] parts){
        try{
            if(parts.length != 3)
                throw new Exception();
            ILibrarySystem system = getSystem(parts[0]);
            Borrower user = (Borrower) getUser(parts[2]);

            if(system == null || user == null)
                throw new Exception();
            
            String line = readNextLine(reader);
            parts = parseStrings(line);

            ArrayList<Book> books = new ArrayList<Book>();
            for(String idstr : parts){
                int id = Integer.parseInt(idstr);
                Book b = real.getBookById(id);
                if(b == null)
                    throw new Exception();
                books.add(b);
            }
            system.checkoutBook(books, user);
        } catch(Exception e){
            displayError();
            return;
        }
    }

    public static void returnIns(BufferedReader reader, String[] parts){
        try{
            if(parts.length != 3)
                throw new Exception();
            ILibrarySystem system = getSystem(parts[0]);
            Book b = real.getBookById(Integer.parseInt(parts[2]));

            if(system == null || b == null)
                throw new Exception();
            system.returnBook(b);
        } catch(Exception e){
            displayError();
            return;
        }
    }

    public static void listAuthor(BufferedReader reader, String[] parts){
        try{
            if(parts.length != 3)
                throw new Exception();
            ILibrarySystem system = getSystem(parts[0]);
            if(system == null)
                throw new Exception();
            ArrayList<Book> books = system.getBooksByAuthor(parts[2]);
            printBooks(books);

        } catch(Exception e){
            displayError();
            return;
        }
    }

    public static void listSubject(BufferedReader reader, String[] parts){
        try{
            if(parts.length != 3)
                throw new Exception();
            ILibrarySystem system = getSystem(parts[0]);
            if(system == null)
                throw new Exception();
            ArrayList<Book> books = system.getBooksBySubject(parts[2]);
            printBooks(books);
        } catch(Exception e){
            displayError();
            return;
        }
    }

    public static void findChecked(BufferedReader reader, String[] parts){
        try{
            if(parts.length != 3)
                throw new Exception();
            ILibrarySystem system = getSystem(parts[0]);
            if(system == null)
                throw new Exception();
            Borrower user = (Borrower) getUser(parts[2]);

            ArrayList<Book> books = system.getCheckedoutBookByBorrower(user);
            if(books != null)
                printBooks(books);
        } catch(Exception e){
            displayError();
            return;
        }
    }

    public static void BorrowerIns(BufferedReader reader, String[] parts){
        try{
            if(parts.length != 3)
                throw new Exception();
            ILibrarySystem system = getSystem(parts[0]);
            Book b = real.getBookById(Integer.parseInt(parts[2]));

            if(system == null || b == null)
                throw new Exception();
            
            Borrower user = system.getBorrowerByBook(b);
            System.out.printf("User: %s\n", user.name);

        } catch(Exception e){
            displayError();
            return;
        }
    }

    public static void printBooks(ArrayList<Book> books){
        for(Book b : books){
            System.out.printf("ID: %d Author: %s Subject: %s\n", b.id, b.author, b.subject);
        }
    }

    static RealLibrarySystem real = new RealLibrarySystem();
    static HashMap<String, ILibrarySystem> userSystem = new HashMap<String, ILibrarySystem>();
    static HashMap<String, User> users = new HashMap<String, User>();

    public static void main(String[] args) {
        try{
            File inputFile;
            if(args.length > 0)
                inputFile = new File(args[0]);
            else
                inputFile = new File("");
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            int numberOfBook = readNextNumber(reader);
            for(int i = 0;i < numberOfBook;i++){
                Book b = readBook(reader, real.getCurId());
                if(b == null){
                    displayError();
                    continue;
                }
                real.addBook(b);
            }

            int numberOfUser = readNextNumber(reader);
            for(int i = 0;i < numberOfUser;i++){
                readUser(reader);
            }

            String line = readNextLine(reader);
            while (line != null) {
                try{
                    String[] parts = parseStrings(line);

                    if(parts[1].equals("addBook")){
                        addBook(reader, parts);
                    } else if(parts[1].equals("removeBook")){
                        removeBook(reader, parts);
                    } else if(parts[1].equals("checkout")){
                        checkout(reader, parts);
                    } else if(parts[1].equals("return")){
                        returnIns(reader, parts);
                    } else if(parts[1].equals("listAuthor")){
                        listAuthor(reader, parts);
                    } else if(parts[1].equals("listSubject")){
                        listSubject(reader, parts);
                    } else if(parts[1].equals("findChecked")){
                        findChecked(reader, parts);
                    } else if(parts[1].equals("Borrower")){
                        BorrowerIns(reader, parts);
                    } else{
                        throw new Exception();
                    }

                } catch(Exception e){
                    displayError();
                }
                
                line = readNextLine(reader);
            }

        } catch(Exception e){
            displayError();
        }
    }
}
