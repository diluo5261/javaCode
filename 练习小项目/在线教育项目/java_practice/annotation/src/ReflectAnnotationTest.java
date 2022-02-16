import org.junit.Test;

public class ReflectAnnotationTest {
    @Test
    public void test1() throws ClassNotFoundException {

        //获取这个类
        Class c1 = Class.forName("MyAoontation");


        //判断这个类上面是否有@MyAnnotation
        boolean isExists =  c1.isAnnotationPresent(MyAoontation.class);
        if(isExists){
            MyAoontation m1 = (MyAoontation) c1.getAnnotation(MyAoontation.class);

            //获取对象的属性怎么办，和调接口没有区别
            int age = m1.age();
            System.out.println(age);
        }
    }

}
