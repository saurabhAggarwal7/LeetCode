/*Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3 */

import java.util.*;

class TreeNode_257{
    TreeNode_257 left;
    TreeNode_257 right;
    int data;
}

//DEPTH FIRST SEARCH:
//RECURSION NORMALLY USED:
class binary_tree_paths{

    public List<String> binaryTreePaths(TreeNode_257 root){
        ArrayList<String> result = new ArrayList<>();
        String solution = "";

        DFS_tree(root, solution, result);
        return null;
    }

    public void DFS_tree(TreeNode_257 root, String solution, ArrayList<String> result){
            if(root == null) 
                return;

            //(solution + root.data) means we are mainting the context of the array string from the past
            //LEAF node: Depth is reached now, add the result in <> array
            
            if(root.left == null && root.right == null){
                    //continue append in result string:
                    result.add(solution + root.data);
            }

            //DFS: LEFT
            DFS_tree(root.left, solution + root.data + "->", result);

            //DFS: RIGHT
            DFS_tree(root.right, solution + root.data + "->", result);

    }

}