package ba02;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Demo01{
    private static List<String> result = new ArrayList<>();

    public static void getAllFiles(String basePath){

        File file = new File(basePath);
        if (file.isFile()){
            result.add(basePath);
            return;
        }else if(file.isDirectory()){

            String[] fileList = file.list();

            for(String str : fileList){
                getAllFiles(basePath+"/"+str);
            }
        }

    }


    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        System.out.println("请输入要查询的路径:");
//        String path = scan.nextLine();
        String path = "D:";

        getAllFiles(path);

        for (String str :result){
            System.out.println(str);
        }
    }

}