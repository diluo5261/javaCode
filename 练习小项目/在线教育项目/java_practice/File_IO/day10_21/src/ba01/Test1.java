package ba01;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class Test1 {

    @Test
    public void test1() throws IOException {

        File file = new File("D:/Document/test.txt");
        System.out.println(file.exists());

        if (!file.exists()){

            file.createNewFile();
        }else if (file.exists()){
            boolean delete = file.delete();
        }

    }

    @Test
    public void test2(){
        File file = new File("D:/Document/test2");

        boolean mkdir = file.mkdir();

        if (mkdir){
            System.out.println("文件目录创建成功!");
        }else{
            System.out.println("文件目录创建失败!");
        }
    }

    @Test
    public void test3() throws IOException {
        File file = new File("D:/test.txt");

        boolean mkdir = file.createNewFile();

        if (mkdir){
            System.out.println("文件目录创建成功!");
        }else{
            System.out.println("文件目录创建失败!");
        }

    }

    @Test
    public void test4(){
        File file = new File("D:/test.txt");

        System.out.println("是否是文件目录:"+file.isDirectory());
        System.out.println("是否是文件:"+file.isFile());
        System.out.println("文件是否存在:"+file.exists());
        System.out.println("文件是否可读:"+file.canRead());
        System.out.println("文件是否可写:"+file.canWrite());
        System.out.println("文件是否隐藏:"+file.isHidden());
    }

    @Test
    public void test5(){
        File file = new File("D:/Document/test.txt");

        System.out.println("getName() : 获取文件的名称 : " + file.getParent());
        System.out.println("getPath() : 获取File 对象中封装的路径 : " + file.getParent());
        System.out.println("getAbsolutePath() : 获取绝对路径 : " + file.getAbsolutePath());
        System.out.println("getAbsoluteFile() : 获取文件绝对路径 : " + file.getAbsoluteFile());
        System.out.println("getParent() : 获取当前路径的父级路径 : " + file.getParent());
        System.out.println("lastModify() : 文件最后一次修改的时间 :  " + file.lastModified());
        System.out.println("length() : 文件的长度 :  " + file.length());
    }

    @Test
    public void test6() {
        File file = new File("D:/Document");

        System.out.println("以字符串的形式返回当前路径下所有的文件和文件夹名称:");
        String[] fileList = file.list();
        for (String str : fileList) {
            System.out.println(str);
        }

        System.out.println("以File对象的形式获取当前路径下所有文件和文件夹名称:");
        File[] files = file.listFiles();

        for (File fil : files) {
            System.out.println(fil);
        }

        System.out.println("获取计算机中所有的盘符");
        File[] files1 = File.listRoots();
        for (File fil1 : files1){
            System.out.println(fil1);
        }
    }


    @Test
    public void test21(){
        File file = new File("D:/");

        System.out.println("文件是否存在"+file.exists());
        System.out.println("是否是文件"+file.isFile());
        System.out.println("是否是目录"+file.isDirectory());

        String[] files = file.list();

        for (String fil : files){
            System.out.println(fil);
        }
    }
}
