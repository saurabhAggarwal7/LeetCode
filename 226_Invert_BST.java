import java.util.LinkedList;

/**
 Invert a binary tree.

Example:

Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9
Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1
 */

 //SOLUTION-1: Recursive:
 //Because of recursion, O(h) function calls will be placed on the stack in the worst case, where hh is the height of the tree. Because h\in O(n), the space complexity is O(n).

 //SOLUTION-2: Iterative
 /*
Since each node in the tree is visited / added to the queue only once, the time complexity is 
O(n)
O(n), where 
n
n is the number of nodes in the tree.

Space complexity is 
O(n)
O(n), since in the worst case, the queue will contain all nodes in one level of the binary tree. For a full binary tree, the leaf level has 
\lceil \frac{n}{2}\rceil=O(n)
⌈2n​⌉=O(n) leaves.

 */

 import java.util.*;

 class TreeNode_226{
    int data;
    TreeNode_226 left;
    TreeNode_226 right;

    TreeNode_226(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }

    //SOLUTION-1: Recursive:
    public TreeNode_226 invertTree(TreeNode_226 root){
        if(root == null) return null;

        TreeNode_226 right = invertTree(root.right);
        TreeNode_226 left = invertTree(root.left);

        //Invert the Left and Right Nodes of the Tree:
        root.left = right;
        root.right = left;
        return root;
    }

    //SOLUTION: 2 Iterative using Queues:
    public TreeNode_226 invertTree_queue(TreeNode_226 root){
        if(root== null) return null;

        //create a queue to store the traversed values from the tree
        Queue<TreeNode_226> queue = new LinkedList<TreeNode_226>();
        queue.add(root);

        while(!queue.isEmpty()){
            //FIFO:
            TreeNode_226 current = queue.poll();
            TreeNode_226 temp = current.left;

            //Reverese
            current.left = current.right;
            current.right = temp;

            if(current.left != null) queue.add((current.left));
            if(current.right != null) queue.add(current.right);

        }
        return root;
    }



 }

