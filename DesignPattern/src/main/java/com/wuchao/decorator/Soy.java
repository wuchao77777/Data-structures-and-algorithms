package com.wuchao.decorator;

/**
 * @author wu
 * @create 2021-04-06-15:45
 */
public class Soy extends Decorator{

    public Soy(Drink obj) {
        super(obj);
        // TODO Auto-generated constructor stub
        setDes(" 豆浆  ");
        setPrice(1.5f);
    }

}