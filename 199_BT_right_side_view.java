/**
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
 */


import java.util.*; 
class TreeNode_199{
    TreeNode_199 left;
    TreeNode_199 right;
    int val;
}

class BT_right_side_view {

    //BFS:
    public List<Integer> rightSideView_BFS(TreeNode_199 root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) return res;

        Queue<TreeNode_199> queue = new LinkedList<TreeNode_199>();
        queue.add(root);

        int size = queue.size();
        while (!queue.isEmpty() && size -- > 0){

            TreeNode_199 curr_node = queue.remove();
            if(curr_node.left != null)
                queue.add(curr_node.left); 
            if(curr_node.right != null)
                queue.add(curr_node.right); 
            
            //when queue empties it usally at right side view
            if (size == 0){
                res.add(curr_node.val);
                size = queue.size();
            }
        }
        return res;
    }

    //DFS:-----
    /**
     * The core idea of this algorithm:
    1.Each depth of the tree only select one node.
    2. View depth is current size of result list.
     */
    public List<Integer> rightSideView_DFS(TreeNode_199 root) {
        List<Integer> result = new ArrayList<Integer>();
        rightView(root, result, 0);
        return result;
    }
    
    public void rightView(TreeNode_199 curr, List<Integer> result, int currDepth){
        if(curr == null){
            return;
        }
        if(currDepth == result.size()){
            result.add(curr.val);
        }
        
        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);
        
    }
}