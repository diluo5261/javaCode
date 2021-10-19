package ba01;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadAllFile {
    public static List<String> result = new ArrayList<>();
    public static void getAllFiles(String basePath){
        File file = new File(basePath);
        if (file.isFile()){
            result.add(basePath);
            return;
        }else if(file.isDirectory()){
            String[] files = file.list();

            for(String fil : files){
                getAllFiles(basePath + "/"+fil);

            }
        }

    }
    public static void main(String[] args) {
        getAllFiles(".");

        for (String s : result){
            System.out.println(s);
        }

    }
}
