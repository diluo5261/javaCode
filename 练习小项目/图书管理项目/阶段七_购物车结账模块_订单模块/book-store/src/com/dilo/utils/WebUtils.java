package com.dilo.utils;

import com.dilo.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils {

    //未优化
   /* public static void copyParamTOBean(HttpServletRequest  request, Object bean){
        try {
            BeanUtils.populate(bean,request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }*/

    /**
     * 把map中的值注入到对应javabean当中属性中,使用map 作为参数,耦合度更低,使用跟家灵活
     * @param map
     * @param bean
     */
    //优化一:将参数改为map
    /*public static void copyParamTOBean(Map map, Object bean){
        try {
            BeanUtils.populate(bean,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }*/

    //优化二:将代码整合成1行
   /* public static Object copyParamTOBean(Map map, Object bean){
        try {
            BeanUtils.populate(bean,map);
            return bean;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;

    }*/

    //优化三:使用泛型,不在需要强制类型转化
    public static <T> T copyParamTOBean(Map map, T bean){
        try {
            BeanUtils.populate(bean,map);
            return bean;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static int parseInt(String num, Integer defaultValue){

        try {
            return Integer.parseInt(num);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }
}
