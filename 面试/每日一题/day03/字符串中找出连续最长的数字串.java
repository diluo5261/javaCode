import java.util.*;
public class Main{
    public static void main(String[] args){
        //读取字符串
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        
        
        //查找字符串中连续最长的数字串
        StringBuilder cur = new StringBuilder();
        StringBuilder res = new StringBuilder();
        int i =0;
        for(;i< str.length();i++){
            
            char ch = str.charAt(i);
            if(ch >='0' && ch <= '9'){
                cur.append(ch);
            }else {
                if(cur.length() > res.length()){
                    res = cur;
                }
                cur = new StringBuilder();
            }
        }
        if(i == str.length() && cur.length() > res.length()){
            res = cur;
        }
        System.out.println(res.toString());
    }
}