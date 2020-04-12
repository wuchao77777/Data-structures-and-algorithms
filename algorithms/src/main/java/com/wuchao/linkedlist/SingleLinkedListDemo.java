package com.wuchao.linkedlist;

import com.sun.xml.internal.ws.util.Pool;

import java.time.temporal.Temporal;
import java.util.Stack;

/**
 * @author wu
 * @create 2020-04-06-16:18
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "吴用", "智多星");
        HeroNode heroNode3 = new HeroNode(3, "林冲", "豹子头");
        HeroNode heroNode4 = new HeroNode(3, "林冲2", "虎子头");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
        singleLinkedList.add(heroNode1);
        singleLinkedList.add(heroNode2);
        singleLinkedList.add(heroNode3);
        System.out.println("链表反转之前：");
//        singleLinkedList.add(heroNode1);
//        singleLinkedList.add(heroNode2);
//        singleLinkedList.add(heroNode3);
        //加入按照编号的顺序
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode2);


        singleLinkedList.list();
        singleLinkedList.update(heroNode4);
        System.out.println("删除前链表的结构");
        singleLinkedList.list();

        singleLinkedList.deleteNode(1);
        System.out.println("删除后 链表的结构");
        singleLinkedList.list();
//        reversetList(singleLinkedList.getHead());
//        System.out.println("链表反转之后：");
//        singleLinkedList.list();
        System.out.println("测试逆序打印单向链表，没有改变原来的数据结构");
        reversePrint(singleLinkedList.getHead());


//        //加入按照编号的顺序
//        singleLinkedList.addByOrder(heroNode3);
//        singleLinkedList.addByOrder(heroNode1);
//        singleLinkedList.addByOrder(heroNode2);
//
//
//
//        singleLinkedList.list();
//        singleLinkedList.update(heroNode4);
//        System.out.println("删除前链表的结构");
//        singleLinkedList.list();
//
//        singleLinkedList.deleteNode(1);
//        System.out.println("删除后 链表的结构");
//        singleLinkedList.list();
//        //测试链表中有多少个节点
//        getLength(singleLinkedList.getHead());
//        //测试是否得到了倒数index个节点。
//        System.out.println("得到了倒数的第index节点信息为" + findLastIndexNode(singleLinkedList.getHead(), 3));

    }

    /**
     * 方式二
     * 可以利用栈的数据结构，将各个节点放入栈中， 然后利用栈的先进后出的特点，实现了逆序打印的效果
     */
    public static void reversePrint(HeroNode head) {

        if (head.next == null) {
            return; //空链表
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (cur != null) {
            stack.add(cur);
            cur = cur.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    /***
     * 将单链表进行发转  (结构都反转了)
     */
    public static void reversetList(HeroNode head) {
        //1如果当前链表为空或者链表只有一个节点 不需要反转
        if (head == null || head.next.next == null) {
            return;
        }
        //定义一个辅助节点 （变量），帮助我们遍历原来的表
        HeroNode cur = head.next;
        HeroNode next = null;  //指向当前节点的下一个节点。
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表上reverseHead的最前端
        //动脑筋
        while (cur != null) {
            next = cur.next;//先暂时 保存当前的节点的下一个节点， 因为后面需要使用
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur;  //将cur连接到新的链表上
            cur = next; //让cur向后移
        }
        //将head.next 指向 reverseHead.next,实现链表的反转。
        head.next = reverseHead.next;
    }


    /**
     * 查找单链表中的 倒数 的第K个节点（新浪）
     * 思路
     * 1.编写一个方法，收到head节点，同时收到一个index
     * 2. indea 是倒数第index个节点
     * 3 先把链表从头到尾遍历一遍，得到总长度length
     * 4. 单链表的倒数第K个节点就是 开始遍历 （lengh - index）个
     * 5 找到了 ，返回该节点，如果没有就返回null;
     */
    public static HeroNode findLastIndexNode(HeroNode head, int inedx) {
        if (head.next == null) {
            return null;
        }
        //得到链表的总长度
        int size = getLength(head);

        if (size <= inedx || inedx <= 0) {
            return null;
        }
        HeroNode cur = head.next;
        for (int i = 0; i < size - inedx; i++) {
            cur = cur.next;
        }
        return cur;
    }


    /**
     * 获取到单链表的有效的节点的个数(如果是带头节点的链表，需求不统计节点)
     *
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head == null) {
            return 0;
        }
        int length = 0;
        HeroNode hur = head.next;
        while (hur != null) {
            length++;
            hur = hur.next;

        }
        System.out.println("节点有：" + length);
        return length;
    }


}

//定义singleLinkedList主要是为了管理 节点
class SingleLinkedList {
    //链表头  节点头不要东， 不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //增加节点到单项列表
    //思路 1 先找到最后节点
    // 2 将最后一个节点的next只想新的节点
    public void add(HeroNode heroNode) {
        //因为头节点不能动， 因此我们需要一个辅助遍历temp;
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            ///如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环的时候 temp就到链表的最后
        //将最后的这个节点的next,指向了新的节点。
        temp.next = heroNode;
    }

    //第二种在增加英雄 ，根据排名将英雄插入到指定的位置(如果有指定的排名，则增加成功，并给出提示);
    public void addByOrder(HeroNode heroNode) {
        //1因为头的节点不能动因此我们仍然通过一个辅助指针(变量)来帮助我们找到增加的位置
        //2因为是单链表 因为我们找的是temp是位于 增加位置的前一个节点，否则插入不了。
        HeroNode temp = head;
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
            //插入节点和 判断到节点的后一个进行联系
            heroNode.next = temp.next;
            //插入节点和 判断到节点的前一个进行联系
            temp.next = heroNode;
        }


    }

    //修改节点的信息，根据no编号来修改，即no编号不能改
    public void update(HeroNode newHeroNode) {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号。
        //定义一个临时的辅助变量
        HeroNode temp = head.next;
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

    //删除节点信息，
    public void deleteNode(int no) {
        //思路。1.先找到待删除节点的前一个节点
        //2 然后将这个节点的下一个引用指向下下一个。（直接跳过中间那个节点） 失去引用会被回收。
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head;
        Boolean flag = false;  //用此判断是否找到待删除节点的前一个节点
        while (true) {
            if (temp == null) {
                System.out.println("链表结尾");
                break;
            }
            if (temp.next.no == no) {
                //找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;   //删除该节点
        } else {
            System.out.printf("未找到此节点 %d,无法删除\n", no);
        }
    }

    //显示链表[遍历]
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点，不能动，因此我们需要一个辅助的变量来遍历
        HeroNode temp = head.next;
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
}


class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    //构造器
    public HeroNode(int no, String name, String nickname) {
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
