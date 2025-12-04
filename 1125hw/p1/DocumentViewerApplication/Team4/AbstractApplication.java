import java.io.File;

public abstract class AbstractApplication {
    public Document document;

    public void openDocument(String path){
        
        if(!checkCanOpen(path)){
            System.out.println("Failed to read " + path);
            return;
        }
        if(!createDocument(path)){
            System.out.println("Failed to read " + path);
            return;
        }
        if(!addDocument()){
            System.out.println("Failed to read " + path);
            return;
        }
        if(!readDocument()){
            System.out.println("Failed to read " + path);
            return;
        }
    }

    public boolean checkCanOpen(String path){
        File file = new File(path);
        if(file.exists() && file.isFile())
            return true;
        return false;
    }

    public abstract boolean createDocument(String path);

    public boolean addDocument(){
        return true;
    }

    public abstract boolean readDocument();
}
