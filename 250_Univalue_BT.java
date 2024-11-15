/**
 * Given a binary tree, count the number of uni-value subtrees.
A Uni-value subtree means all nodes of the subtree have the same value.
For example: Given binary tree,
              5
             / \
            1   5
           / \   \
          5   5   5
return 4.
This is a set of questions regarding trees, two pieces of information is needed, the solution is in the return of recursion, return a struct which contains both information.

 */

 /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Univalue_BT {


     class TreeNode {
          int val;
         TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }
     

    class CNode{

        boolean isUnique;
        int val;
        int size;
        CNode(){
            isUnique = true;
            size = 0;
            val = 0;
        }
    }


    public int countUnivalSubtrees(TreeNode root) {
        CNode c = count(root);
        return c.size;
    }

    CNode count(TreeNode root){
        CNode c = new CNode();
        if( root == null) return c;

        CNode left = count(root.left);
        CNode right = count(root.right);

        if(!left.isUnique || !right.isUnique){

            //Non Unique
            c.size = left.size + right.size;
            c.isUnique = false;

        }else{
            
            boolean lUnique = left.size == 0 || left.val == root.val;
            boolean rUnique = right.size == 0 || right.val == root.val;

            if( lUnique && rUnique){
                c.size = left.size + right.size + 1;

                c.isUnique = true;
            }else{
                c.size = left.size + right.size;
                c.isUnique = false;
            }
        }
        c.val = root.val;
        return c;
    }
}