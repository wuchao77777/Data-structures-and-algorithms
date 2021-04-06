/*
package main.java.com.wuchao;

import java.io.Console;
import java.nio.charset.Charset;

*/
/**
 * @author wu
 * @create 2021-03-31-23:19
 *//*

public class ConcertEncodeing {

    public static void main(String[] args) {
        convertCharset("D:\\workspaces\\workspaceOxygen\\ceshi", Charset.forName("GBK"),Charset.forName("UTF-8"),"java");
    }
    */
/**
     * 转换文件编码格式
     * @param path 需要转换的文件或文件夹路径
     * @param fromCharset 原编码格式
     * @param toCharset 目标编码格式
     * @param expansion 需要转换的文件扩展名,如需全部转换则传 null
     *//*

    private static void convertCharset(String path,Charset fromCharset,Charset toCharset,String expansion ) {
        if (StrUtil.isBlank(path)) {
            return;
        }
        File file = FileUtil.file(path);
        File[] listFiles = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (StrUtil.isBlank(expansion)) {
                    return true;
                }
                if (FileUtil.isDirectory(pathname)||FileUtil.extName(pathname).equals("java")) {
                    return true;
                }
                return false;
            }
        });
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].isDirectory()) {
                String canonicalPath = FileUtil.getCanonicalPath(listFiles[i]);
                //每个文件夹分个线程处理,提高点儿效率
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        convertCharset(canonicalPath,fromCharset,toCharset,expansion);
                    }
                }).start();
            }else {
                FileUtil.convertCharset(listFiles[i], fromCharset, toCharset);
                Console.log("转换完成文件名:{}",listFiles[i].getName());
            }
        }
    }
}
*/
