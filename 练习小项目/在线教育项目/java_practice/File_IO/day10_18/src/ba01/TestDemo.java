package ba01;

import java.io.File;

public class TestDemo {
    public static void main(String[] args) {
        File file = new File("test");
        System.out.println(file.exists());
        //mk就是make,dir 就是 direction 单词的缩写
        //mkdir 不支持创建多级目录
        //mkdirs 支持创建多级目录
        file.mkdir();
        System.out.println(file.exists());
    }
}
