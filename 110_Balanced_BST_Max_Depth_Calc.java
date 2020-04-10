/*abstract
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as:

a binary tree in which the left and right subtrees of every node differ in height by no more than 1.

 

Example 1:

Given the following tree [3,9,20,null,null,15,7]:

    3
   / \
  9  20
    /  \
   15   7
Return true.

Example 2:

Given the following tree [1,2,2,3,3,null,null,4,4]:

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
Return false.
*/

//LEFT - RIGHT <= 1 == BALANCED BST

class TreeNode_110{
    TreeNode_110 left;
    TreeNode_110 right;
    int data;

    TreeNode_110(int item){
        this.data = item;
        this.left = null;
        this.right = null;
    }
}

class BST_Operations_110{

    TreeNode_110 root;
    private boolean ret = false;

    public boolean isBalanced(TreeNode_110 root){

        int depth_max = calculate_max_depth(root);
        return ret;
    }

    public int calculate_max_depth(TreeNode_110 root){
        if(root == null)
            return 0;
        int left = calculate_max_depth(root.left);
        int right = calculate_max_depth(root.right);

        if(Math.abs(left - right) > 1)
            ret = false;
        
        return Math.max(left, right) + 1;
    }
}

class Balanced_BST_110{
    public static void main(String args[]){
        BST_Operations_110 tree_1 = new BST_Operations_110();
        
        tree_1.root = new TreeNode_110(2); 
        tree_1.root.right = new TreeNode_110(3); 
        tree_1.root.right.left = new TreeNode_110(1); 

        
    }
}