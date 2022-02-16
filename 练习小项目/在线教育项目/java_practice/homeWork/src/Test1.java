import java.util.Scanner;

public class Test1 {


    public static void main(String[] args){
        int A = 0;
        int B = 0;
        int C= 0;
        int AAndB = 0;
        int ASubB = 0;
        int BAndC = 0;
        int BSubC =0;



        Scanner scan = new Scanner(System.in);
        ASubB = scan.nextInt();
        BSubC = scan.nextInt();
        AAndB = scan.nextInt();
        BAndC = scan.nextInt();

        A = (AAndB + ASubB)/2;
        B = (BAndC + BSubC)/2;
        C= BAndC - B;

        if (A >= 0 && B >= 0 && C >=0){
            System.out.println(A +" "+B+" "+C);
        }else{
            System.out.println("No");
        }
    }

}
