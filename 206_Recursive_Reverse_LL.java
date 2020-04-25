/**
 * Given pointer to the head node of a linked list, the task is to reverse the
 * linked list. We need to reverse the list by changing links between nodes.
 * 
 * Examples:
 * 
 * Input: Head of following linked list 1->2->3->4->NULL Output: Linked list
 * should be changed to, 4->3->2->1->NULL
 * 
 * Input: Head of following linked list 1->2->3->4->5->NULL Output: Linked list
 * should be changed to, 5->4->3->2->1->NULL
 * 
 * Input: NULL Output: NULL
 * 
 * Input: 1->NULL Output: 1->NULL
 * 
 Recursive Method
 * 
 * 
 * 
 */

class Reverse_LL_Recursive {

    static ListNode head;

    static class ListNode {

        int data;
        ListNode next;

        ListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    //previous a sthe parmeter is sent becuse the current becomes previous now
    //this concpet is used in recusrion in cases when you need to reverse any thing

    //curr=85, prev=null
    //curr=15, prev=85
    ListNode reverse(ListNode curr, ListNode prev){

        //Given Linked list
        //85 15 4 20 

        //Reverse:
        //20 4 15 85

        /* If last node mark it head*/
        if (curr.next == null) { 
            head = curr; 
            /* Update next to prev node */
            curr.next = prev; 
            return head; 
        } 
  
        /* Save curr->next node for recursive call */

        //curr = 85, next1 = curr.next--> 15
        //next1 = (15).next --> 4
        ListNode next1 = curr.next; 
  
        /* and update next ..*/

        //curr = 85, (85).next--> prev = null now
        //curr.next(15's next) = prev = 85 now (from previous iteration)
        curr.next = prev; 
        
        //reverse(15, 85) when loop runs recursively the curr becomes previous
        //reverse(4, 15) this 15 becomes prev for the next iteration
        reverse(next1, curr); 
        return head; 
    }

    void printList(ListNode node){
		while (node != null) { 
			System.out.print(node.data + " "); 
			node = node.next; 
		} 
    }

    public static void main(String args[]){
        Reverse_LL_Recursive list = new Reverse_LL_Recursive(); 
		list.head = new ListNode(85); 
		list.head.next = new ListNode(15); 
		list.head.next.next = new ListNode(4); 
		list.head.next.next.next = new ListNode(20); 

		System.out.println("Given Linked list"); 
		list.printList(head); 
		head = list.reverse(head, null); 
		System.out.println(""); 
		System.out.println("Reversed linked list "); 
		list.printList(head); 
    }
}