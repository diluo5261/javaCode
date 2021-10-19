package ba01;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class TestDEmo6 {
    public static void main(String[] args) {
        try(OutputStream outputStream = new FileOutputStream("test.txt")){
            outputStream.write("你好张三".getBytes());
            outputStream.write(48);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
