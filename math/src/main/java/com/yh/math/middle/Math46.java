/*
package com.yh.math.middle;

import java.util.*;

*/
/**
 * https://zhuanlan.zhihu.com/p/93530380
 * https://labuladong.gitee.io/algo/
 * 回溯算法
 *//*

public class Math46 {


    static List<Integer> nums = new ArrayList<>();

    static {
        nums.add(1);
        nums.add(2);
        nums.add(3);
    }

    static List<List<Integer>> check = new LinkedList<>();


    public static void main(String[] args) {

        backMethod(nums, new ArrayList<Integer>());
        System.out.println(check);
    }

    public static void backMethod(List<Integer> nums, List<Integer> result) {
        // 终止条件
        if (nums.size() == 0) {
            return;
        }
        //能做选择的列表
        List<Integer> canDo = nums;
        Iterator iterator = canDo.iterator();
        //开始做选择
        while (iterator.hasNext()) {
            //添加到结果里
            result.add((Integer) iterator.next());            // 选择列表移除此元素
            iterator.remove();
            //继续做选择
            backMethod(new ArrayList<Integer>(canDo), result);
            //结果添加
            check.add(new ArrayList<Integer>(result));
            //清空暂存
            result.clear();
            //撤销选择 让其回溯
        }
    }

}
*/
