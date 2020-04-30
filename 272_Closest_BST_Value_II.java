
/**
 * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:

Given target value is a floating point.
You may assume k is always valid, that is: k≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286, and k = 2

    4
   / \
  2   5
 / \
1   3

Output: [4,3]
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

 SOLUTION:::
 tags: Stack, Tree
time: O(n)
space: O(n)

#### Method1: Stack, DFS, Inorder Traversal
- find successors and predecessors using BST (both list will be sorted); in the end, we can easily get top k from the two sorted list
    - with BST: **inorder traversal gives us sorted predecessors
    - with BST: **reversed-inorder traversal gives us sorted successors
    - smallest on top of the stack
- time: O(n) visit all nodes, O(k) to output
- space overall: O(n) to store all nodes

#### Method2: BFS, brutle force
- Itereate over all nodes and maintain pq<TreeNode> (improvemenet point: how to avoid traversing entire tree?)
- prioritize nodes that are closer to target, so we may stop early when result reaches k candidates
- time: O(n*logn)
- kinds slow and not utilizing BST
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
/*
Method1: DFS + Stack
- find successors and predecessors using BST (both list will be sorted); in the end, we can easily get top k from the two sorted list
    - with BST: inorder traversal gives us sorted predecessors
    - with BST: reversed-inorder traversal gives us sorted successors
- time: O(n) visit all nodes, O()
- space overall: O(n) to store all nodes
*/

/**
 * #### Method1: Stack, DFS, Inorder Traversal
- find successors and predecessors using BST (both list will be sorted); in the end, we can easily get top k from the two sorted list
    - with BST: **inorder traversal gives us sorted predecessors
    - with BST: **reversed-inorder traversal gives us sorted successors
    - smallest on top of the stack
- time: O(n) visit all nodes, O(k) to output
- space overall: O(n) to store all nodes
 */

 import java.util.*;

 class TreeNode_272{
    TreeNode_272 left;
    TreeNode_272 right;
    int val;

    TreeNode_272(int x){
        this.val = x;
    }
 }
 
/**
 * #### Method1: Stack, DFS, Inorder Traversal
- find successors and predecessors using BST (both list will be sorted); in the end, we can easily get top k from the two sorted list
    - with BST: **inorder traversal gives us sorted predecessors
    - with BST: **reversed-inorder traversal gives us sorted successors
    - smallest on top of the stack
- time: O(n) visit all nodes, O(k) to output
- space overall: O(n) to store all nodes
 */

class Closest_BST_Value_II_DFS_Stack {

    TreeNode_272 root;

    public List<Integer> closestKValues(double target, int k) {

        //pre //successors:
        Stack<Integer> predecessors = new Stack<>();
        Stack<Integer> successors = new Stack<>();

        inorder(predecessors, root, target);
        reverseInorder(successors, root, target);
        
        List<Integer> result = new ArrayList<>();

        //k =2 
        while(k-- > 0) {
            if (predecessors.isEmpty()) 
                result.add(successors.pop());
            else if (successors.isEmpty()) 
                result.add(predecessors.pop());
            else {
                int valA = predecessors.peek();
                int valB = successors.peek();

                if(Math.abs(valA - target) < Math.abs(valB - target)) 
                    result.add(predecessors.pop());
                else 
                    result.add(successors.pop());
            }
        }
        return result;
    }

    // produce sorted predecessors (smallest on top of stack)
    private void inorder(Stack<Integer> stack, TreeNode_272 node, double target) {
        if (node == null) 
            return;

        inorder(stack, node.left, target);

        if (node.val > target) 
            return;

        stack.push(node.val);
        inorder(stack, node.right, target);
    }
    
    // produce sorted successors (smallest on top of stack)
    private void reverseInorder(Stack<Integer> stack, TreeNode_272 node, double target) {
        if (node == null) 
            return;

        reverseInorder(stack, node.right, target);
        if (node.val <= target) 
            return;
        
        stack.push(node.val);
        reverseInorder(stack, node.left, target);
    }
}

/*
Method2: BFS
- eventuall we have to itereate over all nodes and maintain pq of size k.
- can prioritize nodes that are closer to target, so we may stop early when result reaches k
- time: O(n*logn)
- kinds slow
*/
class Closest_BST_Value_II_BFS_Stack {
    TreeNode_272 root;

    public List<Integer> closestKValues(double target, int k) {
        PriorityQueue<TreeNode_272> queue = buildQueue(k, target);
        PriorityQueue<TreeNode_272> rst = buildQueue(k, target);
        
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode_272 node = queue.poll();
            rst.offer(node);
            
            if (node.left != null) 
                queue.offer(node.left);
            if (node.right != null) 
                queue.offer(node.right);
            
            while(rst.size() == k) 
                break;
        }
        
        List<Integer> list = new ArrayList<>();
        while (list.size() != k) 
            list.add(rst.poll().val);
        
        return list;
    }
    
        
    private PriorityQueue<TreeNode_272> buildQueue(int k, double target) {
        return new PriorityQueue<TreeNode_272>(k, new Comparator<TreeNode_272>() {
            public int compare(TreeNode_272 a, TreeNode_272 b) {
                if (Math.abs(a.val - target) - Math.abs(b.val - target) <= 0) return -1;
                return 1;
            }
        });
    }
} 

class closest_bst_value_II_implement {

    public static void main(String args[]){
        Closest_BST_Value_II_DFS_Stack tree_1 = new Closest_BST_Value_II_DFS_Stack(); 

        //Input: root = [4,2,5,1,3], target = 3.714286, and k = 2
            
        // TREE 1 
        /* Construct the following tree 
    4
   / \
  2   5
 / \
1   3
       */
            
        tree_1.root = new TreeNode_272(4); 
        tree_1.root.right = new TreeNode_272(5); 
        tree_1.root.left = new TreeNode_272(2); 
        tree_1.root.left.left = new TreeNode_272(1); 
        tree_1.root.left.right = new TreeNode_272(3); 

        System.out.println(tree_1.closestKValues(3.714286, 2));

        //Method-2::
        Closest_BST_Value_II_BFS_Stack tree_2 = new Closest_BST_Value_II_BFS_Stack();  
        tree_2.root = new TreeNode_272(4); 
        tree_2.root.right = new TreeNode_272(5); 
        tree_2.root.left = new TreeNode_272(2); 
        tree_2.root.left.left = new TreeNode_272(1); 
        tree_2.root.left.right = new TreeNode_272(3); 


        System.out.println(tree_2.closestKValues(3.714286, 2));

    }

}