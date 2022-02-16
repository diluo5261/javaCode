import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * 标准的字节输出流，输出到控制台
 * 标准输出流不需要手动关闭
 */
public class PrintStreamTest {
    @Test
    public void test1() throws FileNotFoundException {
        //联合起来写
        System.out.println("hello");

        //分开写
        PrintStream ps = System.out;
        ps.print("hello china");

        //可以改变标准输出流的输出方向吗
        //标准输出流不在指向控制台，指向log文件
        PrintStream printStream = new PrintStream(new FileOutputStream("log"));
        System.setOut(printStream);
        System.out.println("hello");
    }
}
