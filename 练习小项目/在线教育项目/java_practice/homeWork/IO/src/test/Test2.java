package test;

public class Test2 {

    public static int sum(int n , int m){
        if (m ==0 || n ==0){
            return 1;
        }

        return sum(n-1,m) + sum(n,m-1);
    }

    public static void main(String[] args) {
        System.out.println(sum(1, 2));

    }
}
