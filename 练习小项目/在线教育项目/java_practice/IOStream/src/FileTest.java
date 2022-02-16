import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * 1、File 和四大家族没有关系，所以File类不能完成文件的读写
 * 2、File对象代表什么，可能是目录，或文件
 *
 * 3、掌握File类的常用方法
 */
public class FileTest {
    @Test
    public void test1() throws IOException {
        File f1 = new File("D:\\file");

        if (!f1.exists()) {
            //以文件的形式创建
            f1.createNewFile();
        }

        if (!f1.exists()){
            //如果D：\\file不存在，则以目录的形式创建
            f1.mkdir();

            //多重目录形式新建  //d:\c\d\f\r
            //f2.mkdirs()';
        }

        //获取绝对路径
        f1.getAbsolutePath();

        //判断是否是文件，是否是目录，获取文件最后一次修改时间、获取文件大小

        //File[] listFiles 获取当前目录下的子文件
        File[] files = f1.listFiles();
        for (File file:files  ) {
            System.out.println(file.getName());

        }
    }

    //拷贝目录
    @Test
    public void copyAll(){
        //拷贝源

        //拷贝目标


        //调用拷贝方法

    }
}
