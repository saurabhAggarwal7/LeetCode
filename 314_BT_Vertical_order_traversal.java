/**
 * 
 */


/**
 * THEORY:
 * 
 * TREEMAP:
 * The map is sorted according to the natural ordering of its keys, or by a Comparator provided at map creation time, depending on which constructor is used. This proves to be an efficient way of sorting and storing the key-value pairs.
 * 
 */

 
import java.util.*;

class TreeNode_314{
    TreeNode_314 left;
    TreeNode_314 right;
    int val;

    public TreeNode_314(int val){
        this.val = val;
    }
}

class BT_Vertical_order_traversal {

    static TreeNode_314 root;

    static List<List<Integer>> verticalOrder_implement() {
        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        helper(root, map);
        List<List<Integer>> result = new ArrayList<>();
        result.addAll(map.values());
        return result;
    }
     
    static void helper(TreeNode_314 t, TreeMap<Integer, ArrayList<Integer>> map) {
        if (t == null) {
            return;
        }
     
        LinkedList<TreeNode_314> q1 = new LinkedList<>();
        LinkedList<Integer> q2 = new LinkedList<>();
        q1.offer(t);
        q2.offer(0);
     
        while (!q1.isEmpty()) {
            TreeNode_314 node = q1.poll();
            int order = q2.poll();
     
            //add to map
            ArrayList<Integer> list = map.get(order);
            if (list == null) {
                list = new ArrayList<>();
                map.put(order, list);
            }
            list.add(node.val);
     
            if (node.left != null) {
                q1.offer(node.left);
                q2.offer(order - 1);
            }
     
            if (node.right != null) {
                q1.offer(node.right);
                q2.offer(order + 1);
            }
        }
    }

    public static void maoin(String args[]){
        BT_Vertical_order_traversal tree = new BT_Vertical_order_traversal(); 
        
        tree.root = new TreeNode_314(20); 
        tree.root.left = new TreeNode_314(8); 
        tree.root.right = new TreeNode_314(22); 
        tree.root.left.left = new TreeNode_314(4); 
        tree.root.left.right = new TreeNode_314(12); 
        tree.root.left.right.left = new TreeNode_314(10); 
        tree.root.left.right.right = new TreeNode_314(14); 

        /**
       20
      / \
     8   22
    / \
   4   12
       / \
      10  14

**/
        List<List<Integer>> result = tree.verticalOrder_implement();
        System.out.println(result.toString());

    }
}