package day10;

import java.util.*;

/*
1、A
2、ABCD
3、C
4、D
5、B

二、填空题
1、InputStream  OutputStream
2、ClientSocket  SeverSocket
3、26



 */
public class Day10Test {
    public static void main(String[] args) {
        String str = "I am a student";
       String[] strs = str.split(" ");
       Stack<String> list = new Stack<>();
        for (String s: strs ) {
            list.push(s);
        }

        while(!list.isEmpty()){
            System.out.print(list.pop() + " ");


        }


    }
}
