/*You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.*/

//Algorithm:

/*Just like how you would sum two numbers on a piece of paper, we begin by summing the least-significant digits, which is the head of l1l1 and l2l2. Since each digit is in the range of 0 \ldots 90…9, summing two digits may "overflow". For example 5 + 7 = 125+7=12. In this case, we set the current digit to 22 and bring over the carry = 1carry=1 to the next iteration. carrycarry must be either 00 or 11 because the largest possible sum of two digits (including the carry) is 9 + 9 + 1 = 199+9+1=19.

The pseudocode is as following:

Initialize current node to dummy head of the returning list.
Initialize carry to 00.
Initialize pp and qq to head of l1l1 and l2l2 respectively.
Loop through lists l1l1 and l2l2 until you reach both ends.
Set xx to node pp's value. If pp has reached the end of l1l1, set to 00.
Set yy to node qq's value. If qq has reached the end of l2l2, set to 00.
Set sum = x + y + carrysum=x+y+carry.
Update carry = sum / 10carry=sum/10.
Create a new node with the digit value of (sum \bmod 10)(summod10) and set it to current node's next, then advance current node to next.
Advance both pp and qq.
Check if carry = 1carry=1, if so append a new node with digit 11 to the returning list.
Return dummy head's next node.
Note that we use a dummy head to simplify the code. Without a dummy head, you would have to write extra conditional statements to initialize the head's value.*/

//test case:
/*
l1=[0,1]
l2=[0,1,2]	When one list is longer than the other.

l1=[]
l2=[0,1]	When one list is null, which means an empty list.

l1=[9,9]
l2=[1]	

The sum could have an extra carry of one at the end, which is easy to forget.
*/

class Sum_Two_Numbers_LL{

    public Node addTwoNumbers(Node n1, Node n2){

        Node dummyHead = new Node(0);
        Node curr = dummyHead;

        int carry =0;
        while(n1 != null || n2 != null){

            int x = (n1 != null ) ? n1.data : 0;
            int y = (n2 != null) ? n2.data : 0;

            int sum = carry + x + y;
            carry = sum/10;
            curr.next = new Node(sum % 10);

            curr = curr.next;

            if(n1 != null) n1 = n1.next;
            if(n2 != null) n2 = n2.next;
        }

        if(carry > 0){
            curr.next = new Node(carry);
        }

        return dummyHead.next;
    }

    class Node{
        int data;
        Node next;
         Node(int data){
             this.data  = data;
             this.next = null;
         }
    }

    public static void main(String args[]){
        //
    }

}
