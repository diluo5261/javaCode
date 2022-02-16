import com.dilo.ba06.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest06 {

    @Test
    public void test01(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext06.xml");

        Student student = (Student) context.getBean("student");
        System.out.println(student);
    }
}
