package ba01;

import java.io.File;

public class TestFile {
    public static void main(String[] args) {
        File file = new File("./file.txt");
        String name = file.getName();
        System.out.println(name);

        String parent = file.getParent();
        System.out.println(parent);
        System.out.println(file.getAbsolutePath());
    }
}
