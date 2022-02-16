import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectField {

    @Test
    public void getPublicField() throws ClassNotFoundException, NoSuchFieldException {
        //获取单个属性
        Class stuClass = Class.forName("Student");
        Field stuName = stuClass.getField("name");
        System.out.println(stuName.getName());

        //获取所有public属性
        Field[] fields = stuClass.getFields();
        for(Field field : fields){
            System.out.println(field.getName());
        }
    }

    @Test
    public void getPrivateField() throws ClassNotFoundException, NoSuchFieldException {
        //获取Class类的对象
        Class stuClass = Class.forName("Student");

        //获取单个私有的方法
        Field stu_private_method = stuClass.getDeclaredField("isHigh");
        System.out.println(stu_private_method.getName());

        //获取所有的私有方法
        Field[] fields = stuClass.getDeclaredFields();
        for(Field field : fields){
            System.out.println(field.getName());
        }
    }

    @Test
    public void reflectAccessField() throws ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Class stuClass = Class.forName("Student");
        Object obj_stu = stuClass.newInstance();

        Field field_public = stuClass.getField("name");
        field_public.set(obj_stu,"tom");
        System.out.println(field_public.get(obj_stu));


        Field field_private = stuClass.getDeclaredField("sex");
        field_private.setAccessible(true);
        field_private.set(obj_stu,"男");
        System.out.println(field_private.get(obj_stu));
    }

    @Test
    public void decompiledField() throws ClassNotFoundException {

        Class stuClass = Class.forName("Student");
        StringBuilder str = new StringBuilder();

        // public class Student {}
        //获取类的 权限修饰符
        str.append(Modifier.toString(stuClass.getModifiers()));
        str.append(" class ");

        //获取类名
        str.append(stuClass.getSimpleName());

        str.append("{\n");

        //获取类的所有属性
        Field[] fields = stuClass.getDeclaredFields();

        //格式: 权限修饰符  +  数据类型  + 属性名 + ;
        for(Field field : fields){
            str.append("\t");
            //获取属性的权限修饰符
            str.append(Modifier.toString(field.getModifiers()));
            str.append(" ");

            //获取属性的类型
            str.append(field.getType().getSimpleName());
            str.append(" ");

            //获取属性的名字
            str.append(field.getName());
            str.append(";\n");


        }
        str.append("}");
        System.out.println(str);
    }
}
