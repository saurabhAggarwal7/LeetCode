

//Leetcode version where the nodes remain same after the left over nodes
/*

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

Example:

Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

Note:

Only constant extra memory is allowed.
You may not alter the values in the list's nodes, only nodes itself may be changed.
*/

class Reverse_List_Nodes_K_Groups_II {
    class ListNode {
        int data;
        ListNode next;

        ListNode(int d) {
            data = d;
            next = null;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        // 1. test weather we have more then k node left, if less then k node left we
        // just return head
        ListNode node = head;
        int count = 0;
        while (count < k) {
            if (node == null)
                return head;
            node = node.next;
            count++;
        }
        // 2.reverse k node at current level
        ListNode pre = reverseKGroup(node, k); // pre node point to the the answer of sub-problem
        while (count > 0) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
            count = count - 1;
        }
        return pre;
    }
}