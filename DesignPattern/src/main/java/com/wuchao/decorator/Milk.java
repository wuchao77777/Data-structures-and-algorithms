package main.java.com.wuchao.decorator;

/**
 * @author wu
 * @create 2021-04-06-15:46
 */
public class Milk extends Decorator {

    public Milk(Drink obj) {
        super(obj);
        // TODO Auto-generated constructor stub
        setDes(" 牛奶 ");
        setPrice(2.0f);
    }

}