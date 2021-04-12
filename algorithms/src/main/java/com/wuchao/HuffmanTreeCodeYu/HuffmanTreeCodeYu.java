package com.wuchao.HuffmanTreeCodeYu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 赫夫曼思路
 * @author wu
 * @create 2021-04-12-14:42
 */
public class HuffmanTreeCodeYu {
    public static void main(String[] args) {
        /**
         * 1  现将需要传输的数据转为byte[] 数组 然后将数组 存放到list 中 （方便排序）List 每一个单位是一个node  (data( 是每一个byte) ,weight(每一个byte出现的次数)，leftNode 左子节点 ，rightNode 右子节点)
         * 2 生成 赫夫曼树
         *  思路
         *     2.1先排序
         *     2.2 然后取出排序中的前两个 然后将这两个构成一个树  （！树的weight是 这两个取出数的和 data 为null(方    便遍历的时候进行判断)
         *     2.3然后将前两个数据从数组中移除 加入新构成的那个数， 循环直到集合中只有一个数则构成了赫夫曼树。
         * 3 然后进行左右递归形成 赫夫曼表 （使用一个Map<Byte, String> byte 存放的是叶子节点的数据 ，String存放的是 从树的根节点 到叶子节点的路径 （向左为0 向右时为1））
         * 4 根据赫夫曼表 形成进行压缩
         *
         * */



        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(contentBytes.length);
        // 返回赫夫曼编码处理后的 byte[]
        byte[] huffmanTreeCodes = getHuffmanTreeCodes(contentBytes);
        System.out.println("huffmanCodeBytes="
                + Arrays.toString(huffmanTreeCodes));// 17

    }

    // 编写一个方法，将字符串对应的byte[] 数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]
    /**
     *
     * @param bytes
     *            这时原始的字符串对应的 byte[]
     * @param huffmanCodes
     *            生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的 byte[] 举例： String content =
     *         "i like like like java do you like a java"; =》 byte[]
     *         contentBytes = content.getBytes(); 返回的是 字符串
     *         "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     *         => 对应的 byte[] huffmanCodeBytes ，即 8位对应一个 byte,放入到
     *         huffmanCodeBytes huffmanCodeBytes[0] = 10101000(补码) => byte [推导
     *         10101000=> 10101000 - 1 => 10100111(反码)=> 11011000= -88 ]
     *         huffmanCodeBytes[1] = -88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {

        // 1.利用 huffmanCodes 将 bytes 转成 赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 遍历bytes 数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        // System.out.println("测试 stringBuilder~~~=" +
        // stringBuilder.toString());

        // 将 "1010100010111111110..." 转成 byte[]

        // 统计返回 byte[] huffmanCodeBytes 长度
        // 一句话 int len = (stringBuilder.length() + 7) / 8;
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        // 创建 存储压缩后的 byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;// 记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) { // 因为是每8位对应一个byte,所以步长
            // +8
            String strByte;
            if (i + 8 > stringBuilder.length()) {// 不够8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            // 将strByte 转成一个byte,放入到 huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    private static byte[] getHuffmanTreeCodes(byte[] contentBytes) {
        List<Node> nodes = getNodes(contentBytes);
        System.out.println("nodes=" + nodes);
        // 构造赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        System.out.println("前序遍历");
        huffmanTreeRoot.preOrder();
        // 获得赫夫曼表
        Map<Byte, String> codes = getCodes(huffmanTreeRoot);
        System.out.println("~生成的赫夫曼编码表= " + huffmanCodes);

        byte[] zip = zip(contentBytes, codes);
        return zip;
    }

    // 生成赫夫曼树对应的赫夫曼编码
    // 思路:
    // 1. 将赫夫曼编码表存放在 Map<Byte,String> 形式
    // 生成的赫夫曼编码表{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011,
    // 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    // 2. 在生成赫夫曼编码表示，需要去拼接路径, 定义一个StringBuilder 存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        // 处理root的左子树
        getCodes(root.leftNode, "0", stringBuilder);
        // 处理root的右子树
        getCodes(root.rightNode, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 功能：将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
     *
     * @param node
     *            传入结点
     * @param code
     *            路径： 左子结点是 0, 右子结点 1
     * @param stringBuilder
     *            用于拼接路径
     */
    private static void getCodes(Node node, String code,
                                 StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null) {
            // 遍历到非字节点
            if (node.data == null) {
                getCodes(node.leftNode, "0", stringBuilder2);
                getCodes(node.rightNode, "1", stringBuilder2);
                // 遍历到了子节点
            } else {
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    // 构造赫夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            // 排序, 从小到大
            Collections.sort(nodes);
            // 取出第一颗最小的二叉树
            Node leftNode = nodes.get(0);
            // 取出第二颗最小的二叉树
            Node rightNode = nodes.get(1);
            // 创建一颗新的二叉树,它的根节点 没有data, 只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.leftNode = leftNode;
            parent.rightNode = rightNode;

            // 将已经处理的两颗二叉树从nodes删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 将新的二叉树，加入到nodes
            nodes.add(parent);

        }
        // nodes 最后的结点，就是赫夫曼树的根结点
        return nodes.get(0);
    }

    /**
     * 获取
     *
     * @param bytes
     * @return 返回的就是 List 形式 [Node[date=97 ,weight = 5], Node[]date=32,weight =
     *         9]......],
     */
    public static List<Node> getNodes(byte[] bytes) {

        List<Node> nodes = new ArrayList<Node>();

        Map<Byte, Integer> counts = new HashMap<>();

        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        for (Map.Entry<Byte, Integer> node : counts.entrySet()) {
            nodes.add(new Node(node.getKey(), node.getValue()));
        }
        return nodes;
    }
}

class Node implements Comparable<Node> {
    Byte data;
    int weight;
    Node leftNode;
    Node rightNode;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node paramT) {
        return this.weight - paramT.weight;
    }

    @Override
    public String toString() {
        return "Node [data=" + data + ", weight=" + weight + "]";
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.leftNode != null) {
            this.leftNode.preOrder();
        }
        if (this.rightNode != null) {
            this.rightNode.preOrder();
        }
    }

}