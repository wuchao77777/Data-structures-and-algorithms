package com.wuchao.decorator;

/**
 * @author wu
 * @create 2021-04-06-15:45
 */
public class Chocolate extends Decorator {

    public Chocolate(Drink obj) {
        super(obj);
        setDes(" 巧克力 ");
        setPrice(3.0f); // 调味品 的价格
    }

}