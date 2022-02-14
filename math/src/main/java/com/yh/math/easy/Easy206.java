package com.yh.math.easy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Stack;

/**
 * 反转链表
 */
public class Easy206 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1, null);
        head.next = new ListNode(2, null);
        head.next.next = new ListNode(3, null);
        head.next.next.next = new ListNode(4, null);
        //   System.out.println(head);
        System.out.println(reverseList(head));
        System.out.println(reverseList2(head));
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class ListNode {
        int val;
        ListNode next;
    }

    /**
     * 栈处理
     *
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        // 特殊值处理
        if (head == null || head.next == null) {
            return head;
        }
        // 先入栈 再出栈 先进后出
        Stack<ListNode> stack = new Stack<>();
        // 入栈
        while (head != null) {
            stack.push(new ListNode(head.val, null));
            head = head.next;
        }
        // 出栈
        //第一个节点
        ListNode first = stack.pop();
        // 下一个节点
        ListNode nextNode = first;
        //如果不为空，则弹出（全部出栈）
        while (!stack.isEmpty()) {
            // 赋值
            nextNode.next = stack.pop();
            // 往下一层
            nextNode = nextNode.next;
        }
        return first;
    }

    /**
     * 迭代
     * 将节点的next指针 改成 指向前面的  调换位置
     *
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head) {
        // 特殊值处理
        if (head == null || head.next == null) {
            return head;
        }
        // 前置节点
        ListNode prev = null;
        // 当前节点
        ListNode cur = head;

        while (cur != null) {
            // 调换位置
            ListNode nextNode = cur.next;
            cur.next = prev;
            //继续往下走
            // 前置节点变成当前节点
            prev = cur;
            // 当前节点往下走
            cur = nextNode;
        }
        return prev;
    }
}
