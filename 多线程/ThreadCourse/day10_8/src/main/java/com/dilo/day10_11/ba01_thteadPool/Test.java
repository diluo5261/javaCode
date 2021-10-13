package com.dilo.day10_11.ba01_thteadPool;

import java.util.HashMap;
import java.util.Map;

public class Test extends Base{

    public static void main(String[] args) {
        String str = "i like beijing.";

        String [] array = str.split(" ");

        for(int i = array.length-1; i>= 0;i--){

            if(i >0){
                System.out.print(array[i]+" ");
            }else{
                System.out.print(array[i]);
            }
        }

    }
}




