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
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummy = new ListNode(0);

        ListNode temp = head;

        int size = 0;

        while(temp != null){
            temp = temp.next;
            size++;
        }

        temp = dummy;

        n = size - n + 1;

        for(int i = 1; i < n; i++){

            temp.next = head;
            temp = temp.next;
            head = head.next;
        }

        temp.next = head.next;

        return dummy.next;
    }
}