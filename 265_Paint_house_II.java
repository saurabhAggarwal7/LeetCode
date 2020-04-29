/**
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

 */

 //in this version of problem no side by side same colors are allowed
 //http://www.learn4master.com/interview-questions/leetcode/leetcode-paint-house-i-solution-java
 //http://www.learn4master.com/interview-questions/leetcode/leetcode-paint-house-ii-solution-java

 //SOLUTION:
 /**
  * This is a follow up question of Paint House I. The basic idea to solve Paint House I is to define DP[i][j] as the minimum cost to paint house until i with color j. where i  ∈ [1, n]  and j ∈ {0, 1, 2}.

In the beginning, DP[0][j] = cost[0][j].  Then we can use the following rules to update the table in bottom up manner. 

DP[i][0] += Math.min(DP[i-1][1], DP[i-1][2]);

DP[i][1] += Math.min(DP[i-1][0], DP[i-1][2]);

DP[i][2] += Math.min(DP[i-1][0], DP[i-1][1]);

For this problem, we can also define DP[i][j] as the minimum cost to paint house until i with color j. 

Then DP[i][j] = cost[i][j] + min({DP[i - 1][m] | m = 0, 1, 2, …., k and m != j }). 



We can use an variable """preMin""""" to record the minimum cost of DP[i – 1] [m], and the corresponding color minColor. 

One problem is that the minColor (color k with minimum value) can equal to the current color.  In this case we need to use the color with second smallest cost. 

So define another variable """"""secondMin""""""" to record the second minimum cost of DP[i – 1][m] along with the corresponding color secondMinColor. 
  */

 class paint_house_II{

    static int minCostII(int[][] costs) {
        if (costs != null && costs.length == 0) 
            return 0;
        
        //"""preMin""""" to record the minimum cost of DP[i – 1] [m], and the corresponding color minColor. 
        int preMin = 0;

        /**
         * One problem is that the minColor (color k with minimum value) can equal to the current color.  In this case we need to use the color with second smallest cost. 
            So define another variable """"""secondMin""""""" to record the second minimum cost of DP[i – 1][m] along with the corresponding color secondMinColor. 
         */
        int preSecndMin = 0;
        int preColor = -1;
        
        //HOUSE LOOP::
        for (int i = 0; i < costs.length; i++) {
            int curMin = Integer.MAX_VALUE;
            int curSecndMin = Integer.MAX_VALUE;
            int curColor = -1;

            //COLORS LOOP::
            for (int j = 0; j < costs[0].length; j++) {
                costs[i][j] = costs[i][j] + (preColor == j ? preSecndMin : preMin);
 
                if (costs[i][j] < curMin) {
                    curSecndMin = curMin;
                    curMin = costs[i][j];
                    curColor = j;
                } else if (costs[i][j] < curSecndMin) {
                    curSecndMin = costs[i][j];
                }
            }
            preMin = curMin;
            preSecndMin = curSecndMin;
            preColor = curColor;
        }
        return preMin;
    }
    public static void main(String args[]){
        int[][] cost = {
            {17, 2, 17},
            {16, 16, 5}, 
            {14, 3, 19}
        };

        //COST of paininting house 0 with color 1 is 17
        //COST OF PAININTING THE HOUSE 1 WITH 3 DIFFERENT COLORS IS FOLLLOWING:
        //10- 17
        //11-2------> min cost
        //12-17

        //COST OF PAINTING HOUSE-2 WITH THREE DIFFERENT COLOURS IS FOLLOWING:
        //20-16
        //21-16
        //22-5------->> min cost

        //COST OF PAINTING HOUSE-3 WITH THREE DIFFERENTR COLOURS IS FOLLOWING:
        //30-14
        //31-3------->> min cost
        //32-19

        //min cost for painting all 3 houses with min cost and making sure they are not same color with adjacents is:
        //2+5+3 = 10
        //colors used --> <1, 2, 1> they are not repeated side ways so we are good
        System.out.println(minCostII(cost));

    }
 }