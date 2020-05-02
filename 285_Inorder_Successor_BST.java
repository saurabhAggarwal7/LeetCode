//INORDER IS LEFT ROOT RIGHT

/**
       20
      / \
     8   22
    / \
   4   12
       / \
      10  14

**/

//Inorder Successort of 20-> 22, 8->12, 12->14, 4->8

// Definition for a binary tree node.
class TreeNode_285 {
    int val;
    TreeNode_285 left;
    TreeNode_285 right;
    TreeNode_285(int x) { val = x; }
}

class Inorder_Successor_BST {
    TreeNode_285 root; 

    //node = 12, result shoudl be 14
    public TreeNode_285 inorderSuccessor(TreeNode_285 node) {
        if(root==null){
            return null;
        }
     
        TreeNode_285 next = null;

        while(root !=null && root.val!=node.val){

            //LEFT:
            if(root.val > node.val){

                //step-1 move left
                next = root;
                root = root.left;
            }else{

            //RIGHT:
                //step-2: move right
                root= root.right;
            }
        }

        //after step-2 we reach to our input node successfully mow reach it's successor which is inorder
     
        if(root==null)        
            return null;
     
        if(root.right==null)
            return next; //due to inorder
     
        root = root.right; //14 bcz 12(right)->14

        //no children of 14 so while loop wont run in this case
        while(root.left!=null)
            root = root.left;
     
        return root; //14
    }

    public static void main(String args[]){

        Inorder_Successor_BST tree = new Inorder_Successor_BST(); 
        tree.root = new TreeNode_285(20); 
        tree.root.left = new TreeNode_285(8); 
        tree.root.right = new TreeNode_285(22); 
        tree.root.left.left = new TreeNode_285(4); 
        tree.root.left.right = new TreeNode_285(12); 
        tree.root.left.right.left = new TreeNode_285(10); 
        tree.root.left.right.right = new TreeNode_285(14); 

        /**
       20
      / \
     8   22
    / \
   4   12
       / \
      10  14

**/

//Inorder Successort of 20-> 22, 8->12, 12->14, 4->8

        TreeNode_285 testNode = new TreeNode_285(12);
        TreeNode_285 result_node = tree.inorderSuccessor(testNode);

        System.out.println(result_node.val);
        
    }
}