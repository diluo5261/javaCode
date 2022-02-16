package test;

import org.junit.Test;

import java.util.Locale;

public class Test1 {
    private static void testDemo(){
        System.out.println("testDemo");
    }

    public static void main(String[] args) {
        ((Test1)null).testDemo();
    }


    @Test
    public void test1(){
        int a = 1;
        int b =2;

        int c = a ^ b;
        System.out.println(c);

    }


}
