/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode less = new ListNode(-1);
        ListNode more = new ListNode(-1);

        ListNode tail1 = less;
        ListNode tail2 = more;

        ListNode temp = head;
        while(temp != null) {
            if(temp.val < x) {
                tail1.next = temp;
                tail1 = tail1.next;
            }
            else {
                tail2.next = temp;
                tail2 = tail2.next;
            }
            temp = temp.next;
        }
        
        tail1.next = more.next;
        tail2.next = null;
        return less.next;
    }
}