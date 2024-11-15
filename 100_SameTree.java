/*Given two binary trees, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical and the nodes have the same value.

Example 1:

Input:     1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

Output: true
Example 2:

Input:     1         1
          /           \
         2             2

        [1,2],     [1,null,2]

Output: false
Example 3:

Input:     1         1
          / \       / \
         2   1     1   2

        [1,2,1],   [1,1,2]

Output: false */

class TreeNode_100{
    TreeNode_100 right;
    TreeNode_100 left;
    int val;

}
class Recrsion_Check_Same_Trees {
    public boolean isSameTree(TreeNode_100 p, TreeNode_100 q) {
      // p and q are both null
      if (p == null && q == null) return true;
      // one of p and q is null
      if (q == null || p == null) return false;
      if (p.val != q.val) return false;
      return isSameTree(p.right, q.right) &&
              isSameTree(p.left, q.left);
    }
  }