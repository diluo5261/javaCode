package day09;
/*
* 一、选择题
* 1.
* 2.D
* 3.A
* 4.B
* 5.B
*
* 二、填空题
* 1、interface
* 2、length  length（）
* 3、5
*
*
* 四、
*
* */


import org.junit.Test;

public class day09Test {
    public static void main(String[] args) {
        String str = "abcdef";
        int k = 2;
        str = reverseNum(str,0,k-1);
        str = reverseNum(str,k,str.length()-1);
        str = reverseNum(str,0,str.length()-1);
        System.out.println(str);
    }

    public static String reverseNum(String str,int start,int end){
        char[] chars = str.toCharArray();
        while(end > start){
            char tmp = chars[start];
            chars[start] = chars[end];
            chars[end] = tmp;
            start++;
            end--;
        }
        return new String(chars);





    }
}
