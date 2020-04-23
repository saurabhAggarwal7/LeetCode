/**
 * 
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
 

Example 1:

Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2

 */

 //TRICK : DESIGN a STACK USING LINKED LIST

 class min_stack{

    private class Node{
        int val;
        int min;
        Node next;

        //2-param based constructor::
        private Node(int val, int min){
            //call another constructor from within this:
            this(val, min, null);
        }

        //3-param based constructor::
        private Node(int val, int min, Node next){
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }

    //Head::
    private Node head;

    //push::
    public void push(int x){
        if(head == null)
            head = new Node(x, x);
        else    
            head = new Node(x, Math.min(x, head.min), head);
    }

    //pop::
    public void pop(){
        head = head.next;
    }

    //top::
    public int top(){
        return head.val;
    }

    //get min::
    public int getMin(){
        return head.min;
    }

 }