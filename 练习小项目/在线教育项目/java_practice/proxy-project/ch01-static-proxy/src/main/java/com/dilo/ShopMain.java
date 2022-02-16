package com.dilo;

import com.dilo.shop.TaoBao;

public class ShopMain {
    public static void main(String[] args) {
        //创建代理的商家淘宝对象
        TaoBao taoBao = new TaoBao();
        float sell = taoBao.sell(1);

        System.out.println(sell);
    }
}
