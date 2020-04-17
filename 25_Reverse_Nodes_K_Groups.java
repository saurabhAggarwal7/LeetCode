/*

Given a linked list, write a function to reverse every k nodes (where k is an input to the function).

Input: 1->2->3->4->5->6->7->8->NULL, K = 3
Output: 3->2->1->6->5->4->8->7->NULL


Input: 1->2->3->4->5->6->7->8->NULL, K = 5
Output: 5->4->3->2->1->8->7->6->NULL

Note this version of code rveerses all the elelemts irrespective of remaining elemensts different from Leetcode problem
*/

//IN THE BATCH SIZE OF 3-3 IT WILL BE REVERSED

// Java program to reverse a linked list in groups of 
// given size 
class Reverse_K_Groups_LinkedList {
    Node head; // head of list

    /* Linked list Node */
    class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    Node reverse(Node head, int k) {
        
        // itr-1: head=1
        // itr-2: head=4
        //itr-3: head will be null and thats where prev is returned:

        Node current = head;
        Node next = null;
        Node prev = null;

        int count = 0;

        /* Reverse first k nodes of linked list */
        while (count < k && current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }

        //itr-1: after while loop for k=3 is run: now 
        //count=3 /current=4/next=4/head=1/    
        //pre =3

        //itr-2: after while loop for k=3 it runs:
        //count=3 /cuurent=7/next=7/head=1
        //pre = 6

        //itr-3:
        //pre=9 thats the result which will be the head of the Linkedlist now

        /*
         * next is now a pointer to (k+1)th node Recursively call for the list starting
         * from current. And make rest of the list as next of first node
         */
        if (next != null)
            //itr-1: next =4 for which recusrsion will run now
            //itr-2: next =7 for which recursion will now run
            head.next = reverse(next, k);

        // prev is now head of input list

        //RESULT:
        //llist.head = prev 
        return prev;
    }

    

    /* Utility functions */

    /* Inserts a new Node at front of the list. */
    public void push(int new_data) {
        /*
         * 1 & 2: Allocate the Node & Put in the data
         */
        Node new_node = new Node(new_data);

        /* 3. Make next of new Node as head */
        new_node.next = head;

        /* 4. Move the head to point to new Node */
        head = new_node;
    }

    /* Function to print linked list */
    void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    /* Driver program to test above functions */
    public static void main(String args[]) {
        Reverse_K_Groups_LinkedList llist = new Reverse_K_Groups_LinkedList();

        /*
         * Constructed Linked List is 1->2->3->4->5->6-> 7->8->8->9->null
         */
        llist.push(9);
        llist.push(8);
        llist.push(7);
        llist.push(6);
        llist.push(5);
        llist.push(4);
        llist.push(3);
        llist.push(2);
        llist.push(1);

        System.out.println("Given Linked List");
        llist.printList();

        llist.head = llist.reverse(llist.head, 3);

        System.out.println("Reversed list");
        //3 2 1 6 5 4 9 8 7 
        llist.printList();
    }
}
/* This code is contributed by Rajat Mishra */
