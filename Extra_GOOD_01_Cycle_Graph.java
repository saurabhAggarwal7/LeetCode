/**
 * Print all the cycles in an undirected graph
Given an undirected graph, print all the vertices that form cycles in it.


Approach: Using the graph coloring method, mark all the vertex of the different cycles with unique numbers. Once the graph traversal is completed, push all the similar marked numbers to an adjacency list and print the adjacency list accordingly. Given below is the algorithm:

Insert the edges into an adjacency list.
Call the DFS function which uses the coloring method to mark the vertex.
Whenever there is a partially visited vertex, backtrack till the current vertex is reached and mark all of them with cycle numbers. Once all the vertexes are marked, increase the cycle number.
Once Dfs is completed, iterate for the edges and push the same marked number edges to another adjacency list.
Iterate in the another adjacency list and print the vertex cycle-number wise.

 */

//1, 2, 3 are schema for not visited, partially and completely visited nodes

        /**
         * 1->2
         * 2-> 3
         * 3-> 4, 5
         * 4->6,7
         * 5-> 6, 9
         * 6-> 10
         * 7-> 8
         * 10-> 11
         * 12-> 13
         * 11-> 12, 13
         * 
         * Cycle Number 1: 3 4 5 6 
        *  Cycle Number 2: 11 12 13 
         * 
         */

import java.util.*;

class graph_cycle {
    static final int N = 15;
    // create a graph with capacity of so many nodes

    //graph vector [1] -> connected to 100, 200, 200 etc. etc.
    static Vector<Integer>[] graph = new Vector[N];


    // vector to store graph cycles: like [1] -> 100, 200, 300 etc. etc.
    static Vector<Integer>[] cycles = new Vector[N];

    //Length of total number of cycles = 2
    static int cyclenumber;


    //FUNCTION-1:::::::::
    // Function to mark the nodes with 
    // different colors for different cycles 
    //PARMS:

