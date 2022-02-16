import org.junit.Test;

import java.lang.reflect.*;

/*
    反射所有的属性
 */
public class ReflectField {
    @Test
    public void test1() throws ClassNotFoundException {
        //获取整个类
        Class studentClass = Class.forName("Student");

        //获取类名
        String className = studentClass.getName();
        System.out.println("完整类名:" + className);

        String className2 = studentClass.getSimpleName();
        System.out.println("简单类名:" + className2);

        //获取类中所有的public修饰方法
        Field[] fields = studentClass.getFields();
        System.out.println(fields.length);
        String fieldName = fields[0].getName();
        System.out.println(fieldName);

        //获取所有的field
        Field[] fields1 = studentClass.getDeclaredFields();
        System.out.println(fields1.length);


        System.out.println("***************遍历************");
        for (Field fs: fields1 ) {

            //获取属性修饰符列表
            int i = fs.getModifiers();
            System.out.println(i);
            //将代号数字转换成字符串
            String modifierString = Modifier.toString(i);
            System.out.println(modifierString);

            //获取属性的类型
            Class fieldType = fs.getType();
            System.out.println(fieldType.getName());
            System.out.println(fieldType.getSimpleName());

            //获取属性的名字
            System.out.println(fs.getName());
        }
    }


    //必须掌握
    //通过反射机制访问一个java对象的属性
    //给属性赋值set
    //获取属性的值get
    @Test
    public void test2() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        //使用反射机制访问一个对象的属性
        Class studentClass = Class.forName("Student");

        Object obj = studentClass.newInstance();

        //访问公共获取id属性
        Field id = studentClass.getDeclaredField("id");
        id.set(obj,12);
        System.out.println(id.get(obj));

        //访问私有属性
        Field age = studentClass.getDeclaredField("age");
        age.setAccessible(true);
        age.set(obj,110);
        System.out.println(age.get(obj));
    }



    //重要,获取Method()方法,通过反射机制调用方法
    //必须掌握
    @Test
    public void test3() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class studentClass = Class.forName("Student");
        //创建对象
        Object obj = studentClass.newInstance();

        //获取Method
        Method m1= studentClass.getDeclaredMethod("doubleNum",int.class);

        //调用方法
        //调用方法要素,对象,方法名,参数列表,返回值

        Object retVal = m1.invoke(obj,20);
        System.out.println(retVal);

    }

    //构造方法
    //通过反射创建对象,

    @Test
    public void test4() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class studentClass = Class.forName("Student");

        //调用无参构造方法
        Object obj = studentClass.newInstance();

        //调用有参的构造公共
        //第一步:先获取到这个有参数的构造方法
       Constructor con =  studentClass.getDeclaredConstructor(String.class,int.class);
        Object obj1 = con.newInstance("tom",25);

    }

    //给你一个类,怎么获取这个类的父类,已经实现了哪些接口(重点);
    @Test
    public void test6() throws ClassNotFoundException {
        //以String举例
        Class StringClass = Class.forName("java.lang.String");

        //获取String类的父类
        Class superClass = StringClass.getSuperclass();
        System.out.println(superClass.getName());

        //获取String类实现的所有接口(一个类可以实现多个接口)
        Class[] interfaces = StringClass.getInterfaces();
        for (Class inter : interfaces ) {
            System.out.println(inter.getName());

        }
    }



}
