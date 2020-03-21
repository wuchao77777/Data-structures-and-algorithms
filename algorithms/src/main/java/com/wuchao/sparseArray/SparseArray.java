package com.wuchao.sparseArray;

/**
 * @ProjectName: Data-structures-and-algorithms
 * @Package: com.wuchao.sparseArray
 * @ClassName: SparseArray
 * @Author: 吴超
 * @Description: ${description}
 * @Date: 2020/3/21 17:06
 * @Version: 1.0
 */

/**
 * 稀疏数组
 */
public class SparseArray {


    public static void main(String[] args) {
        //创建一个原始的二位数组 11 * 11
        // 0 表示没有棋子 ,1 表示有棋子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2]=1;
        chessArr1[2][3]=2;
        chessArr1[4][5]=6;
        //输出的原始的二维数组
        System.out.println("原始的数组为");
        for (int[] ints : chessArr1) {
            for (int data : ints) {
                System.out.print(data+"\t");
            }
            System.out.println();
        }

        //将二维数组 转为稀疏数组 的思想
        //1. 先遍历二维数组，得到非0的数的个数
        int sum = 0;
        for (int i = 0; i <11 ; i++) {
            for (int j = 0; j < 11; j++) {
                if(chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }
        System.out.println("sum :"+sum);
        //2创建一个对应的稀疏数组
        int sparseArray[][] = new int[sum+1][3];
          //2.1给稀疏数组赋值
        sparseArray[0][0] =11 ;
        sparseArray[0][1] =11 ;
        sparseArray[0][2] =sum ;
          //遍历二维数组 将非0的值放到稀疏数组中
        int count = 0;  //为了存放稀疏数组中的行号(因为有一个数组稀疏数组会多加一行！)
        for (int i = 0; i <11 ; i++) {
            for (int j = 0; j < 11; j++) {
                if(chessArr1[i][j] != 0){
                    count++;
                    sparseArray[count][0]=i;
                    sparseArray[count][1]=j;
                    sparseArray[count][2]=chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组
        System.out.println("稀疏数组为~~");
        for (int i = 0; i <sparseArray.length ; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArray[i][0],sparseArray[i][1],sparseArray[i][2]);
        }

        //将稀疏数组恢复成原始的二维数组；
         //1先读取稀疏数组的第一行， 根据第一行的数据建立一个数组
        int chessArr2[][] = new int[sparseArray[0][0]][sparseArray[0][1]];

        //2 读取系数数组后几行的数据，并赋给原始的而二维数组即可
        for (int i = 1; i <sparseArray.length ; i++) {
            chessArr2[sparseArray[i][0]][sparseArray[i][1]]=sparseArray[i][2];
        }
        //输出恢复后的二维数组
        System.out.println("恢复后的二维数组");
        for (int[] ints : chessArr2) {
            for (int data : ints) {
                System.out.print(data+"\t");
            }
            System.out.println();
        }



    }
}
