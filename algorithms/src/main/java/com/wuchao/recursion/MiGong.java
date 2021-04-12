package com.wuchao.recursion;

/** 迷宫问题
 * @author wu
 * @create 2021-04-12-14:47
 */
public class MiGong {
    public static void main(String[] args) {
        // 创建一个二维数组
        int[][] map = new int[8][7];
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;

        }

        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        System.out.println("走之前");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
        setWay(map,1,1);
        System.out.println("走之后");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
    }
    /**
     *
     * @param way 表示地图
     * @param i 表示从哪里开始
     * @param j
     * @return  如果找到了通路就返回true , 不通就返回false
     */
    public static boolean setWay(int[][]way,int i,int j) {
        //遵循下右上左的规则  2是代表可以走的,0表示没走，1代表墙	3代表这条路已经走过 行不通
        if(way[6][5]==2){
            return true;
        }
        if(way[i][j]==0){
            //表示没有走
            //假设这个是可以走的
            way[i][j]=2;
            if(setWay(way, i+1, j)){
                return true;
            }else if(setWay(way, i, j+1)){
                return true;
            }else if(setWay(way, i-1, j)){
                return true;
            }else if(setWay(way, i, j-1)){
                return true;
            }else{
                way[i][j] = 3;
                return false;
            }
        }else {
            return false;
        }
    }


}