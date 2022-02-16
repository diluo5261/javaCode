package ba01;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class TestDemo7 {
    //使用printWriter类包装以下 OutputStream 然后可以更方便的进行写数据
    public static void main(String[] args) {
        try(OutputStream outputStream = new FileOutputStream("test.txt")){
            try(PrintWriter writer = new PrintWriter(outputStream)){
                writer.print("你好世界");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
