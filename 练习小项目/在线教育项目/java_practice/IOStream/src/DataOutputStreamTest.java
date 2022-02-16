import org.junit.Test;

import java.io.*;

/**
 * java.io.DataOutputStream : 数据专属流
 * 这个流可以将数据连同数据的类型一同写入到文件
 * 这个文件不是普通文本文档
 */
public class DataOutputStreamTest {

    //写数据

    @Test
    public void test1() throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("data"));

        //写数据
        byte b =100;
        short s = 200;
        int i =300;
        long l =400;
        float f = 20.0f;
        double d = 3.14;
        boolean boo = false;
        char c = 'a';

        //写
        dos.writeByte(b);
        dos.writeShort(s);
        dos.writeInt(i);;
        dos.writeLong(l);
        dos.writeFloat(f);
        dos.writeDouble(d);
        dos.writeBoolean(boo);
        dos.writeChar(c);

        if (dos != null) {
            dos.close();
        }
    }

    //读数据，
    //DataOutputStream写的文件，只能用 DateInputStream 去读，并且读的顺序要和写入的顺序一致，才能够正常的读写
    @Test
    public void test2() throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream("data"));
        //开始读
        byte b = dis.readByte();
        short s = dis.readShort();
        int i = dis.readInt();
        long l = dis.readLong();
        float f = dis.readFloat();
        double d = dis.readDouble();
        boolean boo = dis.readBoolean();
        char c = dis.readChar();

        System.out.println(b);
        System.out.println(s);
        System.out.println(i);
        System.out.println(l);
        System.out.println(f);
        System.out.println(d);
        System.out.println(boo);
        System.out.println(c);

        if (dis != null) {
            dis.close();


        }




    }
}
