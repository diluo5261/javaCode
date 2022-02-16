package com.dilo.factory;

import com.dilo.service.UsbSell;

//目标类:金士顿厂家
public class UsbKingFactory implements UsbSell {

    @Override
    public float sell(int amount) {
        /*
        一个128GU盘是 85元
        后期根据amount , 可以实现不同的 价格
         */
        return 85.0f;
    }
}
