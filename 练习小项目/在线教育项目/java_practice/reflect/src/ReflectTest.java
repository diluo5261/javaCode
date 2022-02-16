import org.junit.Test;

public class ReflectTest {

    /**
     * 获取Class类的三种方式
     */
    @Test
    public void test1() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //方式一:class.forname();
        Class<Student> c1 = (Class<Student>) Class.forName("Student");

        System.out.println(c1);

        //方法二:类.Class
        Class c2 = Student.class;
        System.out.println(c1 == c2);

        //方式三:对象.getClass()
        Student s = new Student();

        Class c3 = s.getClass();
        System.out.println(c1 == c3);


        //通过反射机制,获取Class,通过Class实例化对象
        Object obj = c1.newInstance();
        //newInstance这个方法会调用Student这个类的无参构造方法,完成对象的创建,jdk9以后这个方法就过时了
        System.out.println(obj);
    }
}
