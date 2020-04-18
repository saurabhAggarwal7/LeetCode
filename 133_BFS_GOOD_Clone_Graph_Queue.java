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

import java.util.*;

class GraphNode_BFS {
  int val;
  Vector<GraphNode_BFS> neighbours;

  public GraphNode_BFS(int val) {
    this.val = val;
    neighbours = new Vector<GraphNode_BFS>();
  }
}

class clone_graph_bfs {
  public GraphNode_BFS cloneGraph(GraphNode_BFS source) {

    Queue<GraphNode_BFS> q = new LinkedList<GraphNode_BFS>();
    q.add(source);

     // this is the map which will be result map
    // from this only we get the cloned node for reference
    HashMap<GraphNode_BFS, GraphNode_BFS> hm = new HashMap<GraphNode_BFS, GraphNode_BFS>();
    hm.put(source, new GraphNode_BFS(source.val));

    while (!q.isEmpty()) {
      // front node from queue using poll
      // visit this node's neighbours
      GraphNode_BFS u = q.poll();

      // Get the Corresponding cloned Graph node from map:

      //cloneNodeU---> Node Itself
      //cloneNodeG---> Node's Neighbours
      GraphNode_BFS cloneNodeU = hm.get(u);

      // Visited Node's Neighbours visit them ::
      if (u.neighbours != null) {
        Vector<GraphNode_BFS> v = u.neighbours;

        for (GraphNode_BFS graphNode : v) {
                // Get the corresponding cloned node
                // If the node is not cloned then we will
                // simply get a null
                GraphNode_BFS cloneNodeG = hm.get(graphNode);
                if (cloneNodeG == null) {
                  q.add(graphNode);
                  cloneNodeG = new GraphNode_BFS(graphNode.val);
                  hm.put(graphNode, cloneNodeG);
                }
                // add the 'cloneNodeG' to neighbour
                // vector of the cloneNodeG
                cloneNodeU.neighbours.add(cloneNodeG);
        }
      } // if-ends

    } // while loop ends

    return hm.get(source);
  }

  public void bfs(GraphNode_BFS source) {
    // print the graph in BFS Style:

    //QUEUE:
    Queue<GraphNode_BFS> q = new LinkedList<GraphNode_BFS>();
    q.add(source);

    //{Node},{TRUE}
    HashMap<GraphNode_BFS, Boolean> visit = new HashMap<GraphNode_BFS, Boolean>();

    //Visisted Node Marked: TRUE Initial Node source
    visit.put(source, true);

    while(!q.isEmpty()){
      GraphNode_BFS u = q.poll();
      System.out.println("Value of node is " + u.val);
      System.out.println("Address of the node is" + u);
      //System.out.println("Neighbour 1 of the node is" + u.neighbours.get(0).val);
      //System.out.println("Neighbour 1 of the node is" + u.neighbours.get(1).val);

      if(u.neighbours != null){
        Vector<GraphNode_BFS> v = u.neighbours;

        //Get all neighbours and visit them all:
        for(GraphNode_BFS g: v){
            if(visit.get(g) == null){

              //if queue doesnt hav ethis ndoe lready then add this and mark its as visisted as well
              q.add(g);
              visit.put(g, true);
            }
        }
      }

    } //while loop ends
    System.out.println();
  }

  public GraphNode_BFS buildGraph() {
    /*
     * Note : All the edges are Undirected Given Graph: 1--2 | | 4--3
     */
    GraphNode_BFS node1 = new GraphNode_BFS(1);
    GraphNode_BFS node2 = new GraphNode_BFS(2);
    GraphNode_BFS node3 = new GraphNode_BFS(3);
    GraphNode_BFS node4 = new GraphNode_BFS(4);
    Vector<GraphNode_BFS> v = new Vector<GraphNode_BFS>();
    v.add(node2);
    v.add(node4);
    node1.neighbours = v;
    v = new Vector<GraphNode_BFS>();
    v.add(node1);
    v.add(node3);
    node2.neighbours = v;
    v = new Vector<GraphNode_BFS>();
    v.add(node2);
    v.add(node4);
    node3.neighbours = v;
    v = new Vector<GraphNode_BFS>();
    v.add(node3);
    v.add(node1);
    node4.neighbours = v;
    return node1;
  }

  public static void main(String args[]){
    clone_graph_bfs graph = new clone_graph_bfs(); 
		GraphNode_BFS source = graph.buildGraph(); 
		System.out.println("BFS traversal of a graph before cloning"); 
		graph.bfs(source); 
		GraphNode_BFS newSource = graph.cloneGraph(source); 
		System.out.println("BFS traversal of a graph after cloning"); 
		graph.bfs(newSource); 
  }

}
