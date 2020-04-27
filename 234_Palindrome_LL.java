/**
 * Explanation
 * ..........................................................................................
 * Example : 1-> 2-> 3-> 4-> 2-> 1
 * 
 * ref points 1 initially. Make recursive calls until you reach the last element
 * - 1. On returning from each recurssion, check if it is equal to ref values.
 * ref values are updated to on each recurssion. So first check is ref 1 - end 1
 * Second ref 2 - second last 2 ...and so on.
 * ..........................................................................................
 * }
 */

 //SOLUTION: MADE USE OF RECURSION BACKTRACKING THAT COMPARING FROM LAST, we already have reference from starting sop we can compare last and first elemensta nd move forward

class ListNode_Palindrome_LL {
    ListNode_Palindrome_LL next;
    int val;
}

class Palindrome_LL {
    ListNode_Palindrome_LL ref;

    public boolean isPalindrome(ListNode_Palindrome_LL head) {
        ref = head;
        return check(head);
    }

    public boolean check(ListNode_Palindrome_LL node) {
        if (node == null)
            return true;
        
        //Node's next sent to recursion:
        boolean ans = check(node.next);

        //ref's value == Node's value ??
        //made use pof recursion when backtracking this will be comparing from nodes last and first elements
        boolean isEqual = (ref.val == node.val) ? true : false;
        ref = ref.next;
        return ans && isEqual;
    }

    public static void main(String args[]){
        //
    }
}