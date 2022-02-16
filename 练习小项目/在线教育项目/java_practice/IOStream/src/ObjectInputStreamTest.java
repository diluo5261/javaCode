import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 对象的序列化和反序列化
 *参与序列化和反序列化的对象,必须实现Serializable接口
 * 3、Serializable是一个标志性接口,会默认生成一个序列化版本号
 * java语言中是采用什么机制来区分的
 * 1、首先通过类名进行比对，如果类名不一样，肯定不是一个类
 * 2、如果类名一样，再考序列化版本号进行区分
 *

 *
 */
public class ObjectInputStreamTest {
    //序列化
    @Test
    public void Test1() throws IOException {
        //创建java对象
        Student s = new Student("tom",15);

        //序列化
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students"));

        //序列化对象
        oos.writeObject(s);

        //关闭流
        oos.close();
    }

    //反序列化
    @Test
    public void test2() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students"));
        Object obj = ois.readObject();
        //反序列化回来是一个学生对象，会调用对象的toString方法
        System.out.println(obj);

        ois.close();
    }

    //一次序列化多个对象
    @Test
    public void test3() throws IOException {
        //使用集合
        List<Student> list = new ArrayList<>();
        list.add(new Student("jone",45));
        list.add(new Student("peter",56));

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("StudentsCollection"));

        oos.writeObject(list);
        oos.close();


        //序列化一个集合，这个集合中放了很多的其他对象
    }

    //多个对象的反序列化,反序列化集合
    //参与序列化的Arraylist集合记忆集合中的元素都需要实现Serializable
    @Test
    public void test4() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("StudentsCollection"));

//        Object obj = ois.readObject();
//        System.out.println(obj instanceof List);
        List<Student> list = (List<Student>) ois.readObject();
        for (Student s: list) {
            System.out.println(s);

        }
        ois.close();
    }

    //关键字：transient关键字表示游离的，不参与序列化

}

/*
过了很久，Student这个类源代码改动了
源代码改动之后了，需要重新编译，比那一之后生成了全新的字节码文件
并且class文件再次运行的时候，java虚拟机生成的序列化版本号也会发生相应的改变

 * 这种自动生成自动化版本号有什么缺陷，
 *      这种自动化大亨成的序列化版本号缺点是：一旦代码确定以后，不能进行后续的修改，因为只要修改，必然会重新编译
 *      此时会生成全新的序列化版本号，这个时候java虚拟机会认为这是一个全新的类
 *
 * 最终结论：
 *          凡是一个类实现了Serializable接口，建议给该类提供一个固定不变的序列化版本号
 这样，以后这个类即使代码修改了，但是版本号不变，java虚拟机会认为是同一个类

 java虚拟机看到Serializable接口后，会自动生成一个序列化版本号
 这里没有手动写出来，java虚拟机会默认提供这个序列化版本号
 建议将序列化版本号手的写出来，不建议自动生成


 */


class Student implements Serializable {
    private static final long serialVersionUID = 14L;//java虚拟机识别一个类的时候先通过类名，如果类名一致，再通过序列号
    private String name;
    //private int age;
    //关键字：transient关键字表示游离的，不参与序列化
    private transient String email;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        //this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   /* public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }*/

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age="  +
                '}';
    }


}