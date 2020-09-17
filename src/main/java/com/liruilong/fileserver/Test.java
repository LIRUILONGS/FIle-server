package com.liruilong.fileserver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Liruilong
 * @Date 2020/9/15 15:02
 * @Description:
 */
public class Test {


    /**
     * 使用arrayList实现升序/降序插入
     * @param args
     */
    public static void main(String[] args) {
        List list = new ArrayList();
        for (int i = 0; i < 15; i++) {
            list.add(0,i);
        }
        list.stream().forEach(System.out::print);
        list.clear();

        for (int i = 0; i < 15; i++) {
            list.add(i);
        }
        list.stream().forEach(System.out::print);

        List list1 = new LinkedList();

    }
}