    /**
     * 
     * @param u 1 {starting node from the graph}
     * (POST RECURSION it's actually neighbour u)
     * 
     * @param p PARENT
     * (POST Recursion it's actually u) beacuse we traverse the neighbours 
     * 
     * @param color [0, 0, ........0] 15 indexes length defined by N
     * 
     * RESULT
     * This will keep track of {"hey node-1 is 1/2/3 from color schema i-e complete.partial or not"}
     * [1] -> 2
     * [2] -> 2
     * ...
     * [13] -> 2
     * 
     * array from [1] to [13] all have value 2 means every node is completely visited from 1-13
     * 
     * @param mark  [0, 0, ........0] 15 indexes length defined by N
     * 
     * Result:
     * 0-> 0, 1->0, 2-> 0, 7->0, 8->0, 9->0, 13->0
     * 3->1, 4-> 1, 5->1, 6->1
     * 11->2, 12->2, 13->2
     * 
     * @param par   [0, 0, ........0] 15 indexes length defined by N
     * PARENT ARRAY
     * 
     * par[u] => par[1] = 0 => {}
     * 
     * RESULT:
     * 
     */
    static void dfs_cycle(int u, int p, int[] color, int[] mark, int[] par){

        //when dfs recusrion called to this: before u now-> p and v becomes u 
        //this is done to check for bidirectional 

        //hence when the recursion calls the previous connection is retained and here used and stores in par[] array
        /**
         * EXAMPLE----
         * initially parent of 1 is 0
         * neighbour of 1 is 2
         * so 2 will now be u and it's p from back call is 1
         * this info is stored in array
         * par[2] = 1
         * This info will help us to find te bidirectional connectio across
         * 
         */
        
        //1. COMPLETELY VISITED-2

        //itr-1: u=1, p=0, color[], mark[], par[]
        //itr-2: (from recursion): u =2, p=1, color[1] =1, mark =[], par[]



        /** GRPAH VECTOR FULLY CONECTED BIDIRECTIONAL:::
         * graph[u] or graph[..] will be as following::
         * 1->2
         * 2-> 1, 3
         * 3-> 2, 4, 5
         * 4-> 3, 6,7
         * 5-> 6, 3, 9
         * 6-> 4, 5, 10
         * 7-> 4, 8
         * 8-> 7
         * 9-> 5
         * 10-> 6, 11
         * 11-> 10, 12, 13
         * 12-> 11, 13
         * 13-> 11, 12
         * 
         * Cycle Number 1: 3 4 5 6 
        *  Cycle Number 2: 11 12 13 
         * 
         */

        //USE THIS FOR PAR[] list 
        /**
         * 1->2
         * 2-> 3
         * 3-> 4, 5
         * 4->6,7
         * 5-> 6, 9
         * 6-> 10
         * 7-> 8
         * 10-> 11
         * 12-> 13
         * 11-> 12, 13
         * 
         * Cycle Number 1: 3 4 5 6 
        *  Cycle Number 2: 11 12 13 
         * 
         */

         /** PARENT ARRAY p[]
          * PARENT OF INDEX is VALUE:::::
          * [0] -0
          [1] -0
          [2] -1
          [3]-2
          [4]-3
          [5]-6
          [6]-4 {Parent of 6 is 4}
          [7]-4 {Parent of 7 is also 4}
          [8]-7
          [9]-5
          [10]-6
          [11]-10
          [12]-11
          [13]-12
          * 
          */

        //recursion will end when that current node u is completely visited
        //color[1] == 2 ? no because initailly its initialzed with 0s
        //color[2] == 2 ? no 
        if(color[u] == 2)
            return;
        
        
		// 2. seen vertex, it's again visited so CYCLE DETECTED
        // backtrack based on parents to find the complete cycle. 
        
        if(color[u] == 1){

            //As per GRAPH: CYCLE [3, 4, 5, 6]
            //3-> 4, 5
            //4-> 6, 7
            //5-> 6, 9
            //6-> 10
            //itr-7: u=3, p=5
            cyclenumber++;
            int cur = p;

            //itr-7: mark[5] = 1 : Node 5 belongs to CYCLE-1
            mark[cur] = cyclenumber;

            //backtrack the vertex which are in current cycle thats found !!

            //CYCLE LOGIC START:::
            while (cur != u) 
			{ 
                cur = par[cur]; 
                //CYCLE LOGIC ENDS :::

				mark[cur] = cyclenumber; 
			} 
			return; 
        } //color[u]==1 ends

        //it-1: par[1] = 0
        //itr-2: par[2] = 1
        par[u] = p; 

        // partially visited. 
        //itr:1 color[1] = 1 now
        //itr-2: color[2] = 1 now
		color[u] = 1; 

        // simple dfs on graph 
        
        //EXPLORE NEIGHBOURS
		for (int v : graph[u]) 
		{ 
            //itr-1: graph[1] [1] -> 2(index 0)

			// if it has not been visited previously 
			if (v == par[u]) 
			{ 
				continue; 
            } 
            
            //itr-1: v =2 , u =1, color[1] =1, mark[] par[]
			dfs_cycle(v, u, color, mark, par); 
		} 

		// completely visited. 
		color[u] = 2; 
    }

    //FUNCTION-2:::::::::
    //add edge to the graph (NODE U ---- NODE V)
    static void addEdge(int u, int v){

        //create a connection between vertex (nodes) u and v:
        //addEdge(1, 2); 

        /**
         * GRAPH:::
         * 
         * graph Vector<>
         * [1] -> 2(index 0)
         * [2] -> 1(index 0), 3(index 1)
         * [3] -> 2(index 0), 4(index 1), 5(index 2)
         * [4] -> 3(index 0), 6(index 1), 7(index 2)
         * ....
         * ....
         * ....
         * 
         */

                 /** GRPAH VECTOR FULLY CONECTED BIDIRECTIONAL:::
         * 1->2
         * 2-> 1, 3
         * 3-> 2, 4, 5
         * 4-> 3, 6,7
         * 5-> 6, 3, 9
         * 6-> 4, 5, 10
         * 7-> 4, 8
         * 8-> 7
         * 9-> 5
         * 10-> 6, 11
         * 11-> 10, 12, 13
         * 12-> 11, 13
         * 13-> 11, 12
         * 
         * Cycle Number 1: 3 4 5 6 
        *  Cycle Number 2: 11 12 13 
         * 
         */


        graph[u].add(v);
        graph[v].add(u);
    }

