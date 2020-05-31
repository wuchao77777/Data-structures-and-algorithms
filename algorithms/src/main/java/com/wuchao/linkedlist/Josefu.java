package com.wuchao.linkedlist;

import com.sun.org.apache.xpath.internal.SourceTree;
import jdk.nashorn.internal.ir.ReturnNode;

/**
 * @ProjectName: Data-structures-and-algorithms
 * @Package: com.wuchao.linkedlist
 * @ClassName: Josefu
 * @Author: 吴超
 * @Description: ${description}
 * @Date: 2020/5/31 14:48
 * @Version: 1.0
 */
public class Josefu {
    public static void main(String[] args) {
           //测试一把看看构建一个环形的单向链表是否可以
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(125);//加入5个小孩的节点
        circleSingleLinkedList.showBoy();

        //测试一把小孩出圈是否正确
        circleSingleLinkedList.countBoy(10, 20, 125); // 2->4->1->5->3
        //String str = "7*2*2-5+1-5+3-3";
    }

}

class CircleSingleLinkedList {
    //创建以恶搞first节点，当前没有编号
    private Boy first = null;

    //增加一个小孩节点，创建成一个环形链表
    public void addBoy(int nums) {
        //nums做一个数据的检验
        if (nums < 1) {
            System.out.println("请输入正确的nums");
            return;
        }
        //辅助指针，用于帮助我构建环形链表
        Boy curBoy = null;
        //使用for用来创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            if (i == 1) {
                //构成环
                first = boy;
                first.setNext(first);
                curBoy = first; //让curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }


    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("没有增加任何一个小孩");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n ", curBoy.getNo());
            if (curBoy.getNext() == first) {//说明遍历完毕
                break;
            }
            curBoy = curBoy.getNext(); //curBoy后移、
        }
    }

    /**
     *
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo,int countNum,int nums){
        // 先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误， 请重新输入");
            return;
        }
        // 创建要给辅助指针,帮助完成小孩出圈
        Boy helper = first;
        // 需求创建一个辅助指针(变量) helper , 事先应该指向环形链表的最后这个节点
        while (true) {
            if (helper.getNext() == first) { // 说明helper指向最后小孩节点
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让 first 和  helper 移动 k - 1次
        for(int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first 和 helper 指针同时 的移动  m  - 1 次, 然后出圈
        //这里是一个循环操作，知道圈中只有一个节点
        while(true) {
            if(helper == first) { //说明圈中只有一个节点
                break;
            }
            //让 first 和 helper 指针同时 的移动 countNum - 1
            for(int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first); //

        }
        System.out.printf("最后留在圈中的小孩编号%d \n", first.getNo());
    }



}

/**
 * 创建一个Boy类表示一个节点
 */
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

}
