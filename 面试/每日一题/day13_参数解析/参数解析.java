import java.util.*;

public class Main {
    public static void main(String[] args){
        
        Scanner scan=new Scanner(System.in);
        
        String str = scan.nextLine();
        int count =0;
        for(int i =0;i< str.length();i++){
            char ch = str.charAt(i);
            if(ch == '"'){
                do{
                    i++;
                    ch = str.charAt(i);
                }while(ch != '"');
            }
            
            if(ch == ' '){
                count++;
            }
        }
        
        System.out.println(count+1);
        
        boolean flag = true;
        for(int i =0;i < str.length();i++){
            char ch = str.charAt(i);
            
            //判断是否是双引号中的内容
           if(ch=='"'){
                flag = !flag;;
           }
            
            //除双引号和特殊空格以外的字符都要打印
            if(ch!=' '&& ch!='"'){
                System.out.print(ch);
            }
            
            //双引号中的空格需要打印
            if(ch==' '&& !flag){
                System.out.print(ch);
            }
            
            //双引号遇到空格,需要换行
            if(ch == ' ' && flag){
                System.out.println();
            }
        }
    }
}