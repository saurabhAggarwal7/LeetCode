/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example: 

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */

 /**
  * CONCEPT:
  The idea is simple: print the tree in pre-order traversal and use "X" to denote null node and split node with ",". We can use a StringBuilder for building the string on the fly. For deserializing, we use a Queue to store the pre-order traversal and since we have "X" as null node, we know exactly how to where to end building subtress.
  */

  /**
       20
      / \
     8   22
    / \
   4   12
       / \
      10  14

**/

// Definition for a binary tree node.
import java.util.*;
class TreeNode_297 {
    int val;
    TreeNode_297 left;
    TreeNode_297 right;
    TreeNode_297(int x) {
         val = x; 
    }
}


class serialize_Deserialize_BT{

    TreeNode_297 root;

    private static final String splitter = ","; //splitter used at the time for deserialzie
    private static final String Null_X = "X"; //null for serialize

    //ENCODES the tree into a single string:
    String serialize(){
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode_297 node, StringBuilder sb){
        if(node == null) {
            sb.append(Null_X).append(splitter);
        } else{
            sb.append(node.val).append(splitter);

            //Left Subtree String building
            buildString(node.left, sb);

            //Right subtree String building
            buildString(node.right, sb);
        }
    }

    //DECODES: Decodes your encoded data to tree.
    TreeNode_297 deserialize(String data){
        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(splitter)));
        return buildTree(nodes);
    }

    /**
     * 
        Dequeue nodes ::
        0:"20"
        1:"8"
        2:"4"
        3:"X"
        4:"X"
        5:"12"
        6:"10"
        7:"X"
        8:"X"
        9:"14"
        10:"X"
        11:"X"
        12:"22"
        13:"X"
        14:"X"
     */

    private TreeNode_297 buildTree(Deque<String> nodes){
        String val = nodes.remove();
        if(val.equals(Null_X)){
            return null;
        } else{
            TreeNode_297 node = new TreeNode_297(Integer.valueOf(val));
            
            //Build Subtree <LEFT> recursion
            node.left = buildTree(nodes);

            //Build Subtree <RIGHT> recursion
            node.right = buildTree(nodes);
            return node;
        }
    }

    public static void main(String args[]){

        serialize_Deserialize_BT tree = new serialize_Deserialize_BT(); 
        tree.root = new TreeNode_297(20); 
        tree.root.left = new TreeNode_297(8); 
        tree.root.right = new TreeNode_297(22); 
        tree.root.left.left = new TreeNode_297(4); 
        tree.root.left.right = new TreeNode_297(12); 
        tree.root.left.right.left = new TreeNode_297(10); 
        tree.root.left.right.right = new TreeNode_297(14); 

        /**
       20
      / \
     8   22
    / \
   4   12
       / \
      10  14

**/

//

        String result = tree.serialize();
        System.out.println(result);
        TreeNode_297 tree_result = tree.deserialize(result);
        System.out.println(tree_result);
        
    }
}

