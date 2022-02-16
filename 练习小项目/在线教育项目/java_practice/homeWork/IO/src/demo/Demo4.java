package demo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 案例3 : 扫描指定的目录,并找到名称或者内容中包含指定自读的所有蒲普通文件,不包含目录
 */
public class Demo4 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入扫描的根目录:");

        String srcPath = scan.next();

        File srcFile = new File(srcPath);

        if (!srcFile.isDirectory()){
            System.out.println("输入的不是根目录,程序退出!");
            return;
        }

        System.out.print("输入要查找的内容:");
        String query = scan.next();

        //遍历目录,将所有文件存储在list中
        List<File> result = new ArrayList<>();

        scanDirWithContent(srcFile,query,result);


        //遍历结果
        for (File fil : result){
            System.out.println(fil);
        }
    }

    public static void scanDirWithContent(File rootFile,String query,List<File> result){

        File[] files = rootFile.listFiles();

        if (files == null || files.length == 0){
            return;
        }

        for (File file : files){
            if (file.isDirectory()){
                scanDirWithContent(file,query,result);
            }else{
                //查看名字是否包含
                if (file.getName().contains(query)){
                    result.add(file);

                }else if (isContentContains(file,query)) {//查看内容是否包含
                    result.add(file);
                }
            }
        }
    }

    private static boolean isContentContains(File file,String query){

        StringBuilder builder = new StringBuilder();

        try(InputStream inputStream = new FileInputStream(file)) {
            Scanner scan = new Scanner(inputStream,"UTF-8");
            while(scan.hasNext()){
                builder.append(scan.nextLine()+"\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (builder.indexOf(query) != -1);
    }
}
