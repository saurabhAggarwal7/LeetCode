/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), check if these edges form a valid tree.
 * 
 * This problem can be converted to finding the cycle from a graph. It can be solved by using DFS (Recursion) or BFS (Queue).
 */

import java.util.*;

//DFS::
class DFS_Graph_Valid_Tree {
    public boolean validTree(int n, int[][] edges) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        for(int i=0; i<n; i++){
            ArrayList<Integer> list = new ArrayList<Integer>();
            map.put(i, list);
        }
     
        for(int[] edge: edges){
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
     
        boolean[] visited = new boolean[n];
     
        if(!helper(0, -1, map, visited))
            return false;
     
        for(boolean b: visited){
            if(!b)
                return false;
        }
     
        return true;
    }
     
    public boolean helper(int curr, int parent, 
                          HashMap<Integer, ArrayList<Integer>> map, boolean[] visited){
        if(visited[curr])
            return false;
     
        visited[curr] = true;
     
        for(int i: map.get(curr)){
            if(i!=parent && !helper(i, curr, map, visited)){
                return false;
            }
        }   
     
        return true;
    }
}

//BFS::
class bfs_Graph_Valid_tree{

    boolean BFS_validTree(int n, int[][] edges) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(int i=0; i<n; i++){
            list.add(new ArrayList<>());
        }
 
        //build the graph
        for(int[] edge: edges){
            int a = edge[0];
            int b = edge[1];
 
            list.get(a).add(b);
            list.get(b).add(a);
        }
 
        //use queue to traverse the graph
        HashSet<Integer> visited = new HashSet<>();
        LinkedList<Integer> q = new LinkedList<>();
        q.offer(0);
 
        while(!q.isEmpty()){
            int head = q.poll();
 
            if(visited.contains(head)){
                return false;
            }
 
            visited.add(head);
 
            ArrayList<Integer> vList = list.get(head);
            for(int v: vList){
                if(!visited.contains(v)){
                    q.offer(v); 
                }     
            }
        }
 
        if(visited.size()<n){
            return false;
        }
 
        return true;
    }
}