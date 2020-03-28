/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
*/

/*
Given a cost matrix cost[][] and a position (m, n) in cost[][], write a function that returns cost of minimum cost path to reach (m, n) from (0, 0). Each cell of the matrix represents a cost to traverse through that cell. Total cost of a path to reach (m, n) is sum of all the costs on that path (including both source and destination). You can only traverse down, right and diagonally lower cells from a given cell, i.e., from a given cell (i, j), cells (i+1, j), (i, j+1) and (i+1, j+1) can be traversed. You may assume that all costs are positive integers.

For example, in the following figure, what is the minimum cost path to (2, 2)?

The path with minimum cost is highlighted in the following figure. The path is (0, 0) –> (0, 1) –> (1, 2) –> (2, 2). The cost of the path is 8 (1 + 2 + 2 + 3).

Optimal Substructure:
The path to reach (m, n) must be through one of the 3 cells: (m-1, n-1) or (m-1, n) or (m, n-1). So minimum cost to reach (m, n) can be written as “minimum of the 3 cells plus cost[m][n]”.

minCost(m, n) = min (minCost(m-1, n-1), minCost(m-1, n), minCost(m, n-1)) + cost[m][n]
*/

//(m, n) is the destination
//(0,0) is the origin:
class MinPathSum{

     static int min(int x, int y, int z) 
	{ 
		if (x < y) 
			return (x < z)? x : z; 
		else
			return (y < z)? y : z; 
    } 
    
    static int minCost(int cost[][], int m, int n){
        //create our 2x2 matrix for from coordinates:

        //cost[0] = {00: 1, 01: 2, 02: 3}
        //cost[1] = {10: 4, 11: 8, 12: 2}
        //cost[2] = {20: 1, 21: 5, 22: 3}

        int tc[][] = new int[m+1][n+1];

        //tc[0] = {00: 1, 01: 0, 02: 0}
        //tc[1] = {10: 0, 11: 0, 12: 0}
        //tc[2] = {20: 0, 21: 0, 22: 0}

        //tc[0] is 1 which means cost to reach from (0,0) is 1which is the value itself.

        //set the origin of this matrix from our original matrix:
        tc[0][0] = cost[0][0];

        //set First Row & First Column for the tc[][] array:
        //t[][] is already filled so we start with i=1:

        //Fill Row cost matrix, Go Row wise That is Go down:
        for(int i=1; i<=m; i++){
            tc[i][0] = tc[i-1][0] + cost[i][0];

                /*
                tc[1][0] =  tc[0][0] + cost[1][0]
                tc[2][0] = tc[1][0] + cost[2][0]

                tc[1][0] = 1+ 4 = 5;
                tc[2][0] = t[1][0] + + 1 = 5+1 = 6
                */
        }

        //Fill Column cost matrix, Go Column Wise so Go right:
        for(int j= 1; j<=n; j++){
            tc[0][j] = tc[0][j-1] + cost[0][j];

            //tc[0][1] = t[0][0] + cost[0][1]
            //tc[0][2] = t[0][1] + cost[0][2]

            //tc[0][1] = 1+2 = 3
            //tc[0][2] =  3 + 3 = 6
        }

        //After this Process:
        /*
        tc [{
            00: 1, 01: 3, 02: 6
            10: 5, 11: X, 12: X
            20, 6, 21: X, 22: X
        }]
        */

        // Now run the loop for 11, 12, 21, 22: i-e the smaller matrix because we know the first one is already complate:
        //Now that we have more elements for which the path can be possible so we need to consider all the possible cases from i-1, j-1, and both and take min out of it

        //construct the rest of the array:
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                tc[i][j] = min(tc[i-1][j], tc[i-1][j-1], tc[i][j-1]) + cost[i][j];
            }
        }
        return tc[m][n];
    }

    public static void main(String args[]){
        int cost[][] = {
            { 1, 2, 3 },
            {4, 8, 2},
            {1, 5, 3}
        };

        //choose the destination coordinates:
        int m = 2;
        int n= 2;
        System.out.println(minCost(cost, m, n));
    }


}


