/**
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. 
 * 
 * You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

SOLUTION:::
The basic idea to solve Paint House I is to define DP[i][j] as the minimum cost to paint house until i with color j. where i  ∈ [1, n]  and j ∈ {0, 1, 2}.

In the beginning, DP[0][j] = cost[0][j].  Then we can use the following rules to update the table in bottom up manner. 
 */


class Paint_House {
    static int cost_compute(int[][] costs) {
        if(costs==null||costs.length==0)
            return 0;
     
        for(int i=1; i<costs.length; i++){
            
            //1 ITERTATION IS 1 HOUSE WITH ALL 3 POSSIBLE COLORS::

            //i=1
            //costs[1][0] = costs[1][0] + min(costs[0][1], costs[0][2]) 
            //cost[1][0] = 16 + 2 from min(2, 17)

            //GOAL IS TO PAINT ALL 3 HOUSES(0, 1, 2) WITH MIN COST AND NO DUPLICATE SIDE BY SIDE COLORS
            //COST OF PAINITING HOUSE 1 WITH RED WILL BE COST OF PAINTING HOUSE 1 WITH RED + MIN COST OF PAINTING HOUSE BETWEEN(COST TO PAINT PREVIOUS HOUSE PAINT WITH BLUE + COST TO PAINT PREVIOUS HOUSE WITH GREEN)
            costs[i][0] = costs[i][0] + Math.min(costs[i-1][1], costs[i-1][2]);

            //SOLUTION ITERATION: I=2
            //PREVIOUSLY COST[I-1][2] WAS 7 BECAUSE OF (5+2 FROM HOUSE0-COLOR1 AND HOUSE1-COLOR2 THAT SI THE SUB-PROBLEMS)
            //NOW WHEN I=2 SO, cost[2][2] =  cost[2][2] + min(18, 7) = 3+ 7 = 10
            costs[i][1] = costs[i][1] + Math.min(costs[i-1][0], costs[i-1][2]);

            costs[i][2] = costs[i][2] + Math.min(costs[i-1][0], costs[i-1][1]);
        }

        //AFTER THE LOOP THE NEW ARRAY WILL BE:
        /**
         * COST[][] = {
         * {17, 2, 17},
         * {18, 33, 7},
         * {21, 10, 37}--------->> HERE LIES OUR ANSWER FROM WHERE WE TRY TO GET THE MINIMUM VALUE TO PAINT ALL THE THREE HOUSES WITH DIFFERENT COLOURS
         * }
         * 
         * EVERY ROW HAS THE VALUES TO PAINT THE HOUSE WITH DIFFERENT COLORS WITH DIFFERENT COMBINATIONS
         * WE NEED TO SELECT WHICH COMBINATION WE WANT
         * 
         * ROW-2'S RED = ROW-2'S RED + MIN(ROW 1'S BLUE OR ROW 1'S GREEN)
         * ROW-2'S BLUE = ROW-2'S BLUE + MIN(ROW-1'S RED OR ROW-1'S GREEN)
         * ROW-3'S GREEN = ROW-3'S GREEN + MIN(ROW-2'S RED OR ROW 1'S BLUE)
         * 
         * ORRR
         * 
         * HOUSE-2'S RED = HOUSE-2'S RED + MIN(HOUSE 1'S BLUE OR HOUSE 1'S GREEN)
         * HOUSE-2'S BLUE = HOUSE-2'S BLUE + MIN(HOUSE-1'S RED OR HOUSE-1'S GREEN)
         * HOUSE-3'S GREEN = HOUSE-3'S GREEN + MIN(HOUSE-2'S RED OR HOUSE 1'S BLUE)
         * 
         * MAIN AIM IS TO PAINT THE HOUSE WITH EITHER OF THREE COLORS SO WHEN WE SAY ONE LINE ABOVE IT MEANS WE ARE COMMITING TO ONE OF THE COLORS AND GET TO FIND THE MINIMUM FORM THE OTHER TWO COLROS
         * 
         * 
         */
     
        int m = costs.length-1;
        return Math.min(Math.min(costs[m][0], costs[m][1]), costs[m][2]);
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
        System.out.println(cost_compute(cost));

    }
    
}