//Clone GRAPH: BFS
 
 //SOLUTION: https://www.geeksforgeeks.org/clone-an-undirected-graph/

 /**
  * 
  The idea is to do a BFS traversal of the graph and while visiting a node make a clone node of it (a copy of original node). If a node is encountered which is already visited then it already has a clone node.
  How to keep track of the visited/cloned nodes?
A HashMap/Map is required in order to maintain all the nodes which have already been created.
Key stores: Reference/Address of original Node
Value stores: Reference/Address of cloned Node

A copy of all the graph nodes has been made, how to connect clone nodes?
While visiting the neighboring vertices of a node u get the corresponding cloned node for u , let’s call that cloneNodeU , now visit all the neighboring nodes for u and for each neighbor find the corresponding clone node(if not found create one) and then push into the neighboring vector of cloneNodeU node.

How to verify if the cloned graph is a correct?
Do a BFS traversal before and after the cloning of graph. In BFS traversal display the value of a node along with its address/reference.
Compare the order in which nodes are displayed, if the values are same but the address/reference is different for both the traversals than the cloned graph is correct.
  */


/**Theory:
 * 
 * ArrayList and Vectors both implement the List interface and both use (dynamically resizable) arrays for its internal data structure, much like using an ordinary array.
 * Major Differences between ArrayList and Vector:

Synchronization : Vector is synchronized, which means only one thread at a time can access the code, while arrayList is not synchronized, which means multiple threads can work on arrayList at the same time.
 * Performance: ArrayList is faster, since it is non-synchronized, while vector operations give slower performance since they are synchronized (thread-safe)
 * Data Growth: ArrayList and Vector both grow and shrink dynamically to maintain optimal use of storage – but the way they resize is different
 * Traversal: Vector can use both Enumeration and Iterator for traversing over elements of vector while ArrayList can only use Iterator for traversing.
 * 
 */
