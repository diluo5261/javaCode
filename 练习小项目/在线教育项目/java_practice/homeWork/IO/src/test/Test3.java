package test;

public class Test3 {

    public static void main(String[] args) {

        int[][] board = new int[][]{{1,0,1},{1,-1,-1},{1,-1,0}};

        boolean b = checkWon(board);
        System.out.println(b);


    }




    public static boolean checkWon(int[][] board) {
        // write code here

        int row = board.length;
        int col = board[0].length;

        //判断行
        for (int i = 0; i < row; i++) {
            int sum = 0;
            for (int j = 0; j < col; j++) {
                sum += board[i][j];
            }
            if (sum == 3) {
                return true;
            }
        }

        //判断列
        for (int i = 0; i < col; i++) {
            int sum = 0;
            for (int j = 0; j < row; j++) {
                sum += board[j][i];
            }
            if (sum == 3) {
                return true;
            }
        }

        //判断对角线
        int tmp = 0;
        for (int i = 0; i < row; i++) {
            tmp += board[i][i];
        }
        if (tmp == 3){
            return true;
        }

        tmp = 0;
        for (int i = 0; i < row; i++) {
            tmp += board[row-i][row-i];
        }
        if (tmp == 3){
            return true;
        }


        return false;
    }


}
