/**
 *1、注解，或者叫做注释，英文单词是：Annotation
 * 2、注解Annotation是一种引用数据类型。编译之后也生成xxx.class文件
 *3、自定义注解
 * 【修饰符列表】 @interface 注解类型名{
 *
 * }
 *
 *
 * 注解怎么使用，出现在哪里
 * 第一：注解使用时的语法格式
 * @注解类型名
 *
 * 第二：注解可以出现在类上，属性，方法，类上面，注解上
 *
 *
 * JDK内置注解
 * @Deprecated
 *
 * 常见的元注解
 * Target
 * 1、这是一个元注解，用来标注“注解模型”的注解
 * 这个Target注解用来标注“被标注的注解”可以出现在哪些位置
 *
 * @Target（ElementType）：表示“被标注的注解”，只能出现在方法上
 *
 * Retention
 * 用来标注“被标注的注解最终保存在哪里
 *
 * @Retention（RetentionPolicy.SOURCE）:表示该注解只被保留在java源文件中
 * @Retention（RetentionPolicy.CLASS）:表示该注解被保存在class文件中
 * @Retention（RetentionPolicy.RUNTIME）:表示该注解被保存在class文件中，并且可以被反射
 *
 *
 */

public class AnnotationTest1 {

    //报错原因，如果一个注解中有属性，那么必须给属性赋值,除非该属性指定了默认值
    //如果只有一个注解，且注解名字是value时，此时赋值时属性名可以省略
    //@MyAoontation(属性名 = 属性值，属性名 = 属性值)
    @MyAoontation(name = "zhangsan")
    public void doSome(){

    }
}
