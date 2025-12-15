package hw1125.p1;

import java.io.BufferedReader;
import java.io.FileReader;

public class SpreadSheetApplication extends AbstractApplication{

    public SpreadSheetApplication(){}

    public boolean createDocument(String path){
        this.document = new SpreadSheetDocument(path);
        return true;
    }

    public boolean readDocument(){
        StringBuilder content = new StringBuilder();
        boolean isValid = true;
        int expectedFieldCount = -1;

        try (BufferedReader reader = new BufferedReader(new FileReader(this.document.path))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // 依逗號分割
                String[] fields = line.split(",", -1); // -1 保留空欄位

                // 第一行：決定欄位數量
                if (expectedFieldCount == -1) {
                    expectedFieldCount = fields.length;
                } else {
                    // 後續行：欄位數量必須一致
                    if (fields.length != expectedFieldCount) {
                        return false;
                    }
                }

                content.append(line).append("\n");
            }

            // 驗證全部成功 → 印出
            if (isValid) {
                System.out.print(content.toString());
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }
}
