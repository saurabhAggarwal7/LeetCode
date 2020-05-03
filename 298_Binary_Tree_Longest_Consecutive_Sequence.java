/**
 * Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
 */

         /**
       1
      / \
     2   10
    / \
   100   3
       / \
      1000  4

**/

//**Note: it's a Binary tree and not a BST: */

 //BFS::

 import java.util.*;

class TreeNode_298{
    TreeNode_298 left;
    TreeNode_298 right;
    int val;

    public TreeNode_298(int val){
        this.val = val;
    }
}
class Binary_Tree_Longest_Consecutive_Sequence {
    static TreeNode_298 root;

    static int longestConsecutive() {
        if(root==null)
            return 0;
     
        LinkedList<TreeNode_298> nodeQueue = new LinkedList<TreeNode_298>();
        LinkedList<Integer> sizeQueue = new LinkedList<Integer>();

                 /**
       1
      / \
     2   10
    / \
   100   3
       / \
      1000  4

**/
     
        //1

        nodeQueue.offer(root); 

        //1
        sizeQueue.offer(1);
        int max=1;
     
        while(!nodeQueue.isEmpty()){

            //itr-1: head=1, nodeQueue=<>
            TreeNode_298 head = nodeQueue.poll(); 

            //itr-1: size=1, sizeQueue=<>
            int size = sizeQueue.poll(); 
            
            //LEFT:
            if(head.left!=null){
                //itr-1: left: 2
                //itr-1: leftSize=1

                int leftSize=size;
                if(head.val==head.left.val-1){
                    //itr-1: leftSize=2 now
                    leftSize++; 

                    //itr-1: max=2
                    max = Math.max(max, leftSize);
                }else{
                    leftSize=1;
                }
                
                //itr-1: nodeQueue<2> now from <>
                nodeQueue.offer(head.left);
                //itr-1: sizeQueue<2> from <>
                sizeQueue.offer(leftSize);
            }
            
            //RIGHT:
            if(head.right!=null){
                //itr-1: right is 10
                //itr-1: rightSize is 2
                int rightSize=size;
                if(head.val==head.right.val-1){
                    //itr-1: no
                    rightSize++;
                    max = Math.max(max, rightSize);
                }else{
                    //itr-1: rightsize=1
                    rightSize=1;
                }
                
                //itr-1: nodeQueue now <2, 10>
                nodeQueue.offer(head.right);
                //itr-1: sizeQueue now <2>
                sizeQueue.offer(rightSize);
            }
        }
        
        return max;
    }

    public static void main(String args[]){
        Binary_Tree_Longest_Consecutive_Sequence tree = new Binary_Tree_Longest_Consecutive_Sequence(); 
        tree.root = new TreeNode_298(1); 
        tree.root.left = new TreeNode_298(2); 
        tree.root.right = new TreeNode_298(10); 
        tree.root.left.left = new TreeNode_298(100); 
        tree.root.left.right = new TreeNode_298(3); 
        tree.root.left.right.left = new TreeNode_298(1000); 
        tree.root.left.right.right = new TreeNode_298(4); 

        /**
       1
      / \
     2   10
    / \
   100   3
       / \
      1000  4

**/

        System.out.println(tree.longestConsecutive());
    }
}