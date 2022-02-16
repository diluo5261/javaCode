package day08;

import org.junit.Test;

public class NULL {
    public static void print(){
        System.out.println("MTDP");
    }
    public static void main(String[] args) {
        try{
            ((NULL)null).print();
        }catch(NullPointerException e){
            System.out.println("NullPointerException");
        }
    }


    @Test
    public void test(){
        int a4 =10;
        a4 = a4++;
        System.out.println(a4);


    }
}
