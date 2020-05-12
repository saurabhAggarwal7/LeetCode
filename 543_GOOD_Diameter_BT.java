

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
Given a binary tree
          1
         / \
        2   3
       / \     
      4   5    
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.
 */

/**SOLUTION
 MaxDepth is basically a simple height()
 * function, finding the height of a tree rooted at the given node.
 *
 * What makes it clever is as it is doing this, it is also
 * adding taking the computed heights of it's subtrees to find the
 * maximum diameter that passes through the given node as a root.
 * If this is the global maximum diameter, it updates the class member.
 */

 /**
  * we want to use the max height from recursion each time hence we are returning at call 
  return Math.max(left, right) + 1;
  */

 class TreeNode_543{
    TreeNode_543 left;
    TreeNode_543 right;
    int data;
    TreeNode_543(int data){
        this.data = data;
    }
 }

 //Diameter = Max ( leftHeight + rightHeight + 1, Max (leftDiameter, rightDiameter) )
class diamtere_BT {
    int max = 0;
    TreeNode_543 root;
    
    public int diameterOfBinaryTree() {

        // This will find the max depth going through each node,
        // and update the global maximum to the class member 'max'
        maxDepth(root);
        
        // Return the global maximum
        return max;
    }
    
    private int maxDepth(TreeNode_543 root) {
        // Height of null is 0
        if (root == null) {
            //root = 4 it's left = null
            //root = 4 it's right = null
            //root=5, its; left and right both null so return 0
            return 0;
        }

                /*Given a binary tree
          1
         / \
        2   3
       / \     
      4   5    */
        
        // Find height of left and right subTrees

        //1
        //2
        //4
        //then root left is null, return 0, returned 0 from above, recusrsion ends for root=4 proceed next, but 1,2 are still instack to compute
        int left = maxDepth(root.left);

        //root=4, returned 0 from above
        //root=2, recursion bactrack so  now this will run, it's right is 5, run the fuction for 5 again

        //root=2's right:
        //root=5

        //root=2's right is finished so we can come back to root=2 and use it's elements computation as it's computation which are left+right = 1+1
        int right = maxDepth(root.right);
        
        // New global max is either already reached,
        // or is acheived using this node as the root

        //root=4, max = max, 0+0 = 0
        //root=5, max=0+0 =0
        max = Math.max(max, left + right);
        
        // Return height of tree rooted at this node

        //root=4, max(0,0) +1 =1 that means when root=4 max diameter is 1
        //root=5, max(0,0) +1 =1 that means when root=4 max diameter is 1
        return Math.max(left, right) + 1;
    }

    public static void main(String args[]){
        diamtere_BT tree_1 = new diamtere_BT();
        /*Given a binary tree
          1
         / \
        2   3
       / \     
      4   5    */
        tree_1.root = new TreeNode_543(1); 
        tree_1.root.right = new TreeNode_543(3); 
        tree_1.root.left = new TreeNode_543(2); 
        tree_1.root.left.left = new TreeNode_543(4); 
        tree_1.root.left.right = new TreeNode_543(5); 

        System.out.println(tree_1.diameterOfBinaryTree()); //3
    }
}