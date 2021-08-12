import org.junit.Test;

import java.io.*;

/*

1、文件字节输入流，是万能的，任何类型的文件都可以采用这个流来读
2、字节的方式，完成输入的操作，完成读的操作（硬盘 --> 内存）
 */
public class FileInputStreamTest {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try {
            //创建文件字节输入流
            //文件路径C:\Users\34391\Documents\document\fileinputstream
            //写成 / 也是可以的
            fis = new FileInputStream("C:\\Users\\34391\\Documents\\document\\fileinputstream.txt");
//            int readDate = fis.read();//返回值是：读取到的“字节”本身，每调用一次read()方法，指针向后移动一次
            int readDate = -1;
            while((readDate = fis.read()) != -1){
                System.out.println(readDate);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //在finally语句中确保流一定关闭
            try {
                if (fis == null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /*
    一次读取一个字节，

     */
    @Test
    public void test1(){

        FileInputStream fis = null;
        try{
            fis = new FileInputStream("C:\\Users\\34391\\Documents\\document\\fileinputstream.txt");
            while(true){
                int readDate = fis.read();
                if(readDate == -1){
                    break;
                }
                System.out.println(readDate);
            }


        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if (fis == null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /*
    一次读取多个字节，减少和硬盘交互，提高效率
    IDEA默认的当前路径是project的根
     */
    @Test
    public void test2(){
        FileInputStream fis = null;
        try{
            fis = new FileInputStream("C:\\Users\\34391\\Documents\\document\\fileinputstream.txt");

            //开始读，采用byte数组，一次读取多个字节。最多读取”数组.length“个字节
            byte[] bytes = new byte[20];
            int readCount =-1;
//            readCount = fis.read(bytes);//返回的是读取字节的个数
            while((readCount = fis.read(bytes)) != -1){
                System.out.println(new String(bytes,0,readCount));

            }
/*
                for (int i = 0; i <readCount ; i++) {
                System.out.println(bytes[i]);
            }*/


        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if (fis == null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /*
    FileInputStream 其他常用方法：
    int available():返回流当中剩余的没有读到的字节数量
    long skip(long n) :跳过几个字节不读

     */
    @Test
    public void test3(){

        FileInputStream fis = null;
        try{
            fis = new FileInputStream("C:\\Users\\34391\\Documents\\document\\fileinputstream.txt");

            //不适合大文件，byte数组不能太大
            byte[] bytes = new byte[fis.available()];

            int readCount = fis.read(bytes);
            System.out.println(new String(bytes));
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if (fis == null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件字节输出流，负责写
     * 从内存到硬盘
     *
     */

    @Test
    public void test4(){
        FileOutputStream fos =null;
        try{
            //文件不存在会自动创建，会将原文件清空，然后重新写入
            fos = new FileOutputStream("myfile.txt");
//            fos = new FileOutputStream("myfile.txt"，true);//以追加的方式追加到文件末尾

            String str = "hello china";
            fos.write(str.getBytes());

            //写完之后，一定要刷新
            fos.flush();

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if (fos == null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用FileInputStream + FileOutputStream完成文件的拷贝
     * 拷贝的过程是一边读，一边写。
     * 使用以上的字节流拷贝文件时，文件类型随意，万能
     */
    @Test
    public void test5(){
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try{
            fis = new FileInputStream("C:\\Users\\34391\\Documents\\document\\mysql.7z");
            fos = new FileOutputStream("mysql.7z");

            byte[] bytes = new byte[1024];
            int readCount = -1;

            while((readCount = fis.read(bytes)) != -1){
                fos.write(bytes,0,readCount);
            }



        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if (fos == null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (fis == null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            //不要将两个写在一个try catch中，因为其中一个报错会导致后面的无法关闭
        }
    }

}
