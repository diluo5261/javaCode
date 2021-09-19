package com.dilo.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class WebUtils {
//    public static void copyParamToBean(HttpServletRequest req,Object bean){
    public static <T> T  copyParamToBean(Map value, T bean){
        try {
            //把所有的数据都注入到user对象中
            BeanUtils.populate(bean,value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bean;
    }


    /**
     * 将字符串转换成int类型的数字
     * @param strInt
     * @return
     */
    public static int parseInt(String strInt){
        return Integer.parseInt(strInt);
    }
}
