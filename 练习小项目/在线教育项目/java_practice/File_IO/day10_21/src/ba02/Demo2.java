package ba02;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 案例1:指定一个目录.扫描这个目录,找到文件名中包含指定字符的文件,并提示用户是否删除这个文件
 */
public class Demo2 {

    public static void main(String[] args) {

        //1. 让用户指定一个扫描的目录
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入一个目录:");
        String path = scan.nextLine();

        File file = new File(path);

        if (!file.isDirectory()){
            System.out.println("路径错误,程序退出!");
            return;
        }

        System.out.println("请输入文件名包含的关键字:");
        String token = scan.nextLine();


        //2.递归遍历目录
        List<File> result = new ArrayList<>();

        scanDir(file,token,result);

        //3.遍历结果
        System.out.println("共查到"+result.size()+"条结果:");

        for(File res : result){
            System.out.println(res.getAbsolutePath()+"是否要删除文件 (Y/N)");

            String choice = scan.next();

            if (choice.toLowerCase().equals("y")){
                res.delete();
            }
        }
    }

    private static void scanDir(File file,String token,List<File> result){
        File[] files = file.listFiles();

        if (files == null || files.length == 0){
            return;
        }

        for (File fil : files){
            if (fil.isFile()){
                //如果当前是一个普通的文件,判断当前文件名是否包含关键字
                if (fil.getName().contains(token)){
                    result.add(fil);
                }
            }else if (fil.isDirectory()){
                scanDir(fil,token,result);
            }
        }
    }
}
