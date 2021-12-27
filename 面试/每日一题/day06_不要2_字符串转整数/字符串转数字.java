public class Solution {
    public int StrToInt(String str) {
        if(str == null || str.isEmpty()){
            return 0;
        }
        
        int i = 0;
        char symbol = str.charAt(0);
        boolean flag = false;
        
        if(symbol == '-'){
            flag=true;
            i =1;
        }
        
        if(symbol == '+'){
            i =1;
        }
        
        int sum =0;
        while(i < str.length()){
            char ch = str.charAt(i);
            if(ch >= '0' && ch <= '9'){
                sum = sum*10 + (ch -48);
            }else{
                return 0;
            }
            i++;
        }
        
        if(flag){
            sum *= -1;
        }
        
        return sum;
    }
}