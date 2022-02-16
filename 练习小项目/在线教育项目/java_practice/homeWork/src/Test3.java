public class Test3 {
    public static void main(String[] args) {
        String str = "-1234";
        System.out.println(StrToInt(str));

    }



    public static int  StrToInt(String str){
        if (str == null || str.length() == 0 || str == "") return 0;

        int len = str.length();
        if (len == 1 && str.charAt(0) == '0') return 0;

        int num = 0;

        int flag = 1;
        int i =0;
        if (str.charAt(i) =='-'){
            flag = -1;
            i++;
        }else if (str.charAt(i) == '+'){
            flag = 1;
            i++;
        }


        for (; i < len; i++) {

            if (str.charAt(i) >= '0' && str.charAt(i) <= '9'){
                num = num*10 + (str.charAt(i) -48);
            }else{
                return 0;
            }

        }

        if (flag == -1){
            num *= -1;
        }

        return num;

    }
}
