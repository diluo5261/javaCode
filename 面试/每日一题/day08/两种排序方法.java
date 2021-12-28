/*

考拉有n个字符串字符串，任意两个字符串长度都是不同的。考拉最近学习到有两种字符串的排序方法： 

1.根据字符串的字典序排序。例如：
"car" < "carriage" < "cats" < "doggies < "koala"
2.根据字符串的长度排序。例如：
"car" < "cats" < "koala" < "doggies" < "carriage"
考拉想知道自己的这些字符串排列顺序是否满足这两种排序方法，考拉要忙着吃树叶，所以需要你来帮忙验证。
*/

import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        String[] strs = new String[n];
        
        for(int i =0;i < n;i++){
            strs[i] = scan.next();
        }
        
        //开始比较
        if(isSortDir(strs) && isSortLength(strs)){
            System.out.println("both");
        }else if(isSortDir(strs)){
            System.out.println("lexicographically");
        }else if(isSortLength(strs)){
            System.out.println("lengths");
        }else{
            System.out.println("none");
        }
    }
    
    public static boolean isSortDir(String[] strs){
        int len = strs.length;
        
        for(int i = 0; i< len-1;i++){
            if(strs[i].compareTo(strs[i+1]) > 0) return false;
        }
        return true;
    }
    
    public static boolean isSortLength(String[] strs){
        int len = strs.length;
        
        for(int i = 0; i< len-1;i++){
            if(strs[i].length() > strs[i+1].length()) return false;
        }
        return true;
    }
}