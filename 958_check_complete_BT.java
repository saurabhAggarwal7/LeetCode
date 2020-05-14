/***
 * Given a binary tree, determine if it is a complete binary tree.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
Example 1

Input: [1,2,3,4,5,6]
Output: true
Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.
Example 2:
 */


//TRICK: Idea is if we do a level order traversal and we see a non emptyNode followed by an empty node, it isn't a complete binary tree.
import java.util.*;
class TreeNode_958 {
    TreeNode_958 left;
    TreeNode_958 right;
    int val;
}

class bt_check_complete_bt {
    static boolean isCompleteTree(TreeNode_958 root) {
        Queue<TreeNode_958> queue = new LinkedList<>();
        queue.offer(root);
        boolean seenEmpty = false;

        while (!queue.isEmpty()) {
            TreeNode_958 curr = queue.poll();
            if (curr == null) {
                seenEmpty = true;
                continue;
            } else if (seenEmpty) {
                return false;
            }

            queue.offer(curr.left);
            queue.offer(curr.right);
        }

        return true;
    }
}