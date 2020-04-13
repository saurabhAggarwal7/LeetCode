/*Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).

The binary search tree is guaranteed to have unique values.

 

Example 1:

Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
Output: 32
Example 2:

Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
Output: 23 */

//All 3 methods will DFS traverse all nodes in worst case, and if we count in the recursion trace space cost, the complexities are as follows:
//Time: O(n), space: O(h), where n is the number of total nodes, h is the height of the tree..

import java.util.*;
class TreeNode_938{
    TreeNode_938 left;
    TreeNode_938 right;
    int val;
}

class Range_Sum_BST{
    //Recursive solution:
    //IDEA: If node.val falls outside the range [L, R], (for example node.val < L), then we know that only the right branch could have nodes with value inside [L, R].

    //METHOD-1:
    public int range_sum_BST(TreeNode_938 root, int L, int R){
        if(root == null)
            return 0;
        
        if(root.val <= L) //smaller than lower range then exclude left traversal
            return range_sum_BST(root.right, L, R) + (root.val == L ? root.val : 0);
        
        if(root.val >= R) //more than higher than right so we exclude the right traversal
            return range_sum_BST(root.left, L, R) + (root.val == R ? root.val : 0);
        
        return range_sum_BST(root.left, L, R) + range_sum_BST(root.right, L, R);
    }

    //METHOD-2:
    int ans;
    public int range_sum_BST_2(TreeNode_938 root, int L, int R) {
        ans = 0;
        dfs(root, L, R);
        return ans;
    }

    public void dfs(TreeNode_938 node, int L, int R) {
        if (node != null) {
            if (L <= node.val && node.val <= R)
                ans += node.val;
            if (L < node.val)
                dfs(node.left, L, R);
            if (node.val < R)
                dfs(node.right, L, R);
        }
    }

    //METHOD-3: Iterative solution:
    public int rangeSumBST_3(TreeNode_938 root, int L, int R) {
        Stack<TreeNode_938> st = new Stack<>();
        st.add(root);
        int sum = 0;
        while (!st.isEmpty()){
            TreeNode_938 n = st.pop();
            if (n == null) continue;
            if (n.val >= L && n.val <= R) sum += n.val;
            if (n.val > L) st.push(n.left);
            if (n.val < R) st.push(n.right);
        }
        return sum;
    }


}

class Range_Sum_BST_display{
    //display:
}