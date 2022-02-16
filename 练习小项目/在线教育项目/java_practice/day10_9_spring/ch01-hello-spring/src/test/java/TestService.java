import com.dilo.service.impl.SomeServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestService {
    @Test
    public void test01(){

        //1.读取配置文件,获取spring容器对象
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        //2.从spring容器中 通过 id 获取需要使用的对象
        SomeServiceImpl service = (SomeServiceImpl) context.getBean("someService");

        service.doSome();

    }
}
