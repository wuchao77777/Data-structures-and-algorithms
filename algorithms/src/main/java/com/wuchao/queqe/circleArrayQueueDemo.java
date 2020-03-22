package com.wuchao.queqe;

import java.util.Scanner;

/**
 * @ProjectName: Data-structures-and-algorithms
 * @Package: com.wuchao.queqe
 * @ClassName: circleArrayQueueDemo
 * @Author: 吴超
 * @Description: ${description}
 * @Date: 2020/3/22 20:22
 * @Version: 1.0
 */
public class circleArrayQueueDemo {

    public static void main(String[] args) {

        //创建一个队列
        CircleArray queue = new CircleArray(4);
        char key = ' ';//用于接收用户的输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列的信息");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add):  增加数据到队列");
            System.out.println("g(get):  从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g'://取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h'://查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d/n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e'://退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }

        }
    }
}
class CircleArray{
    private  int   maxSize ; //队列最大的容量

    //front 变量的含义做一个调整， front 就指向队列的第一个元素，也就是说arr[front]
    private  int   front ; //队列头
    //rear 做一个调整 rear指向队列的最后一个元素的后一个位置，因为希望空出一个位置
    private  int   rear ; //队列的尾部

    private  int[] arr;  //该数据用于存放数据，模拟队列

    public CircleArray(int arrMaxSize){
        maxSize =arrMaxSize;
        arr = new int[maxSize];
    }

    //判断是否满
    public boolean isFull(){
        return (rear+1)%maxSize == front;
    }

    //判断时候是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //增加数据到队列
    public void addQueue(int n){
        //判断队列是否满
        if(isFull()){
            System.out.println("队列满,不能加入数据");
            return;
        }
      //直接将数据加入
        arr[rear] = n;
      //将rear后移
        rear=(rear+1) % maxSize;
    }

    //获取队列的数据，出队列
    public int getQueue(){
        //判断队列是否为空
        if(isEmpty()){
            //通过抛出异常
            throw new RuntimeException("队列空，不能取出数据");
        }
        //这里需要分析出front是指向队列的第一个元素
        //1 .先把front的值保存到一个临时的变量
        //2 .将front后移
        //3 .返回临时保存的变量
        int value = arr[front];
        front = (front+1)%maxSize;
        return  value;
    }

    //显示队列的所有的数据
    public void showQueue(){
        //
        if(isEmpty()){
            System.out.println("队列是空的，没有数据~");
            return;
        }
        //思路 从front开始遍历，遍历多少个元素

        //取 模 的作用 和绝对值的意思一样 ！！！！！
        for (int i = front; i < front+size(); i++) {
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }

    //求出当前队列的有效数据的个数
     public int size(){
        return (maxSize + rear - front) % maxSize;
     }


    //显示队列的头数据，注意不是取出数据
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列是空的，没有数据~~");
        }
        return arr[front];
    }
}

