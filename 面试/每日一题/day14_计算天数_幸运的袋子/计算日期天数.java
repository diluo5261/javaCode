import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            int year = scan.nextInt();
            int month = scan.nextInt();
            int day = scan.nextInt();
            
            int sum = 0;
            
            switch(month-1){
                case 12:
                    sum+=31;
                case 11:
                    sum+=30;
                case 10:
                    sum += 31;
                case 9 :
                    sum += 30;
                case 8 :
                    sum += 31;
                case 7 :
                    sum += 31;
                case 6 :
                    sum += 30;
                case 5 :
                    sum += 31;
                case 4 :
                    sum += 30;
                case 3 :
                    sum += 31;
                case 2 :
                    if(year % 400 == 0 || year % 4 == 0 && year % 100 != 0){
                        sum += 29;
                    }else{
                        sum += 28;
                    }
                case 1 :
                    sum += 31;
            }
            sum += day;
            System.out.println(sum);
        }
    }
}