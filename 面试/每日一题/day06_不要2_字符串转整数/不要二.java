import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int W = scan.nextInt();
        int H = scan.nextInt();
        
        int[][] array = new int[W][H];
        
        int count = 0;
        for(int i =0;i<W;i++){
            for(int j=0;j<H;j++){
                if(array[i][j] == 0){
                    count++;
                    
                    if(i+2 < W){
                        array[i+2][j]=1;
                    }
                    
                    if(j+2 < H){
                        array[i][j+2] = 1;
                    }
                }
            }
        }
        System.out.println(count);
    }
}