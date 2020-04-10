/*

Given a linked list, rotate the list to the right by k places, where k is non-negative.

Example 1:

Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL
Example 2:

Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->NULL

*/

//rotate the list to the right by k places MEANS to te right there should be 4 elements, if <k=4>
//TRICK: Make the list Circular and skip the non required elements:
//O(n) complexity:

//TRICK is:
//K means from last how many elements are not to be changed:
//MOVED CIRCULAR and then my point came WE CUT THAT i-e pointed to NULL. (Imagine a Rope and cut it as per K)

class rotate_linkedlist{

    //head of Linked List
    Node head; 

    //Create a node for the LL:
    class Node{
        int data;
        Node next;
        Node(int d){
            data = d;
            next = null;
        }
    }

    //Supporting functions:
    void push(int new_data){
         /* 1 & 2: Allocate the Node & 
        Put in the data*/
        Node new_node = new Node(new_data);
        /* 3. Make next of new Node as head */
        new_node.next = head;
        /* 4. Move the head to point to new Node */
        head = new_node;
    }

    void printlist(){
        Node temp = head;
        while(temp != null){
            System.out.println(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public Node rotate(int k){

        //boundry condition:
        if(head == null || k< 0) return head;
        int len =1;

        //Traverse through the list and count the length:
        //10->20->30->40->50->60 
        Node current = head;
        while(current.next != null){
            current = current.next;
            len++;
        }

        //RESULT: 30 40 50 60 10 20
        //len = 6
        //k = 4
        //skips = 2
        //current = 60 <at this point since it is the last element of LL>
        //k=4 means from last 4 elements will be fixed
        //skip = 6-4 = 2 so 2 elements will be skipped or these 2 will be moved to right



        //check for the position from where the rotate will start: 
        k%=len;
        //check for the skips required to be done before you reach the k
        int skips = len - k;

        //<head> = <10> at this time i-e the starting point
        //make current as back to first node i-e 10
        //we didnt do anything to head remember we just played with current and current-> next

        //TRICK:
        //point the next back to head again and not to NULL:
        current.next = head;
        current = head;

        //after this step current = 10 back to front instead of NULL !!

        //Skip 2 steps ahead of current i-e the elements which will be rotated
        //Reach to the Position
        for(int i=0;current != null && i< skips -1; i++){
            //skip through the list based on what we calculated above:
            current = current.next;
        }
        //current = 20 at this time which is 2nd element in the List

        
        //Head is now: 30 (current's next)
        //current = 20
        //20-> next = null now

        //Rotated:
        head = current.next;
        current.next = null;

        return head;
    }

    public static void main(String args[]){
        rotate_linkedlist llist = new rotate_linkedlist();
        
        // create a list 10->20->30->40->50->60 
        for(int i=60;i>=10;i-=10)
            llist.push(i);
        
        System.out.println("Given List");
        llist.printlist();

        //k=4
        llist.rotate(4);

        System.out.println("Rotated Linked List"); 
        llist.printlist(); 
    }
}