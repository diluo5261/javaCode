package day12;
/*
* 1.B
* 2.AD
* 3.C
* 4.D
* 5.A
*
* 二、填空题
* 1.3，2
* 2.数组
* 3.5 ，5 ，4 ，6
*
* 四。
* 有三顶帽子
* 1.如果有一顶帽子，当事人看到别人的帽子全是白色，则知道自己的帽子是黑色，第一次关灯就应该有掌声
* 2.第一次关灯没有掌声，说明帽子不止有一顶，假设有两顶，当事人看到外面只有一顶黑帽子，
* 则能判断出自己头上的也是黑帽子，第二次关灯就应该有掌声
*
* 3.第二次关灯，也没有掌声，说明帽子的数量多余两顶，如果有三顶，当事人看到外面帽子有两顶黑帽子，则能判断出自己也是黑帽子
* 第三次关灯出现了把掌声，说明有三个黑帽子
* */
public class Day12Test {
    public static void main(String[] args) {
        System.out.println(StrToInt("2147483647"));
        System.out.println(StrToInt("2147483648"));
        System.out.println(StrToInt("123abcd"));
    }

    public static int StrToInt(String str){
        int num = 0;
        int i = 0;
        if (str.charAt(0) == '-'){
            i =1;
        }
        for (; i <str.length() ; i++) {
             if (str.charAt(i) >= '0' && str.charAt(i) <= '9'){
                num = num *10 + (str.charAt(i) - '0');
            }else{
                return 0;
            }
        }

        if (str.charAt(0) != '-'){
            if (num < 0){
                return 2147483647;
            }
        }

        return num;
    }
}
