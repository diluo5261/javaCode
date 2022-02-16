public class HelloB extends HelloA{
    public HelloB(){
        System.out.println("B class");
    }

    static {
        System.out.println("B static");
    }

    public static void main(String[] args) {
        new HelloB();
    }
}

class HelloA{
    static{
        System.out.println("static A");
    }

    {
        System.out.println("A class");
    }
}
