
//注解中定义属性

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface MyAoontation {
    /**
     * 我们通常在注解当中可以定义属性，以下这个MyAnnotation的name属性
     * 看着像一个方法，实际上我们称之为属性
     *
     * 注解当中的属性可以是哪些类型
     *
     * 如果一个注解
     * @return
     */
    String name();

    int age() default 0;
}


//只允许该注解可以标注类、方法
@Target({ElementType.TYPE,ElementType.METHOD})

//希望这个注解能被反射
    @Retention(RetentionPolicy.RUNTIME)
@interface test1{

}
