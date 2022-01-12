import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            int row = scan.nextInt();
            int col = 2 * row -1;
            int[][] array = new int[row][col];
            
            //初始化
            for(int i =0;i<row;i++){
                for(int j = 0; j<col;j++){
                    array[i][j] = 0;
                }
            }
            
            array[0][0] = 1;
            
            for(int i=1;i<row;i++){
                array[i][0] = array[i][2*i] = 1;
                
                for(int j = 1;j<2*i;j++){
                    if(j == 1){
                        array[i][j] = array[i-1][j]+array[i-1][j-1];
                    }else{
                        array[i][j] = array[i-1][j]+array[i-1][j-1]+array[i-1][j-2];
                    }
                }
            }
            int k=0;
            for(;k<col;k++){
                if(array[row-1][k] % 2 == 0){
                    System.out.println(k+1);
                    break;
                }
            }
            
            if(k == col){
                System.out.println(-1);
            }

        }
    }
}