import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class ReflectConstructor {

    @Test
    public void reflectCreateInstance() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //1、获取类的对象
        Class stuClass = Class.forName("Student");

        //无参构造器实例化对象方法一：
        //2、获取无参构造器
        Constructor constructor_stu = stuClass.getConstructor();

        //3、通过无参构造器实例化对象
        Object obj = constructor_stu.newInstance();

        //无参构造器实例化对象方法二：
        //系统会自动调用无参构造器
        Object obj2 = stuClass.newInstance();
        System.out.println(obj2);

        //获取有参的构造方法
        Constructor constructor_stu2 = stuClass.getConstructor(String.class,int.class);
        Object obj_2 = constructor_stu2.newInstance("jone",25);
        System.out.println(obj_2);
    }

    @Test
    public void decompiledConstructor() throws ClassNotFoundException {
        //1、获取类的对象
        Class stuClass = Class.forName("Student");

        StringBuilder str = new StringBuilder();
        //获取类的修饰符
        str.append(Modifier.toString(stuClass.getModifiers()));
        str.append(" class ");

        //获取类的名字
        str.append(stuClass.getSimpleName());
        str.append("{\n");

        //获取所有的构造方法
        Constructor[] constructors = stuClass.getDeclaredConstructors();
        //显示样式： 权限修饰符  + 构造名 （参数列表）
        for(Constructor constructor : constructors){
            str.append(" "+ Modifier.toString(constructor.getModifiers()) + " ");
            str.append(constructor.getName() + " (");

            Class[] parameters = constructor.getParameterTypes();
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
