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
 * Iterative Method
 * 
 * Initialize three pointers prev as NULL, curr as head and next as NULL.
 * Iterate trough the linked list. In loop, do following. // Before changing
 * next of current, // store next node next = curr->next // Now change next of
 * current // This is where actual reversing happens curr->next = prev
 * 
 * // Move prev and curr one step forward prev = curr curr = next
 * 
 * 
 * 
 */

class Reverse_LL {

    static ListNode head;

    static class ListNode {

        int data;
        ListNode next;

        ListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    ListNode reverse(ListNode node){

        //Given Linked list
        //85 15 4 20 

        //Reverse:
        //20 4 15 85

        ListNode prev = null; //prev= null ||
		ListNode current = node; //current = 85, next=15 ||
        ListNode next = null; //next = null ||
        
        //current = 85 != null True  || 
        //current = 15 != null
		while (current != null) {  

            //next = 85(next) =15 ||
            //next = 15(next) = 4
            next = current.next; 
            
            //current.next = null bcz prev is null||
            //current.next = prev {prev is 85 from previous iteration} means ---> cuurent.next(4's next) = 85
            current.next = prev; 
            
            //prev = 85
            //now previous becomes 15 as used in next iteration
            prev = current; 
            
            //current = 15 now
            //current = 4 for next iteration
			current = next; 
        } 
        
        //when the loop ends the prev is the last node in given list which is now the first node in reversed list so thats why we put node = prev
		node = prev; 
		return node; 
    }

    void printList(ListNode node){
		while (node != null) { 
			System.out.print(node.data + " "); 
			node = node.next; 
		} 
    }

    public static void main(String args[]){
        Reverse_LL list = new Reverse_LL(); 
		list.head = new ListNode(85); 
		list.head.next = new ListNode(15); 
		list.head.next.next = new ListNode(4); 
		list.head.next.next.next = new ListNode(20); 

		System.out.println("Given Linked list"); 
		list.printList(head); 
		head = list.reverse(head); 
		System.out.println(""); 
		System.out.println("Reversed linked list "); 
		list.printList(head); 
    }
}