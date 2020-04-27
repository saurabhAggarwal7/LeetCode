/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
 */

 import java.util.*;
 class TreeNode_230{
     TreeNode_230 left;
     TreeNode_230 right;
     int val;
 }

 class Kth_smallest_BST{
    //Recursive:

    int count = 0;
    int result = Integer.MIN_VALUE;
    
    public int kthSmallest(TreeNode_230 root, int k) {
        traverse(root, k);
        return result;
    }
    
    public void traverse(TreeNode_230 root, int k) {
        if(root == null) return;
        traverse(root.left, k);
        count ++;
        if(count == k) result = root.val;
        traverse(root.right, k);       
    }
    //Iterative:
    
     int kthSmallest_Iterative(TreeNode_230 root, int k) {
         Stack<TreeNode_230> stack = new Stack<TreeNode_230>();
         TreeNode_230 p = root;
         int count = 0;
         
         while(!stack.isEmpty() || p != null) {
             if(p != null) {
                 stack.push(p);  // Just like recursion
                 p = p.left;   
                 
             } else {
                TreeNode_230 node = stack.pop();
                if(++count == k) return node.val; 
                p = node.right;
             }
         }
         
         return Integer.MIN_VALUE;
     }
 }