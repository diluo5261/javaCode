package ba01;

import java.io.File;

public class TestDemo3 {
    public static void main(String[] args) {
        File file = new File("helloworld.txt");
//        File file2 = new File("test.txt");

        //renameTo方法,不仅可以重命名文件,还可以移动文件
        File file2 = new File("./out/test.txt");
        boolean res = file.renameTo(file2);

    }
}
