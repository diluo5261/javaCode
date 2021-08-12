import org.junit.Test;

import java.io.*;

/**
 * 带有缓冲区的自读输入流，使用这个流不需要自定义char数组，自带缓冲
 */
public class BufferReaderTest {
    @Test
    public void test1() throws IOException {

        //当一个流的构造方法中需要一个流的时候，这个被传进来的流叫做：节点流
        //外部负责包装的这个流，叫做包装流，还有一个名字叫做：处理流
        //像当前这个程序来说：FileReader 就是一个节点流。BufferReader就是一个处理流

        FileReader reader = new FileReader("myfile.txt");
        BufferedReader br = new BufferedReader(reader);

        //对于包装流来说，只需要关闭最外层的流就可以了， 里面的流可以会自动关闭

       String s = null;
       //br.readLine() 读取一行数据，不包括后面的换行符
       while((s = br.readLine()) != null){
           System.out.println(s);
       }

        if (br != null) {
            br.close();

        }
    }

    @Test
    public void test2() throws FileNotFoundException {

        //字节流
        FileInputStream in = new FileInputStream("myfile.txt");

        //通过转换流，将字节流转换成字符流
        //in是节点流，reader是包装流
        InputStreamReader reader = new InputStreamReader(in);

        //这个构造放法只能传递一个字符流，不能传递字节流。
        //reader是节点流，br是包装流
        BufferedReader br = new BufferedReader(reader);

        //合并起来写

    }

    /**
     * BufferedWriter :带有缓冲的字符输出流
     * OutputStreamWriter：转换流
     *
     */
    @Test
    public void test4() throws Exception {
        //带有缓冲区的字符输出流
//        BufferedWriter out = new BufferedWriter(new FileWriter("myfile.txt"));

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("myfile.txt")));

        out.write("hello5555555555 ");
        out.write("world");
        out.write("\n");
        out.write(10);


        if (out != null) {
            out.close();

        }
    }


}
