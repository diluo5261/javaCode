/*
描述

A,B,C三个人是好朋友,每个人手里都有一些糖果,我们不知道他们每个人手上具体有多少个糖果,但是我们知道以下的信息：
A - B, B - C, A + B, B + C. 这四个数值.每个字母代表每个人所拥有的糖果数.
现在需要通过这四个数值计算出每个人手里有多少个糖果,即A,B,C。这里保证最多只有一组整数A,B,C满足所有题设条件。

**输入描述：**

输入为一行，一共4个整数，分别为A - B，B - C，A + B，B + C，用空格隔开。 范围均在-30到30之间(闭区间)。

**输出描述：**

输出为一行，如果存在满足的整数A，B，C则按顺序输出A，B，C，用空格隔开，行末无空格。 如果不存在这样的整数A，B，C，则输出No
*/
import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int a_Sub_b= scan.nextInt();
        int b_Sub_c= scan.nextInt();
        
        int a_Add_b = scan.nextInt();
        int b_Add_c= scan.nextInt();
        
        int A = (a_Sub_b + a_Add_b) >> 1;
        int C = (b_Add_c - b_Sub_c) >> 1;
        int B1 = (a_Add_b - a_Sub_b) >> 1;
        int B2 = (b_Sub_c + b_Add_c) >> 1;
        
        if(B1 == B2){
            System.out.println(A+" "+B1+" "+C);
        }else{
            System.out.println("No");
        }
        
        
        
    }
}