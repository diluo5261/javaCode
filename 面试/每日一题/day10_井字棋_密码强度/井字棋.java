import java.util.*;

public class Board {
    public boolean checkWon(int[][] board) {
        // write code here
        int N = board.length;
        int sum = 0;
        
        for(int i = 0;i<N;i++){
            for(int j=0;j<N;j++){
                //求每一行元素的和,行号不变,列号变
                sum += board[i][j];
            }
            
            if(sum == N){
                return true;
            }
        }
        
        for(int i = 0;i<N;i++){
            sum =0;
            for(int j=0;j<N;j++){
                //求每一列元素的和,列号不变,行号变
                sum += board[j][i];
            }
            
            if(sum == N){
                return true;
            }
        }
        
        //检查列元素的和
        for(int i = 0;i<N;i++){
            sum =0;
            for(int j=0;j<N;j++){
                //求每一列元素的和,列号不变,行号变
                sum += board[j][i];
            }
            
            if(sum == N){
                return true;
            }
        }
        
        
        //主对角线元素和
        sum =0;
        for(int i =0;i<N;i++){
            sum += board[i][i];
        }
        if(sum == N){
             return true;
        }
        
        sum=0;
        for(int i =0;i<N;i++){
            sum += board[N-i-1][N-i-1];
        }
        if(sum == N){
             return true;
        }
        
        return false;
    }
}