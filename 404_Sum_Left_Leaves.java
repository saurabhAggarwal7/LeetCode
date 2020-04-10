/*Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24. */

import java.util.*;
class TreeNode_404{
    int data;
    TreeNode_404 left;
    TreeNode_404 right;
}

//SOLUTION-1: Recursive Solution:
class Sum_Left_Leaves_Recursion {
    public int sum_Left_Leaves(TreeNode_404 root){
        return helper_recursive(root, false);
    }

    public int helper_recursive(TreeNode_404 root, boolean isLeft){
        if(root == null) 
            return 0;
        
        //Find Left Leaf:
        if(root.left == null && root.right == null && isLeft)
            return root.data;
        
        return helper_recursive(root.left, true) + helper_recursive(root.right, false);
    }
}

//SOLUTION-2: Iterative Solution:
class sum_left_leafs_iterative{
    public int sum_left_leves(TreeNode_404 root){
        if(root == null)
            return 0;

        int ans =0;
        Stack<TreeNode_404> stack = new Stack<TreeNode_404>();
        stack.push(root);

        while(!stack.empty()) {
            TreeNode_404 node = stack.pop();

            //Left NODE
            if(node.left != null) {
                //check if it's leaf node
                if (node.left.left == null && node.left.right == null)
                    ans += node.left.data;
                else
                    stack.push(node.left);
            }

            //RIGHT NODE
            if(node.right != null) {
                //check if it's right node
                if (node.right.left != null || node.right.right != null)
                    stack.push(node.right);
            }
        }
        return ans;
    }
}

