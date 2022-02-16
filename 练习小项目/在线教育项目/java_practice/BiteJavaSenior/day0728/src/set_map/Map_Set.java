package set_map;

import org.junit.Test;

import java.util.*;

public class Map_Set {
    public static void main(String[] args) {

    }

    //题目一:有10个数据,找到第一个重复的数据
    @Test
    public void findDuplicatedDate(){
        ArrayList<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i <100000 ; i++) {
            list.add(random.nextInt(100000));
        }

        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i <list.size() ; i++) {
            if (hashSet.contains(list.get(i))){
                System.out.println("第一个重复的数据为:"+ list.get(i));
                break;
            }else{
                hashSet.add(list.get(i));
            }
        }
    }

    //有10万个数据,去除所有重复的元素
    @Test
    public void removeDuplication(){
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i <100000 ; i++) {
            list.add(random.nextInt(100000));
        }

        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i <list.size() ; i++) {
            hashSet.add(list.get(i));
        }

        System.out.print(hashSet);
    }

    //有10万的数据,统计每个数据出现了多少次
    @Test
    public void dataCount(){
        List<Integer> list = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i <100000 ; i++) {
            list.add(random.nextInt(100000));
        }

        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i = 0; i <list.size() ; i++) {
            if(hashMap.containsKey(list.get(i))){
                hashMap.put(list.get(i),hashMap.get(list.get(i))+1);
            }else{
                hashMap.put(list.get(i),1);
            }
        }

        for (Map.Entry<Integer,Integer> entry : hashMap.entrySet()) {
            System.out.println("数据 " + entry.getKey() + "出现过"+ entry.getValue()+"次");

        }
    }
}
