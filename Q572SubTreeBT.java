

    class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
        TreeNode nextRight;

        TreeNode (final int item) {
            data = item;
            left = null;
            right = null;
            nextRight = null;
        }
    }

    class BinaryTree {

        // Tree-1 with root1 is equal to Tree-2 with root-2:
        TreeNode root1;
        TreeNode root2;

        boolean areIdentical(TreeNode root1, TreeNode root2) {
            if (root1 == null && root2 == null)
                return true;
            if (root1 == null || root2 == null)
                return false;

            boolean value1 = root1.data == root2.data;
            boolean value2 = areIdentical(root1.left, root2.left);
            boolean value3 = areIdentical(root1.right, root2.right);
            boolean result = value1 && value2 && value3;
            return result;
        }

        //check if S is subtree of T or not
        boolean isSubtree(TreeNode T, TreeNode S){
            if (S== null) {return true;}
            if (T== null) { return false;}

            if(areIdentical(T, S)) {return true;}

            /* If the tree with root as current node doesn't match then 
		    try left and right subtrees one by one */
            boolean left = isSubtree(T.left, S);
            boolean right = isSubtree(T.right, S);
            return left || right;
        }
    }


    class Q572SubTreeBT{

        public static void main(String args[]) 
        { 
            BinaryTree tree = new BinaryTree(); 
            
            // TREE 1 
            /* Construct the following tree 
                26 
                / \ 
                10	 3 
            / \	 \ 
            4	 6	 3 
            \ 
                30 */
                
            tree.root1 = new TreeNode(26); 
            tree.root1.right = new TreeNode(3); 
            tree.root1.right.right = new TreeNode(3); 
            tree.root1.left = new TreeNode(10); 
            tree.root1.left.left = new TreeNode(4); 
            tree.root1.left.left.right = new TreeNode(30); 
            tree.root1.left.right = new TreeNode(6); 
    
            // TREE 2 
            /* Construct the following tree 
            10 
            / \ 
            4	 6 
            \ 
            30 */
                
            tree.root2 = new TreeNode(10); 
            tree.root2.right = new TreeNode(6); 
            tree.root2.left = new TreeNode(4); 
            tree.root2.left.right = new TreeNode(30); 
    
            if (tree.isSubtree(tree.root1, tree.root2)) 
                System.out.println("Tree 2 is subtree of Tree 1 "); 
            else
                System.out.println("Tree 2 is not a subtree of Tree 1"); 
        } 
    }