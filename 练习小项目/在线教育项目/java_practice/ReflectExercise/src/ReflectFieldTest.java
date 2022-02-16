import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectFieldTest {
    @Test
    public void getClassWay() throws ClassNotFoundException {

        //方式一:通过对象.getClass()的方式
        Student student = new Student();
        Class c1 = student.getClass();
        System.out.println(c1);

        //方式二:类.Class的方式
        Class c2 = Student.class;
        System.out.println(c2);
        System.out.println(c1 == c2);

        //方式三:Class.forName("类的全路径");
        Class c3 = Class.forName("Student");
        System.out.println(c3);
    }


    //反射所有的属性
    @Test
    public void reflectField() throws ClassNotFoundException {
        Class c1 = Class.forName("Student");
        System.out.println(c1);
        //获取完整类名
        System.out.println(c1.getName());
        //获取简单类名
        System.out.println(c1.getSimpleName());

        //获取类中所有的public属性
        Field[] fields = c1.getFields();
        System.out.println(fields.length);
        //获取属性的名字
        System.out.println(fields[0].getName());

        //获取所有的属性
        System.out.println("***********所有的属性*********");
        Field[] fieldsAll = c1.getDeclaredFields();

        System.out.println("所有属性个数:" + fieldsAll.length);
        for (Field fie: fieldsAll ) {

            //获取属性的名字
            System.out.println(fie.getName());

            //获取属性的类型
            Class fieldType = fie.getType();
            System.out.println("属性的类型:" + fieldType.getName());

            //说去属性的修饰符
            int i = fie.getModifiers();
            //此时是以数字符号代表的修饰符信息,需要将数字信息转换位字符串信息
            //System.out.println(i);
            System.out.println(Modifier.toString(i));

        }
    }

    //反编译Field练习
    @Test
    public void decompiledField() throws ClassNotFoundException {
        StringBuilder s = new StringBuilder();
        Class c1 = Class.forName("Student");

        s.append(Modifier.toString(c1.getModifiers()) + " Class "+c1.getName() +" { \n");


        Field[] fields = c1.getDeclaredFields();
        for(Field field :fields){
            s.append("\t");
            s.append(Modifier.toString(field.getModifiers()) + " ");
            s.append(field.getType().getSimpleName() + " ");
            s.append(field.getName() + ";\n");

        }
        s.append("}");
        System.out.println(s.toString());
    }

    //通过反射访问对象的属性
    @Test
    public void setField() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class student_class = Class.forName("Student");
        Object obj = student_class.newInstance();

        //获取公共属性
        Field name_field = student_class.getField("name");

        //设置属性的值,使用set
        name_field.set(obj,"tom");
       //读取属性的值,使用get

        System.out.println(name_field.get(obj));

        //获取私有属性
        Field sex_field = student_class.getDeclaredField("sex");
        //设置私有属性为可连接状态
        sex_field.setAccessible(true);

        //设置属性的值
        sex_field.set(obj,true);
        System.out.println(sex_field.get(obj));

    }

}
