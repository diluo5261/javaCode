import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        String str2 = scan.nextLine();
        
        Set<Character> setActual = new HashSet<>();
        for(char ch : str2.toUpperCase().toCharArray()){
            setActual.add(ch);
        }
        
        Set<Character> setBroken = new HashSet<>();
        for(char ch : str.toUpperCase().toCharArray()){
            if(!setActual.contains(ch) && !setBroken.contains(ch)){
                setBroken.add(ch);
                System.out.print(ch);
            }
        }
    }
}