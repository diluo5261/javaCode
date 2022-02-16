package ba02;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*

示例3
 */
public class Demo3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //1.让用户输入一个路径,待搜索的路径
        System.out.println("请输入要扫描的根目录:");
        String rootDir = scan.next();

        File file = new File(rootDir);

        if (!file.isDirectory()){
            System.out.println("该目录不存在或者不是文件!程序退出");
            return;
        }
        //2.再让用户输入一个查询词,表示要搜索的结果要包含这个次
        System.out.println("请输入要查询的词:");
        String query = scan.next();
        //3.白能力目录以及文件,进行匹配

        List<File> result = new ArrayList<>();
        scanDirWithContent(file,query,result);

        //4.把结果打印
        for (File file1 : result){
            System.out.println(file1.getName());
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
                if (file.getName().contains(query)){
                    //看看名称是否包含
                    result.add(file);
                }
                if (isContentContains(file,query)){
                    //看看内容是否包含
                    result.add(file);
                }
            }
        }
    }

    public static boolean isContentContains(File file , String query){
        //打开file文件,一次读取每一行结果,去和query来进行一个 indexof
        StringBuilder builder = new StringBuilder();

        try(InputStream inputStream = new FileInputStream(file)) {
            Scanner scan = new Scanner(inputStream,"UTF-8");
            while(scan.hasNext()){
                String line = scan.nextLine();
                builder.append(line+"\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.indexOf(query) != -1;
    }
}
