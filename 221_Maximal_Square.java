/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:

Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4

HOW ??
Input: 

1 0 1 0 0
1 0 1(X) 1(X) 1
1 1 1(X) 1(X) 1
1 0 0 1 0

Output: 4
 */

 /**SOLUTION:::
  * 
  
1. dp[i][j] represents the length of the square which lower right corner is located at (i, j).

2. If the value of this cell is also 1, then the length of the square is the minimum of: the one {above, its left, and diagonal up-left value +1}. Because if one side is short or missing, it will not form a square.
b[i][j] represent the edge length of the largest square ENDING at position (i, j), it would be much clearer.

3.
EXAMPLE:
Top, Left, and Top Left decides the size of the square. If all of them are same, then the size of square increases by 1. If they're not same, they can increase by 1 to the minimal square. 
max[i][j] denotes the max length of a square of 1's whose bottom-right is at i-1,j-1 
  */

  /**INPUT:
   * 
   * 0 1 2 3 4 
   0 1 0 1 0 0
   1 1 1 1 1 1
   2 1 1 1 1 1
   3 1 0 0 1 0
   */

   /* AUXILIARY ARRAY:

   *  0 1 2 3 4 5
   0  0 0 0 0 0 0
   1  0 1 0 1 0 0 
   2  0 1 0 1 1 1
   3  0 1 1 1 2 2
   4  0 1 0 0 1 0

   */

   class maximal_square{

    static int maximal_square_cal(char[][] matrix){
        if(matrix.length ==0)
            return 0;
        
        //width:
        int m = matrix.length;
        //height:
        int n = matrix[0].length;

        int[][] dp = new int[m+1][n+1];

        int maxEdge=0;
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){

                //CREATE AN AUXILARY ARRAY::
                //i=1, j=1
                if(matrix[i-1][j-1] == 1){ //matrix[0][0] == 1 ? YES
                    dp[i][j] = Math.min( //dp[1][1] = MIN(MIN(dp[0][1] vs dp[1][0]) Vs dp[0][0]) + 1

                        Math.min(
                            dp[i - 1][j], //TOP dp[0][1]
                            dp[i][j - 1]), //LEFT dp[1][0]

                            dp[i - 1][j - 1]) + 1; //DIAGNOL ELEMENT:: TOP LEFT dp[0][0]
                            maxEdge = Math.max(maxEdge, dp[i][j]);
                }
            }
        }

        return maxEdge*maxEdge; //2*2 = 4
    }

       public static void main(String args[]){
        char[][] square = {{1, 0, 1, 0, 0},
                            {1, 0, 1, 1, 1},
                            {1, 1, 1, 1, 1},
                            {1, 0, 0, 1, 0}};
        System.out.println(maximal_square_cal(square));
       }
   }
    