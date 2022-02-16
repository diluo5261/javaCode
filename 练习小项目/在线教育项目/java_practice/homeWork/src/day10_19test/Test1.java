package day10_19test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       int n = scan.nextInt();

       String[] strings = new String[n];

        for (int i = 0; i < n; i++) {
            strings[i] = scan.nextLine();

        }

       boolean lex = isLexicographically(strings);
       boolean len = isLength(strings);

       if (lex && len){
           System.out.println("both");
       }else if(lex && !len){
           System.out.println("lexicographically");
       }else if (!lex && len){
           System.out.println("lengths");
       }else{
           System.out.println("none");
       }

    }

    public static boolean isLexicographically(String[] strings){
        for (int i= 0; i<strings.length-1;i++){
            if (strings[i+1].compareTo(strings[i]) < 1){
                return false;
            }
        }
        return true;
    }

    public static boolean isLength(String[] strings){

        int len = strings.length;
        for (int i = 0; i < len-1; i++) {
            if (strings[i].length() > strings[i+1].length()){
                return false;
            }
        }
        return true;
    }


}
