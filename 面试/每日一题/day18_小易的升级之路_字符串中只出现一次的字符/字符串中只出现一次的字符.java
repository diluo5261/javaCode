import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        while(scan.hasNext()){
            String str1 = scan.nextLine();
            
            Map<Character,Integer> map = new HashMap<>();
            
            for(int i =0;i<str1.length();i++){
                char ch = str1.charAt(i);
                map.put(ch,map.getOrDefault(ch,0)+1);
            }
            
            int i =0;
            for(;i<str1.length();i++){
                char ch = str1.charAt(i);
                if(map.get(ch) == 1){
                    System.out.println(ch);
                    break;
                }
            }
           
            if(i == str1.length()){
                System.out.println(-1);
            }

        }
    }
}