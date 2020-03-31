/*Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
          */

//Solution:

/*Recurse the binary tree in Inorder Format, 
at every stage of function call pass on the address of last node in the flattened linked list so that 
current node can make itself a right node of the last node.

For left child, it’s parent node is the last node in the flattened list


For the right child there are two conditions:
If there is no left child to the parent, parent node is the last node in the flattened list.
If left child is not null then leaf node from left sub-tree is the last node in the flattened list.
*/

class Tree_To_List{

//tree structure to represent data:
static class Node{
    int data;
    Node left;
    Node right;
}

//allocate the data to the tree node:
static Node AllocNode(int data){
    Node temp = new Node();
    temp.left = null;
    temp.right = null;
    temp.data = data;
    return temp;
}

//print inorder traversal of the tree
static void printInorderTree(Node root){
    if(root==null) return;
    printInorderTree(root.left);
    System.out.println(root.data + " ");
    printInorderTree(root.right);
}

//Last Node:
static Node last = null;

//make current node, right of the last node in the list:
static void FlattenBinaryTree(Node root){

    //root is 1, with left and right subtrees with debug as mentioned below:
    if (root == null) return;

    //left and right tree sub tree: 
    //debug is : 
    //left = root(2) left = 3, right = 4
    //Right = root(5), left = null right = 6

    Node left = root.left;
    Node right = root.right;

    //Boundry case:
    //avoid first iteration, where root is the only node in the list:
    
    //Last node's right is the root
    //Last Node's left is NULL
    //Last Node is the Root

    //debug:
    //first iteration: root(1) == last(1) so skip this block:
    //second iteration, left subtree so, root(2) and the last stil remains the same whic is root(1), so now it will go inside the code block:

    if(root != last){

        //make the right of the last as root:
        //keep the lett as NULL as per our requirements
        //make the last as root again, which is the new last
        (last).right = root;
        (last).left = null;
        last = root;
    }

    //first iteration, so recursion will take place and now the rot for this recursion becomes the left subtree
    FlattenBinaryTree(left);
    FlattenBinaryTree(right);

    if(left == null && right == null){
        last = root;
    }

}

//driver function:
public static void main(String args[]) 
{ 

	// Build the tree 
	Node root = AllocNode(1); 
	root.left = AllocNode(2); 
	root.left.left = AllocNode(3); 
	root.left.right = AllocNode(4); 
	root.right = AllocNode(5); 
	root.right.right = AllocNode(6); 

	// Print the inorder traversal of the 
	// original tree 
	System.out.print("Original inorder traversal : "); 
	printInorderTree(root); 
	System.out.println(); 

	// Flatten a binary tree, at the beginning 
	// root node is the only and last in the list 
	last = root; 
	FlattenBinaryTree(root); 

	// Print the inorder traversal of the flattened 
	// binary tree 
	System.out.print("Flattened inorder traversal : "); 
	printInorderTree(root); 
	System.out.println(); 
	
} 

}

