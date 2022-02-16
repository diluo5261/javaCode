package com.dilo.shop;

import com.dilo.factory.UsbKingFactory;
import com.dilo.service.UsbSell;

//商家,代理金士顿U盘的销售
public class TaoBao implements UsbSell {

    //声明商家代理的厂家具体是谁
    private UsbKingFactory factory = new UsbKingFactory();



    //实现销售u盘的功能
    @Override
    public float sell(int amount) {
        //向厂家发送订单,告诉厂家,我买了U盘,厂家发货
        float sell = factory.sell(amount);

        //商家需要加价,也就是代理需要增加价格
        sell += 25; //增强功能,代理类在完成目标类方法调用后,增强了功能

        //在目标类的方法调用后,你做的其他功能,都是增强的意思
        return sell;
    }
}