    //FUNCTION-3::::::::::
    static void printCycles(int edges, int mark[]){

        //push all the edges in the cycle list, and mark them as per mark array:
        for(int i=1; i<= edges;i++){
            if(mark[i] != 0)
                //value of mark array is the index for cycle array
                cycles[mark[i]].add(i);
        }

        /***
         * 
         * MARK[]:
         * 0-> 0, 1->0, 2-> 0, 7->0, 8->0, 9->0, 13->0
         * 
         * Cycle-1: Indexes mark with 1 are nodes that form a cycle-1
         * 3->1, 4-> 1, 5->1, 6->1
         * 
         * Cycle-2 Indexes mark with 2 are nodes that form a cycle-2
         * 11->2, 12->2, 13->2
         * 
         */

         /**
          * 
          CYCLE Vector[] 
          [1] -> 3, 4, 5
          [2] -> 11, 12, 13
          */

          /**
           * cycle number is total number of cycles = 2
           */

        //print all the vertex (nodes) that are with same cycle:
        // print all the vertex with same cycle 

        //Print cycle and it's elements
		for (int i = 1; i <= cyclenumber; i++) 
		{ 
			// Print the i-th cycle 
            System.out.printf("Cycle Number %d: ", i); 
            
			for (int x : cycles[i]) 
				System.out.printf("%d ", x); 
			System.out.println(); 
		} 
    }

    //DRIVER CODE:::::::::::
    public static void main(String args[]){

        //blank init:
        for (int i = 0; i < N; i++) 
		{ 
			graph[i] = new Vector<>(); 
			cycles[i] = new Vector<>(); 
        } 

        // add edges 
		addEdge(1, 2); 
		addEdge(2, 3); 
		addEdge(3, 4); 
		addEdge(4, 6); 
		addEdge(4, 7); 
		addEdge(5, 6); 
		addEdge(3, 5); 
		addEdge(7, 8); 
		addEdge(6, 10); 
		addEdge(5, 9); 
		addEdge(10, 11); 
		addEdge(11, 12); 
		addEdge(11, 13); 
        addEdge(12, 13); 

        /**
         * 1->2
         * 2-> 3
         * 3-> 4, 5
         * 4->6,7
         * 5-> 6, 9
         * 6-> 10
         * 7-> 8
         * 10-> 11
         * 12-> 13
         * 11-> 12, 13
         * 
         * Cycle Number 1: 3 4 5 6 
        *  Cycle Number 2: 11 12 13 
         * 
         */

        /** GRPAH VECTOR FULLY CONECTED BIDIRECTIONAL:::
         * 1->2
         * 2-> 1, 3
         * 3-> 2, 4, 5
         * 4-> 3, 6,7
         * 5-> 6, 3, 9
         * 6-> 4, 5, 10
         * 7-> 4, 8
         * 8-> 7
         * 9-> 5
         * 10-> 6, 11
         * 11-> 10, 12, 13
         * 12-> 11, 13
         * 13-> 11, 12
         * 
         * Cycle Number 1: 3 4 5 6 
        *  Cycle Number 2: 11 12 13 
         * 
         */


        //array to color the graph:
        int[] color = new int[N];

        //store the parent of node 
        int[] par = new int[N];

        // mark with unique numbers 
        int[] mark = new int[N]; 
        
        // store the numbers of cycle 
		cyclenumber = 0; 
        int edges = 13; //means 13 connections between two nodes

		// call DFS to mark the cycles 
		dfs_cycle(1, 0, color, mark, par);
        
		// function to print the cycles 
		printCycles(edges, mark); 
        
    }



}