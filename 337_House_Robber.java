/*The thief has found himself a new place for his thievery again. 
There is only one entrance to this area, called the "root." 
Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:

Input: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \ 
     3   1

Output: 7 
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:

Input: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \ 
 1   3   1

Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.*/

import java.util.*;

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    TreeNode(final int item) {
        data = item;
        left = null;
        right = null;
    }
}

class BinaryTree {
    TreeNode root;

    //Method-1 POST ORDER TRAVERSAL:
    //since post order, the root is accessed in last so post order works !
    static int rob_1(TreeNode root){
        if(root == null) return 0;
        int[] res = postOrder(root);
        return Math.max(res[0], res[1]);
    }

    static int[] postOrder(TreeNode node){
        if(node == null) return new int[2];

        int[] left = postOrder(node.left);
        int[] right = postOrder(node.right);

        int with = node.data + left[1] + right[1];
        int without = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[] {with, without};
    }

    //METHOD-2: MEMOIZATION, CHECK KEY:
    static int rob_2(TreeNode root){

        Map<TreeNode, Integer> memo = new HashMap<>();
        return do_rob_memo(root, memo);
    }

    static int do_rob_memo(TreeNode root, Map<TreeNode, Integer> memo){
        if(root == null) return 0;

        //root value from memo:
        if(memo.containsKey(root)){
            return memo.get(root);
        }
        
        //child and grandchild:
        int maxChild= 0;
        int maxGrandChild = 0;

        //LEFT Tree: Children and Grand Children:
        if(root.left != null){
            maxChild += do_rob_memo(root.left, memo);
            //Grand Child: LEFT > LEFT AND RIGHT
            maxGrandChild += do_rob_memo(root.left.left, memo) + do_rob_memo(root.left.right, memo);
        }

        //RIGHT Tree: Children and Grand Children
        if(root.right != null){
            maxChild += do_rob_memo(root.right, memo);
            //GRAND CHILD RIGHT> LEFT AND RIGHT
            maxGrandChild += do_rob_memo(root.right.left, memo) + do_rob_memo(root.right.right, memo);
        }

        int max_rob = Math.max(maxChild, root.data + maxGrandChild);
        memo.put(root, max_rob);
        return max_rob;
    }

}

class Rob_Binary_Tree {
    public static void main(String args[]) {
        BinaryTree tree = new BinaryTree();
        /*Input: [3,2,3,null,3,null,1]

            3
           / \
          2   3
           \   \ 
            3   1*/

        tree.root = new TreeNode(3);
        tree.root.right = new TreeNode(3);
        tree.root.left = new TreeNode(2);
        tree.root.left.right = new TreeNode(3);
        tree.root.right.right = new TreeNode(1);

    }
}
