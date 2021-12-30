/*
描述

请计算n*m的棋盘格子（n为横向的格子数，m为竖向的格子数）从棋盘左上角出发沿着边缘线从左上角走到右下角，总共有多少种走法，要求不能走回头路，即：只能往右和往下走，不能往左和往上走。
*/

import java.util.*;

public class Main{
    
    public static int count(int n, int m){
        if(n == 1 && m >=1 || m ==1 && n >= 1){
            return n+m;
        }
        
        return count(n-1,m) + count(n,m-1);
    }
    
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
             int n = scan.nextInt();
             int m = scan.nextInt();
             System.out.println(count(n,m));
        }
    }
}