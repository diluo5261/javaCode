import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectMethodTest {

    @Test
    public void getReflectMethod() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //1、获取Class对象
        Class student_class = Class.forName("Student");

        Object obj = student_class.newInstance();

        Method[] student_methods = student_class.getDeclaredMethods();
        for (Method method: student_methods) {

            //获取方法修饰符
            System.out.print(Modifier.toString(method.getModifiers())+" ");


            //获取方法的返回值
            System.out.print(method.getReturnType().getSimpleName());

            //方法的名字
            //获取方法的名字
            System.out.println(" "+method.getName());

            //方法的参数列表
             Class[] parameters = method.getParameterTypes();
             for(Class parameter: parameters){
                 System.out.println(parameter.getSimpleName());
             }
        }
    }

    //反编译方法
    @Test
    public void decompiledMethod() throws ClassNotFoundException {
        //获取Class的对象
        Class student_class = Class.forName("Student");

        StringBuilder s = new StringBuilder();
        s.append(Modifier.toString(student_class.getModifiers())+" + class " + student_class.getSimpleName()+"{\n");

        //获取student的所有方法
        Method[] student_methods = student_class.getDeclaredMethods();
        for (Method method: student_methods){
            s.append("\t");
            //权限修饰符
            s.append(Modifier.toString(method.getModifiers()) + " ");

            //获取返回值
            s.append(method.getReturnType().getSimpleName()+" ");

            //获取方法名
            s.append(method.getName() + "(");

            //获取参数列表，参数可能有多个
            Class[] method_parameters = method.getParameterTypes();

            for (int i = 0; i < method_parameters.length; i++) {
                s.append(method_parameters[i].getSimpleName());
                if(i != method_parameters.length-1){
                    s.append(",");
                }
            }
            s.append("){}\n");
        }
        s.append("}");

        System.out.println(s);
    }

    //通过反射机制调用方法
    @Test
    public void reflectInovkeMethod() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //1、获取Class类的对象
        Class student_class = Class.forName("Student");
        //2、创建对象的实例
        Object obj = student_class.newInstance();

        //3、获取method
        Method stu_method_login = student_class.getMethod("login",String.class,String.class);
        //4、调用方法
        Object retVal = stu_method_login.invoke(obj,"admin","123");
        System.out.println(retVal);
    }

    //通过反射创建对象
    @Test
    public void reflectCreatConsructor() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //1、获取类的对象
        Class stu_class = Class.forName("Student");

        //2、调用无参的构造方法
        //这种方法在jdk9.0以后就弃用了，
        Object obj = stu_class.newInstance();
        System.out.println(obj);

        //调用有参的构造方法
        Constructor stu_constructor =
                stu_class.getDeclaredConstructor(String.class,int.class,double.class,boolean.class);

        //通过有参的构造器，新建对象
        Object stu_obj = stu_constructor.newInstance("jerry",78,99.9,false);
        System.out.println(stu_obj);

        //调用无参的构造方法
        Constructor  stu_constructor_nonArgument = stu_class.getDeclaredConstructor();
        Object stu2_obj = stu_constructor_nonArgument.newInstance();
        System.out.println(stu2_obj);


    }

    //反编译一个构造器
    @Test
    public void  decompiledConstructor() throws ClassNotFoundException {
        //1、获取类的对象
        Class stuClass = Class.forName("Student");

        //2、创建一个字符串
        StringBuilder s = new StringBuilder();
        s.append(Modifier.toString(stuClass.getModifiers()) + " class ");
        s.append(stuClass.getSimpleName() +"{ \n");

        Constructor[] stuConstructors = stuClass.getDeclaredConstructors();
        for(Constructor constructor : stuConstructors){
            s.append("\t"+Modifier.toString(constructor.getModifiers()) + " "+ constructor.getName()+"(");
            Class[] parameters = constructor.getParameterTypes();

            for (int i = 0; i < parameters.length ; i++) {
                s.append(" " + parameters[i].getSimpleName());
                if(i != parameters.length-1){
                    s.append(", ");
                }
            }
            s.append(")\n");
        }


        s.append("}");
        System.out.println(s);


    }


}
