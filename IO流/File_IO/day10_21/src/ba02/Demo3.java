package ba02;

import java.io.*;
import java.util.Scanner;

/*
启动程序后,用户输入一个文件路径(绝对路径),要求这个文件是一个普通的文件,不是目录,然后指定一个目标路径
 */
public class Demo3 {


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入源文件路径:");
        String sourcePath = scan.next();

        File file = new File(sourcePath);
        if (!file.isFile()){
            System.out.println("文件路径错误!!程序退出!");
            return;
        }

        if (!file.exists()){
            System.out.println("文件不存在!");
            return;
        }

        System.out.print("请输入你要复制的目标路径:");
        String destPath = scan.next();

        File destFile = new File(destPath);

        if (destFile.exists()){
            System.out.println("目标路径已经存在");
            return;
        }

        if(!destFile.getParentFile().exists()){
            System.out.println("目标路径的文件目录不存在!");
            return;
        }

        //进行复制操作

        try(InputStream inputStream = new FileInputStream(file);OutputStream outputStream = new FileOutputStream(destFile)) {
            byte[] array = new byte[1024];
            int len= 0;

            while((len = inputStream.read(array)) != -1){

                outputStream.write(array,0,len);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("复制完成!");

    }
}
