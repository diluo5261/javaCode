package day11;
/*
一、选择题
1.C
2.A
3.A
4.D
5.C

二、填空题
1.6
2.String StringBuilder \ StringBuffer
3.3

四、智力题
方法一：请一个不是瞎子的人替他们分开
方法二：将每对袜子拆开，每人各拿一只，
 */
public class Day11Test {
    public static void main(String[] args) {

        String str = "abbcccffr";
        System.out.println(StringCompress(str));
    }



    public static String StringCompress(String str){
        int len = str.length();

        StringBuilder stringBuilder = new StringBuilder();

        int count =1;
        char tmp = str.charAt(0);

        for (int i = 1; i <len ; i++) {

            if (tmp == str.charAt(i)){
                count++;
            }else{
                stringBuilder.append(tmp);
                stringBuilder.append(count);

                tmp = str.charAt(i);
                count = 1;
            }
        }

        stringBuilder.append(tmp);
        stringBuilder.append(count);

        return stringBuilder.toString();
    }
}
