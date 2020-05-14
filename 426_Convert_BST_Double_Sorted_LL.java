/***
 * 
 * Description
 * 
https://www.lintcode.com/problem/convert-binary-search-tree-to-sorted-doubly-linked-list/description

Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.

Let's take the following BST as an example, it may help you understand the problem better

We want to transform this BST into a circular doubly linked list. Each node in a doubly linked list has a predecessor and successor. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

The figure below shows the circular doubly linked list for the BST above. The "head" symbol means the node it points to is the smallest element of the linked list.

LL
1--2--3--4--5
            |
<------------

tree
    4
    /|
    2 5
   /|
  1 3

  Specifically, we want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. We should return the pointer to the first element of the linked list.

The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.

Example
Example 1:

Input: {4,2,5,1,3}
        4
       /  \
      2   5
     / \
    1   3

Output: "left:1->5->4->3->2  right:1->2->3->4->5"
Explanation:
Left: reverse output
Right: positive sequence output
Example 2:

Input: {2,1,3}
        2
       /  \
      1   3
Output: "left:1->3->2  right:1->2->3"

 */


 /**
  * solution: https://www.geeksforgeeks.org/convert-a-binary-tree-to-a-circular-doubly-link-list/
Given a Binary Tree_426, convert it to a Circular Doubly Linked List (In-Place).

The left and right pointers in nodes are to be used as previous and next pointers respectively in converted Circular Linked List.
The order of nodes in List must be same as Inorder of the given Binary Tree_426.
The first node of Inorder traversal must be head node of the Circular List.

The idea can be described using below steps.
1) Write a general purpose function that concatenates two given circular doubly lists (This function is explained below).

2) Now traverse the given tree
….a) Recursively convert left subtree to a circular DLL. Let the converted list be leftList.
….a) Recursively convert right subtree to a circular DLL. Let the converted list be rightList.

….c) Make a circular linked list of root of the tree, make left and right of root to point to itself.

….d) Concatenate leftList with list of single root node.
….e) Concatenate the list produced in step above (d) with rightList

Note that the above code traverses tree in Postorder fashion. We can traverse in inorder fashion also. We can first concatenate left subtree and root, then recur for right subtree and concatenate the result with left-root concatenation.

How to Concatenate two circular DLLs?

Get the last node of the left list. Retrieving the last node is an O(1) operation, 
since the prev pointer of the head points to the last node of the list.
Connect it with the first node of the right list
Get the last node of the second list
Connect it with the head of the list.
Below are implementations of above idea.
  */

  // Java Program to convert a Binary Tree_426 to a 
// Circular Doubly Linked List 

// Node_426 class represents a Node_426 of a Tree_426 

//decleare the node class like we do it for a tree
class Node_426 
{ 
	int val; 
	Node_426 left,right; 

	public Node_426(int val) 
	{ 
		this.val = val; 
		left = right = null; 
	} 
} 

// A class to represent a tree 
class Tree_426 
{ 
	Node_426 root; 
	public Tree_426() 
	{ 
		root = null; 
	} 

	// concatenate both the lists and returns the head // of the List 
	public Node_426 concatenate(Node_426 leftList,Node_426 rightList) 
	{ 
		// If either of the list is empty, then 
		// return the other list 
		if (leftList == null) 
			return rightList; 
		if (rightList == null) 
			return leftList; 

		// Store the last Node_426 of left List 
		Node_426 leftLast = leftList.left; 

		// Store the last Node_426 of right List 
		Node_426 rightLast = rightList.left; 

		// Connect the last node of Left List 
		// with the first Node_426 of the right List 
		leftLast.right = rightList; 
		rightList.left = leftLast; 

		// left of first node refers to 
		// the last node in the list 
		leftList.left = rightLast; 

		// Right of last node refers to the first 
		// node of the List 
		rightLast.right = leftList; 

		// Return the Head of the List 
		return leftList; 
	} 

	// Method converts a tree to a circular 
	// Link List and then returns the head 
	// of the Link List 
	public Node_426 bTreeToCList(Node_426 root) 
	{ 
		if (root == null) 
			return null; 

		// Recursively convert left and right subtrees 
		Node_426 left = bTreeToCList(root.left); 
		Node_426 right = bTreeToCList(root.right); 

		// Make a circular linked list of single node 
		// (or root). To do so, make the right and 
		// left pointers of this node point to itself 
		root.left = root.right = root; 

		// Step 1 (concatenate the left list with the list 
		//		 with single node, i.e., current node) 
		// Step 2 (concatenate the returned list with the 
		//		 right List) 
		return concatenate(concatenate(left, root), right); 
	} 

	// Display Circular Link List 
	public void display(Node_426 head) 
	{ 
		System.out.println("Circular Linked List is :"); 
		Node_426 itr = head; 
		do
		{ 
			System.out.print(itr.val+ " " ); 
			itr = itr.right; 
		} 
		while (itr != head); 
		System.out.println(); 
	} 
} 

// Driver Code 
class Main 
{ 
	public static void main(String args[]) 
	{ 
		// Build the tree 
		Tree_426 tree = new Tree_426(); 
		tree.root = new Node_426(10); 
		tree.root.left = new Node_426(12); 
		tree.root.right = new Node_426(15); 
		tree.root.left.left = new Node_426(25); 
		tree.root.left.right = new Node_426(30); 
		tree.root.right.left = new Node_426(36); 

		// head refers to the head of the Link List 
		Node_426 head = tree.bTreeToCList(tree.root); 

		// Display the Circular LinkedList 
		tree.display(head); 
	} 
} 

