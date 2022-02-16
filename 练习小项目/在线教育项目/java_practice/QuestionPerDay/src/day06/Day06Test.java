package day06;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Day06Test {

    //算法题:找出无序数组中,出现过数组长度一半的数字
    //解题思路:对数组进行排序,中间的数为超过数组长度一般的数字
   /* @Test
    public void overHalfNum(){
        int [] array = {1,2,3,2,2,2,5,4,2};

        Arrays.sort(array);
        int halfLen = array.length / 2;
        System.out.println(array[halfLen]);
    }*/

    //方法二:使用hashMap
    //将数组值存储为hashMap的key值,如果是第一次添加,则value值为1,如果不是首次添加,先获取value值,再将value加一
    @Test
    public void overHalfNum1(){
        int [] array = {1,2,3,2,2,2,5,4,2};
        Map<Integer,Integer> map = new HashMap<>();
        for (int num:array ) {
            //判断
            if(map.containsKey(num)){
                int value = map.get(num);
                map.put(num,++value);
            }else{
                map.put(num,1);
            }
        }

        int halfLen = array.length / 2;
        for (Integer count : map.keySet()) {
            if (map.get(count) >= halfLen) {
                System.out.println(count);
                break;
            }
        }


    }

    /*
    * 智力题:假设你站在镜子前，抬起左手，抬起右手，看看镜中的自己。当你抬起左手时，镜中的自己抬起的似乎是右手。可
    *是当你仰头时，镜中的自己也在仰头，而不是低头。为什么镜子中的影像似乎颠倒了左右，却没有颠倒上下？
    *

    * 镜子中所呈现的像,都会遵循平面的成像规律:镜子中所呈现的是大小相等,左右方向相反的虚像.
    * 换句话说,镜子颠倒的不是上下,也不是左右,而是前后
    *
    * */


}
