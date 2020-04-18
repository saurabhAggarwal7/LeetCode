
/**
 * 
 * Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a val (int) and a list (List[GraphNode]) of its neighbors.

class GraphNode {
    public int val;
    public List<GraphNode> neighbors;
}
 

Test case format:

For simplicity sake, each node's value is the same as the node's index (1-indexed). For example, the first node with val = 1, the second node with val = 2, and so on. The graph is represented in the test case using an adjacency list.

Adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.

EXAMPLE-1

Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]
Explanation: There are 4 nodes in the graph.
1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).

EXAMPLE-2
Input: adjList = [[]]
Output: [[]]
Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
Example 3:

EXAMPLE-3
Input: adjList = []
Output: []
Explanation: This an empty graph, it does not have any nodes.

EXAMPLE-4
Input: adjList = [[2],[1]]
Output: [[2],[1]]
 

Constraints:

1 <= GraphNode.val <= 100
GraphNode.val is unique for each node.
Number of Nodes will not exceed 100.
There is no repeated edges and no self-loops in the graph.
The Graph is connected and all nodes can be visited starting from the given node.
 */

import java.util.*;

class GraphNode {
    public int data;
    public List<GraphNode> neighbors;

    GraphNode(int data) {
        this.data = data;

        //**/
        //!! Most Important !!
        //Else Null pointer Exception !!
        neighbors = new ArrayList<GraphNode>();
    }
}

class clone_graph {
    public static GraphNode clone(GraphNode node) {
        if (node == null)
            return null;
        Map<GraphNode, GraphNode> map = new HashMap<>();

        // do a dfs for the given node with the map
        dfs(map, node);

        // return the map {value} for this node:
        return map.get(node);
    }

    /**
     * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]] Explanation: There are 4 nodes in
     * the graph. 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node
     * (val = 4). 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node
     * (val = 3). 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node
     * (val = 4). 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node
     * (val = 3).
     * 
     */
    static void dfs(Map<GraphNode, GraphNode> map, GraphNode curr) {

        // itr-1: curr->1
        //itr-2: [Neighbours of 1] curr-> 2 which is neighbour of 1 from recursion 
        //itr-3: [neighbours of 2] curr-> 1 again !!! because its the neighbor of 2 //but it wont proceed further becuase its already in map
        //itr-4  [Neighbour of 2 agaib becuase 1 already traveresed] curr-> 3 which is from recursion

        if (map.containsKey(curr))
            return;

        // {key current}
        // {value} GraphNode -> {data} as curr.data

        //itr-1: map: {k}-> 1, {v}-> 1
        //itr-2: map: {k}-> 2, {v}-> 2
        //itr-3: <> already has 1
        //itr-4: map: {k}-> 3, {v}-> 3
        
        //....
        //after some iterations map has all the nodes will all the neighbours
        map.put(curr, new GraphNode(curr.data));
        for (GraphNode next : curr.neighbors) {

            // traverse each neighbour in dfs format
            dfs(map, next);

            //ONCE the recursion ends now:

            //e.g. curr-> 4 {neighbors are 1 & 3}. 
            //Inside loop we are traversing neighbours) variable next is 1 and then next will be 3
            //{code}: map.get(curr).neighbors means map.get(4).neighbors ---> List<> 1& 3 node objects
            //.add(map.get(next)); means add the Graph Node object in this List in two loops
            //But as already seen it's already available so nothing new is done here
            //This is done to make sure we dont have any unexplored node.

            map.get(curr).neighbors.add(map.get(next));
        }
    }

    public static void main(String args[]) {
        /*
         * Note : All the edges are Undirected Given Graph: 1--2 | | 4--3
         */
        GraphNode node1 = new GraphNode(1);
        GraphNode node2 = new GraphNode(2);
        GraphNode node3 = new GraphNode(3);
        GraphNode node4 = new GraphNode(4);

        ArrayList<GraphNode> v = new ArrayList<GraphNode>();
        v.add(node2);
        v.add(node4);
        node1.neighbors = v;

        v = new ArrayList<GraphNode>();
        v.add(node1);
        v.add(node3);
        node2.neighbors = v;

        v = new ArrayList<GraphNode>();
        v.add(node2);
        v.add(node4);
        node3.neighbors = v;

        v = new ArrayList<GraphNode>();
        v.add(node3);
        v.add(node1);
        node4.neighbors = v;

        // node 1 is the graph itself
        GraphNode result = clone(node1);
        System.out.println(result.data);
    }
}