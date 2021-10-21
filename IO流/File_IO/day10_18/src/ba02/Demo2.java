package ba02;

import java.io.*;
import java.util.Scanner;

/*
启动程序后,让用户输入一个文件的路径(绝对路径)要求这个文件是一个普通的文件,不是目录,然后指定一个目标路径
 */
public class Demo2 {
    //进行文件复制
    public static void main(String[] args) {
        System.out.println("请输入要复制的文件:");
        Scanner scan = new Scanner(System.in);
        String srcPath = scan.next();

        File file  =new File(srcPath);

        if (!file.isFile()){
            System.out.println("文件路径错误,!程序退出");
            return;
        }
        System.out.println("请输入你要复制的文件目标路径(绝对路径):");
        String destPath = scan.next();

        //要求这个destFile 必须不能存在
        File destFile = new File(destPath);
        if (destFile.exists()){
            System.out.println("目标文件的路径已经存在");
            return;
        }

        if (!destFile.getParentFile().exists()){
            System.out.println("目标路径的文件目录不存在");
            return;
        }

        //具体进行复制操作
        //复制惭怍就是打开待复制的文件,读取每个字节,然后把这些字节给写入到目标文件当中
        try(InputStream inputStream = new FileInputStream(file); OutputStream outputStream = new FileOutputStream(destFile)) {
            //从inputSteam 中按照字节来读,然后把结果写入到outputStream中
            while(true){
                byte[] buffer = new byte[1024];
                int len = inputStream.read(buffer);
                if (len == -1){
                    break;
                }

                outputStream.write(buffer,0,len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("复制完成1");
    }
}
