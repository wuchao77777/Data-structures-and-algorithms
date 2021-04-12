/*
package com.wuchao.HuffmanTreeCodeYu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

*/
/** 赫夫曼树的构建
 * @author wu
 * @create 2021-04-12-14:43
 *//*

public class HuffmanTreeYu {

    public static void main(String[] args) {
        int[] arr = { 13, 7, 8, 3, 29, 6, 1 };
        Node createHuffmanTree = createHuffmanTree(arr);
        preOrder(createHuffmanTree);
    }
    //编写一个前序遍历的方法
    public static void preOrder(Node parent){
        if(parent != null){
            parent.preOrder();
        }else {
            System.out.println("树是空树无法遍历");
        }
    }

    //创建赫夫曼输的方法
    public static Node createHuffmanTree(int[] arr) {
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr) {
            Node node = new Node(value);
            nodes.add(node);
        }

        while (nodes.size()>1) {
            Collections.sort(nodes);

            //取出节点全职最小的两颗二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //将两个节点构建成一个新的二叉树
            Node parent = new Node(leftNode.value+rightNode.value);
            parent.lightNode = leftNode;
            parent.rightNode = rightNode;
            //在原来数组中去除最小的节点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //然后新构建的二叉树给加入
            nodes.add(parent);
        }
        return nodes.get(0);
    }

}
//创建节点

class Node implements Comparable<Node>{
    int value;
    Node lightNode;
    Node rightNode;
    public Node(int value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return "Node [value=" + value + "]";
    }
    //实现从小到大排序
    @Override
    public int compareTo(Node o) {
        return this.value-o.value;
    }
    //进行前序遍历树
    public void preOrder(){
        System.out.println(this);
        if(this.lightNode !=null){
            this.lightNode.preOrder();
        }
        if(this.rightNode !=null){
            this.rightNode.preOrder();
        }
    }
}*/
