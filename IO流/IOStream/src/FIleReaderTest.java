import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 文件字符输入流，只能读取普通文本，读取内容时，比较方便，快捷
 */
public class FIleReaderTest {
    @Test
    public void test1(){
        FileReader reader = null;
        try{
            reader = new FileReader("myfile.txt");
            char[] chars = new char[4];
            int readCount = -1;
            while((readCount = reader.read(chars)) != -1){
                System.out.print(new String(chars,0,readCount));
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{

            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * FileWrite
     * 文件字符输出流
     * 只能写普通文本
     */

    @Test
    public void test2(){
        FileWriter writer = null;
        try{
            writer = new FileWriter("myfile.txt",true);

            String str = "china number one";
            writer.write(str);

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
