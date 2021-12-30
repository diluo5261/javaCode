/*
描述:

给定两个int **A** 和 **B**。编写一个函数返回A+B的值，但不得使用+或其他算数运算符。
*/
import java.util.*;

public class UnusualAdd {
    public int addAB(int A, int B) {
        
        if(B == 0) return A;
        
        int sum = 0;
        int carry = 0;
        
        while(B != 0){
            sum = A ^ B;
            carry = (A & B) << 1;
            A =sum;
            B = carry;
        }
        return A;
    }
}