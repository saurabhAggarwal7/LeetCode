/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 */

class TreeNode_LCA_BT {
    TreeNode_LCA_BT root;
    TreeNode_LCA_BT left;
    TreeNode_LCA_BT right;
    int val;
}

class LCA_BT {
    public TreeNode_LCA_BT lowestCommonAncestor(TreeNode_LCA_BT root, TreeNode_LCA_BT p, TreeNode_LCA_BT q) {
        if (root == p || root == q || root == null)
            return root;
        TreeNode_LCA_BT left = lowestCommonAncestor(root.left, p, q);
        TreeNode_LCA_BT right = lowestCommonAncestor(root.right, p, q);
        if (left == null)
            return right;
        else if (right == null)
            return left;
        else
            return root;
    }
}