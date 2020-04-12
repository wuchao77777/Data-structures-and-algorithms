package com.wuchao.linkedlist;

import java.util.Stack;

/**
 * 演示Stack的基本使用
 *
 * @author wu
 * @create 2020-04-12-21:31
 */
public class TestStack {
    public static void main(String[] args) {

        Stack<String> stack  = new Stack<>();
        //入栈
        stack.push("1");
        stack.add("2");
        stack.add("3");
        //出栈
        while (stack.size()>0){
            System.out.println(stack.pop());//将栈顶的数据取出
        }

    }
}
