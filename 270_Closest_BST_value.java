

/* 
Given a binary search tree and a target node K. The task is to find the node with minimum absolute difference with given target value K.
*/

//SOLUTION:

/*An efficient solution for this problem is to take advantage of characteristics of BST. Here is the algorithm to solve this problem :

If target value K is present in given BST, then it’s the node having minimum absolute difference.
If target value K is less than the value of current node then move to the left child.
If target value K is greater than the value of current node then move to the right child. */

class TreeNode_270 {
    TreeNode_270 left;
    TreeNode_270 right;
    int data;
}

class closest_BST {
    int min_diff;
    int min_diff_key;

    // Function to find node with minimum absolute
    // difference with given K
    // min_diff -. minimum difference till now
    // min_diff_key -. node having minimum absolute
    // difference with K

    private void max_diff_util(TreeNode_270 root, int k){
        if(root == null)
            return;
        
        // if we find the key now after recusrive calls finally:
        if(root.data == k){
            min_diff_key = k;
            return;
        }

        //STEP-1:
        // update min_diff and min_diff_key by checking 
        // current node value 
        if(min_diff > Math.abs(root.data - k)){
            min_diff = Math.abs(root.data - k);
            min_diff_key = root.data;
        }     
        
        //STEP-2:
        //k < root.data then Left Subtree
        if(k < root.data)
            max_diff_util(root.left, k);
        else
        //k > root.data then RightSubTree
            max_diff_util(root.right, k);
    }

    public int max_diff(TreeNode_270 root, int k){
        min_diff = Integer.MAX_VALUE;
        min_diff_key = -1;

        //get the max difference:
        max_diff_util(root, k);

        return min_diff_key;
    }

}

class closest_BST_display {
    public static void main(String args[]) {
        TreeNode_270 root = new TreeNode_270();
        //

    }
}