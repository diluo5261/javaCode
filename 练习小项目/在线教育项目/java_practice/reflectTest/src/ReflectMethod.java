import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectMethod {
    @Test
    public void getPublicMethod() throws ClassNotFoundException, NoSuchMethodException {
        //获取public方法
        Class stuClass = Class.forName("Student");

        //获取public指定方法
        Method public_method_stu = stuClass.getMethod("hobby",String.class);
        System.out.println(public_method_stu.getName());

        //获取所有的public 方法
        Method[] methods = stuClass.getMethods();
        for(Method method : methods){
            System.out.println(method.getName());
        }

    }

    @Test
    public void getPrivateMethod() throws ClassNotFoundException, NoSuchMethodException {
        Class stuClass = Class.forName("Student");

        //获取指定的private对象
        Method method_private = stuClass.getDeclaredMethod("login",String.class,String.class);
        System.out.println(method_private.getName());

        //获取所有的方法 公有 + 私有
        Method[] methods = stuClass.getDeclaredMethods();

        for(Method method : methods){
            System.out.println(method.getName());
        }
    }

    @Test
    public void reflectInvokeMethod() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //1、获取Class类的对象
        Class stuClass = Class.forName("Student");

        //2、实例化Student对象
        Object obj_stu = stuClass.newInstance();

        //3、获取方法
        Method method_stu = stuClass.getDeclaredMethod("login",String.class,String.class);

        //获取方法名：
        System.out.println("方法名 ： "+method_stu.getName());
        //获取方法的返回值
        System.out.println("返回值 : " + method_stu.getReturnType());
        //获取权限修饰符
        System.out.println("权限修饰符" + Modifier.toString(method_stu.getModifiers()));

        //获取参数列表
        Class[] parameters = method_stu.getParameterTypes();
        for(Class parameter : parameters) {
            System.out.println(" 参数列表：" + parameter.getSimpleName());
        }



        //4、调用方法
        method_stu.setAccessible(true);
        Object retVal = method_stu.invoke(obj_stu,"tom","123");
        System.out.println(retVal);
    }

    //反编译方法
    @Test
    public void decompliedMethod() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //1、获取Class对象
        Class stuClass = Class.forName("Student");

        StringBuilder str = new StringBuilder();
        //获取Student类的权限修饰符
        str.append(Modifier.toString(stuClass.getModifiers()));
        str.append(" class ");
        str.append(stuClass.getSimpleName());
        str.append("{\n");

        //获取所有的方法
        Method[] methods = stuClass.getDeclaredMethods();
        for(Method method : methods){
            //显示格式：权限修饰符 + 返回值类型  +  方法名 + （参数列表）

            //获取方法的权限修饰符
            str.append("\t" + Modifier.toString(method.getModifiers())+" ");

            //获取方法的返回值
            str.append(method.getReturnType() + " ");

            //获取方法名
            str.append(method.getName() +"(");

            //获取参数列表
            Class[] parameters = method.getParameterTypes();
            for (int i = 0; i < parameters.length; i++) {
                str.append(parameters[i].getSimpleName());
                if(i != parameters.length-1){
                    str.append(", ");
                }
            }
            str.append(")\n");
        }

        str.append("}");
        System.out.println(str);
    }
}
