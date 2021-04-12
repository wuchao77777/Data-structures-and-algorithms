package com.wuchao.linkedlist;

import org.jcp.xml.dsig.internal.dom.DOMUtils;

/**
 * @author wu
 * @create 2020-04-13-22:35
        */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表的测试");
        HeroNode2 heroNode1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 heroNode2 = new HeroNode2(2, "吴用", "智多星");
        HeroNode2 heroNode3 = new HeroNode2(3, "林冲", "豹子头");
        HeroNode2 heroNode4 = new HeroNode2(4, "武松", "打虎");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(heroNode1);
        doubleLinkedList.add(heroNode2);
        doubleLinkedList.add(heroNode3);
        doubleLinkedList.add(heroNode4);

        doubleLinkedList.list();
        //修改
        HeroNode2 heroNode5 = new HeroNode2(4, "武松1", "打虎1");
        System.out.println("修改节点");
        doubleLinkedList.update(heroNode5);
        doubleLinkedList.list();
        //删除
        doubleLinkedList.deleteNode(2);
        System.out.println("删除后的链表结构");
        doubleLinkedList.list();
        //增加 指定节点
        //根据位置进行增加
        System.out.println();
        doubleLinkedList.addByOrder(heroNode2);
        System.out.println("根据位置进行增加之后");
        doubleLinkedList.list();
    }
}

class DoubleLinkedList {
    //链表头  节点头不要东， 不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    //遍历双向链表的方法
    //显示链表[遍历]
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点，不能动，因此我们需要一个辅助的变量来遍历
        HeroNode2 temp = head.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
                //输出节点信息
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }

    public void add(HeroNode2 heroNode2) {
        //因为头节点不能动， 因此我们需要一个辅助遍历temp;
        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            ///如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环的时候 temp就到链表的最后
        //将最后的这个节点的next,指向了新的节点。
        temp.next = heroNode2;
        heroNode2.pre = temp;
    }

    //修改节点的信息，根据no编号来修改，即no编号不能改
    public void update(HeroNode2 newHeroNode) {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号。
        //定义一个临时的辅助变量
        HeroNode2 temp = head.next;
        Boolean flag = false;
        while (true) {
            if (temp == null) {
                System.out.println("链表结尾");
                ;
                break;
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            //表示修改成功
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("未找到编号 %d 的节点，不能修改\n", newHeroNode.no);
        }
    }


    //第二种在增加英雄 ，根据排名将英雄插入到指定的位置(如果有指定的排名，则增加成功，并给出提示);
    public void addByOrder(HeroNode2 heroNode) {
        //1因为头的节点不能动因此我们仍然通过一个辅助指针(变量)来帮助我们找到增加的位置
        //2因为是单链表 因为我们找的是temp是位于 增加位置的前一个节点，否则插入不了。
        HeroNode2 temp = head;
        boolean flag = false; //flag标志增加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {//说明temp已经在链表的最后了
                break;
            }
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {//位置已经找到,就在temo后面。
                flag = true; //编号存在
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("准备插入的英雄编号 %d 已经存在了 不能加入\n", heroNode.no);
        } else {
            System.out.println("开始增加");
            //插入节点和 判断到节点的后一个进行联系

            temp = heroNode.pre;
            temp.next.pre=heroNode;
            //插入节点和 判断到节点的前一个进行联系
            heroNode.next = temp.next;
            heroNode=temp.next;

        }
    }

    //从双向列表中删除一个节点。
    //说明  1 对于双向链表，可以直接删除节点，而不需要像单向链表一样找到前一个
    //删除节点信息，
    public void deleteNode(int no) {
        //思路。1.先找到待删除节点的前一个节点
        //2 然后将这个节点的下一个引用指向下下一个。（直接跳过中间那个节点） 失去引用会被回收。
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        Boolean flag = false;  //用此判断是否找到待删除节点的前一个节点
        while (true) {
            if (temp == null) {
                System.out.println("链表结尾");
                break;
            }
            if (temp.no == no) {
                //找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            //temp.next = temp.next.next; 单项链表的删除
            //删除该节点
            temp.pre.next = temp.next;   //节点前一个向后一个连接
            //代码有问题  如果是删除最后一个节点就不需要下面这句话  如果执行会空指针
            if (temp.next != null) {
                temp.next.pre = temp.pre;   //节点后一个向前一个连接
            }

        } else {
            System.out.printf("未找到此节点 %d,无法删除\n", no);
        }
    }

}

class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; //指向下一个节点  默认为null
    public HeroNode2 pre; //指向前一个节点  默认为null

    //构造器
    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方法，我们重新toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname +
                '}';
    }
}
