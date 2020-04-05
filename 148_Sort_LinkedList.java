/*Sort a linked list in O(n log n) time using constant space complexity.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5*/

/*
1. find the mid node using slow and fast pointers
2. break the list into two lists by setting midnode.next = null
3. keep on recursively breaking the list until your lists contain single node .. 
   and single node means already sorted
4. Now just implement merge two sorted lists method.
*/

//LOGIC IS FAST-SLOW POINTER TO GET 1 NODE + MERGE ALGORITHM


//Node:
class ListNode{
    int data;
    ListNode next;
    ListNode(int data){
         this.data  = data;
         this.next = null;
     }
}

class sort_linked_list{

    static ListNode sortList(ListNode head){
        if(head == null || head.next == null) return head;

        //both pointers start from Head:
        ListNode slow = head;
        ListNode fast = head;

        //FAST = 2X SLOW
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        //Once Traversed point the fast to SLOW's NEXT and point SLOW's NEXT to NULL
        fast = slow.next;
        slow.next = null;

        //NOW THAT ARE LEFT WITH ONE NODE THEN MERGE THAT:
        //MERGE THE HEAD & FAST:
        return merge(sortList(head), sortList(fast));
    }

    static ListNode merge(ListNode left, ListNode right){

        //Head, and curr is Pointing towards Head:
        ListNode head = new ListNode(0);
        ListNode curr = head;

        //Traverse left and right LL to merge across them:
        while(left != null && right != null){
            if(left.data <= right.data){
                curr.next = left;
                left = left.next;
            } else{
                curr.next = right;
                right = right.next;
            }
            curr = curr.next;
        }

        if(left != null)
            curr.next = left;
        else if (right != null)
            curr.next = right;

        return head.next;
    }


    public static void main(String args[]){
        //
    }
}





