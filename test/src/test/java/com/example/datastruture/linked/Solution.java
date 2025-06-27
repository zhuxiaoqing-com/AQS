package com.example.datastruture.linked;


import org.junit.Test;

class ListNode {
    char val;
    ListNode next;

    ListNode(char x) {
        val = x;
    }
}

/**
 * 用快慢指针先找到中点，然后把后半段链表reversed，
 * 然后一个指针在头部，一个指针再中点，开始逐个比较，时间复杂度是O（n)
 * 上海自来水来自海上
 * 海自来上水来自海上
 */

public class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        //  head.next 如果 == null 说明是奇数
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        if (fast != null) {
            slow = slow.next;
        }

        while (slow != null) {
            if (slow.val != prev.val) {
                return false;
            }
            slow = slow.next;
            prev = prev.next;
        }

        return true;
    }

    public void print(ListNode head) {
        ListNode temp = head;
        StringBuilder builder = new StringBuilder();
        while (temp != null) {
            builder.append(temp.val + ",");
            temp = temp.next;
        }
        System.out.println(builder);
    }


    @Test
    public void test01() {
        //水来自海上来自海上
        //String s = "上海自来水来自海上";
        String s = "上海自来水水来自海上";
        ListNode head = new ListNode('上');
        ListNode temp = head;
        char[] chars = s.toCharArray();
        for (int x = 1; x < chars.length; x++) {
            temp.next = new ListNode(chars[x]);
            temp = temp.next;
        }
        print(head);
        isPalindrome(head);
    }

    public boolean isPalindrome01(ListNode head) {
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {

        }

        return true;
    }
}