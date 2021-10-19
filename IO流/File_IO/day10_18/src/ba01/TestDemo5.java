package ba01;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class TestDemo5 {
    public static void main(String[] args) {
        //尝试从文件中读取中文,借助标准库中内置的处理字符集的方式
        //Scanner 不光能从控制台读取标准输入,也可以从文件中读取数据
        try(InputStream inputStream = new FileInputStream("test.txt")) {
            //Scanner里面也有个 close 方法,这个close 其实也就是用来关闭Scanner 内包含的 InputStream
            try(Scanner scan = new Scanner(inputStream,"UTF-8")){
                while(scan.hasNext()){
                    String s = scan.next();
                    System.out.println(s);
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
