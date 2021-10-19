package ba02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
    案例1:指定一个目录,扫描这个目录,找到文件中包含指定字符的文件,并提示用户是否需要删除这个文件
 */
public class Demo1 {
    public static void main(String[] args) throws IOException {
        //1.让用户指定一个待扫描的根目录
        System.out.println("请输入一个根目录:");
        Scanner scan = new Scanner(System.in);
        String path  = scan.next();

        File file  = new File(path);

        if (!file.isDirectory()){
            System.out.println("路径错误,程序退出");
            return;
        }

        System.out.println("文件名包含的关键词");

        String token = "";

        //2.递归遍历目录
        List<File> result = new ArrayList<>();
        scanDir(file,token,result);

        //3.遍历result 问用户是否需要删除文件
        for (File f :result){
            System.out.println("是否要删除文件");
            String input = scan.next();

            if (input == "Y"){
                f.delete();
            }

        }


    }

    private static void scanDir(File rootDir,String token,List<File> result) throws IOException {
        //list 返回的是一个文件名(String),使用listFiles 直接得到的是 File 对象,用起来更方便一些
        File[] files = rootDir.listFiles();

        if (files == null || files.length == 0){
            //当前目录是一个空目录
            return;
        }

        for (File f : files){
            if (f.isDirectory()){

                scanDir(f,token,result);
            }else{
                //如果当前文件是一个普通的文件,就判断着文件是否包含待查找的关键词
                if (f.getName().contains(token)){
                    result.add(f.getCanonicalFile());
                }
            }
        }

    }

}
