import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str1;
        String str2;
        
        while((str1 = reader.readLine()) != null){
            str2 = reader.readLine();
            System.out.println(getMaxLen(str1,str2));
        }
        
    }
    
    public static int getMaxLen(String str1,String str2){
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        int len1 = arr1.length;
        int len2 = arr2.length;
        
        int[][] maxSubLen = new int[len1 + 1][len2 + 1];
        int maxLen = 0;
        
        for(int i=1;i<=len1;i++){
            for(int j =1;j<=len2;j++){
                if(arr1[i-1] == arr2[j-1]){
                    
                    //f(i,j) = f(i-1,j-1) + 1
                    maxSubLen[i][j] = maxSubLen[i-1][j-1]+1;
                    if(maxLen < maxSubLen[i][j]){
                        maxLen = maxSubLen[i][j];
                    }
                }
            }
        }
        return maxLen;
    }
} 