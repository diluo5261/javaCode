package day13;
/*
* 一、选择题
* 1.AD
* 2.B
* 3.C
* 4.A
* 5.A
*
* 二、填空题
* 1.能
* 2.java虚拟机 ，JDK JRE JVM
* 3.#define Min(x,y) (x) > (y)?(y):(x)
*
* 四、智力题
* 将四个瓶子依次编号1~4
* 瓶子  4   3   2   1
* 从编号1的瓶子中取出1颗药丸，2号瓶子取出2颗药丸，3号瓶子取出3颗药丸，4号瓶子取出4颗药丸
* 一起称重，称重的重量 - 标准的重量 = 瓶子的标号
*
* */
public class Day13Test {
    public static void main(String[] args) {
        System.out.println(CountNum("bit666keji123"));

    }

    public static int CountNum(String str) {
        int count =0;
        for (int i = 0; i <str.length() ; i++) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9'){
                count++;
            }
        }
        return count;
    }
}
