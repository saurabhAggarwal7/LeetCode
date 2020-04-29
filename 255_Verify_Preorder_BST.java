
/**
 * One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

      9
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.
Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

SOLUTION::
We can keep trimming the leaves until there is no one to remove. If a sequence is like "4 # #", change it to "#" and continue. A stack is a good date structure for this purpose.


9 3 4 # # 1 # # 2 # 6 # #
    -#--  -#---     ---#--
  -----#------- ---#------
  -------------#-----------

  [4 # #] --> #
  [1 # #] --> #
  These two will merge to get #

  [6 # #] --> #
  [2, #] + above 
  These will combine to get #  

*/

import java.util.*;
class Verify_Preorder_BST {
    boolean isValidSerialization(String preorder) {
        String[] arr = preorder.split(",");
     
        Stack<String> stack = new Stack<>();
        for(String s: arr){
            if(stack.isEmpty() || !s.equals("#")){
                //first iteration:
                //empty and non null so pushed to stack
                stack.push(s);
            }else{
                //from second 
                while(!stack.isEmpty() && stack.peek().equals("#")){
                    //as soon as we get # we pop 
                    stack.pop();
                    if(stack.isEmpty()){
                        return false;
                    }else{
                        stack.pop();
                    }
                }
                stack.push("#");
            }            
        }
     
        return stack.size()==1 && stack.peek().equals("#");
    }
}