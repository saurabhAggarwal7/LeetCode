/* 

>>AVL Trees:
>>FLIP BST:

Problem:
Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

For example:
Given a binary tree {1,2,3,4,5},

    1
   / \
  2   3
 / \
4   5
return the root of the binary tree [4,5,2,#,#,3,1].

   4
  / \
 5   2
    / \
   3   1
confused what “{1,#,2,3}” means? > read more on how binary tree is serialized on OJ.
Thoughts
The key point here is that the tree is given by strict condition.
all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty

This means if a node has a right child, the right child will never has a subtree, or in another word, the right child will not have any children.

So the basic step we will need is to reorganize the pointer between a node, its left child (if there is) and the right child (if there is). If left child is not empty, we go down to recursively solve the problem.
Using this method, we need to make sure to clean the left and right pointer of current node, otherwise, the original root will keep having old values in its left and right pointer.

Also, there could be an iterative solution which will avoid to use stack when calling function recursively.
The key idea for this method is to always solve (assign the correct value for left and right pointer of) current node.
Say for the example above,
Step 1, assign correct left and right for 1, which is null for both.
Step 2, assign correct left and right for 2, which is 3 and 1.
Step 3, assign correct left and right for 4, which is 5 and 2,
etc.
And need to have two pointer to keep the value of above level.

There could also be a version to use a modified post order solution.
This is similar to the first solution – recursion. The difference is that if we want to re-assign pointer from top to down OR from bottom to up?
*/

class TreeNode_156{
    int data;
    TreeNode_156 left;
    TreeNode_156 right;
    TreeNode_156(int x){
        this.data = x;
    }
}

class BST_Operation_156{
    TreeNode_156 root;

    public TreeNode_156 upsidedown_BST(TreeNode_156 root){
        if(root == null)
            return null;
        
        //LEFT and Right of the node:
        TreeNode_156 left = root.left;
        TreeNode_156 right = root.right;

        if(left !=null){
            TreeNode_156 ret = upsidedown_BST(left);
             
            left.left = right;
            left.right = root;

             root.left = null;
             root.right = null;
             
             return ret;
        }  
        return null;      
    }
}

class Upside_Down_BST{
    public static void main (String args[]){
        BST_Operation_156 tree_1 = new BST_Operation_156();
        // TREE 1 
        /* Construct the following tree 
                2
               / \
              1     3       */
            
              tree_1.root = new TreeNode_156(2); 
              tree_1.root.right = new TreeNode_156(3); 
              tree_1.root.right.left = new TreeNode_156(1); 
      
              //System.out.println(tree_1.upsidedown_BST());
    }
}

