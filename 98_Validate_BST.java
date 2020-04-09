import java.util.Stack;

/*Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
 

Example 1:

    2
   / \
  1   3

Input: [2,1,3]
Output: true
Example 2:

    5
   / \
  1   4
     / \
    3   6

Input: [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.
*/

//SOLUTION: INORRDER TRAVERSAL:
/*
O(N) time complexity and \mathcal{O}(N)O(N) space complexity could be simple:

Compute inorder traversal list inorder.

Check if each element in inorder is smaller than the next one.

Do we need to keep the whole inorder traversal list?

Actually, no. The last added inorder element is enough to ensure at each step that the tree is BST (or not). Hence one could merge both steps into one and reduce the used space.

*/

class TreeNode_98 {
    int data;
    TreeNode_98 left;
    TreeNode_98 right;

    TreeNode_98 (final int item) {
        data = item;
        left = null;
        right = null;
    }
}


//INORDER: LEFT: ROOT RIGHT
class BinaryTree_Operations_98 {

        // Tree-1 with root1 is equal to Tree-2 with root-2:
        TreeNode_98 root;

        public boolean isValidBST() {
            Stack<TreeNode_98> stack = new Stack();
            double inorder = - Double.MAX_VALUE;
        
            while (!stack.isEmpty() || root != null) {
              while (root != null) {
                stack.push(root);
                root = root.left;
              }
              root = stack.pop();

              // If next element in inorder traversal
              // is smaller than the previous one
              // that's not BST.
              if (root.data <= inorder) return false;
              //STEP:2 ROOT  
              inorder = root.data;
              //STEP:3 RIGHT
              root = root.right;
            }
            return true;
          }


    }


class Validate_BST{

    public static void main(String args[]){
        BinaryTree_Operations_98 tree_1 = new BinaryTree_Operations_98(); 
            
        // TREE 1 
        /* Construct the following tree 
                2
               / \
              1     3       */
            
        tree_1.root = new TreeNode_98(2); 
        tree_1.root.right = new TreeNode_98(3); 
        tree_1.root.right.left = new TreeNode_98(1); 

        System.out.println(tree_1.isValidBST());
    }

}