package ba01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class TestDemo4 {
    public static void main(String[] args) throws IOException {
        //这个创建实例的过程,就相当于在打开文件
        //要打开文件,然后才能进行读写

        InputStream inputStream = new FileInputStream("test.txt");

        //逐个字节的方式把文件内容读取出来
        int res = 0;
        try{

            while((res = inputStream.read()) != -1){
            /*
            每次调用read就可以读取一些数据出来,read提供了好几个版本,其中无参数版本就是一次读取一个字节
            对于这个无参数版本的 read 来说,返回值就是这次操作读取到的字节
            这个范围就是 0-255
            如果读到文件末尾(EOF end of file), 此时继续进行read ,就会返回 -1
             */
                System.out.println(res);

            }
        }finally {

            //读取完资源后必须关闭
            inputStream.close();
        }

    }
}
