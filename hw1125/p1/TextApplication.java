package hw1125.p1;

import java.nio.file.Files;
import java.nio.file.Paths;

public class TextApplication extends AbstractApplication{
    public TextApplication(){}

    public boolean createDocument(String path){
        this.document = new TextDocument(path);
        return true;
    }

    public boolean readDocument(){
        try {
            // 先把整個檔案內容讀進來（一次性）
            String content = Files.readString(Paths.get(this.document.path));

            // 如果成功讀到這裡，表示沒問題
            System.out.println(content);
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
