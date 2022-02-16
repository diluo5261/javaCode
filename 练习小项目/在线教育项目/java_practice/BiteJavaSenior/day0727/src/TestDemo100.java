import java.util.*;

public class TestDemo100 {
    public static void main(String[] args) {
        Map<Integer,Integer> m1 = new HashMap<>();

        Random random = new Random();
        for (int i = 0; i < 10_0000; i++) {

            int randNum = random.nextInt(10000);

            if(m1.containsKey(randNum)){
                int val=m1.get(randNum);
                m1.put(randNum,val+1);
            }else{
                m1.put(randNum,1);
            }
        }


    }




    public static void main1(String[] args) {
        Set<Integer> s1 = new HashSet<>();

        Random random = new Random();
        for (int i = 0; i < 10_0000; i++) {

            int randNum = random.nextInt(10000);

            if(s1.contains(randNum)){
                System.out.println(randNum);
                break;
            }else{
                s1.add(randNum);
            }

        }





    }
}
