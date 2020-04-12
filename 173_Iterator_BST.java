//first thought was to use inorder traversal to put every node into an array, and then make an index pointer for the next() and hasNext(). That meets the O(1) run time but not the O(h) memory. O(h) is really much more less than O(n) when the tree is huge.

/*once you get to a TreeNode, in order to get the smallest, you need to go all the way down its left branch. So our first step is to point to pointer to the left most TreeNode. The problem is how to do back trace. Since the TreeNode doesn't have father pointer, we cannot get a TreeNode's father node in O(1) without store it beforehand. Back to the first step, when we are traversal to the left most TreeNode, we store each TreeNode we met ( They are all father nodes for back trace).

After that, I try an example, for next(), I directly return where the pointer pointing at, which should be the left most TreeNode I previously found. What to do next? After returning the smallest TreeNode, I need to point the pointer to the next smallest TreeNode. When the current TreeNode has a right branch (It cannot have left branch, remember we traversal to the left most), we need to jump to its right child first and then traversal to its right child's left most TreeNode. When the current TreeNode doesn't have a right branch, it means there cannot be a node with value smaller than itself father node, point the pointer at its father node. */

import java.util.*;

class TreeNode_173 {
    TreeNode_173 left;
    TreeNode_173 right;
    int data;
}

class BST_Iterator {
    Stack<TreeNode_173> stack;

    public BST_Iterator(TreeNode_173 root) {
        stack = new Stack<>();
        fillStack(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode_173 curNode = stack.pop();
        fillStack(curNode.right);
        return curNode.data;
    }

    private void fillStack(TreeNode_173 root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

}// bst iterator ends

class Design_BST_Itr {
    public static void main(String args[]) {
        //
    }
}