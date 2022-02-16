package day11;

class HelloA {
    public HelloA() {
        System.out.println("HelloA");
    }

    {
        System.out.println("i am A class");
    }

    static {
        System.out.println("static A");
    }
}
public class TestClass extends HelloA{
    public TestClass() {
        System.out.println("TestClass");
    }
    {
        System.out.println("i am TestClass");
    }
    static {
        System.out.println("static TestClass");
    }

    public static void main(String[] args) {

        new TestClass();
    }
}
