import java.util.Arrays;

/*Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:

Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.
Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9. */

//TWO Solutions: 1) Backtracking and Branch Pruning 


class Perfectsquares_min{

//SOLUTION-1:
static class PerfectSquares_Backtracking{
    private int result = Integer.MAX_VALUE;
    private int maxSq;

    public PerfectSquares_Backtracking(){
        //
    }

    public int numSquares(int n){
        maxSq = (int) Math.sqrt(n);
        totalNumbers(n, 0);
        return result;
    }

    private void totalNumbers(int n, int total){
        //stop if current solution would ends in a larger result:

        //iteration-1: total =0 and result is some MAX Number, maxSq= 3
        
        if(total >= result -1){
            return;
        }

        //deduct the square number from 'n' until it reaches 0:
        //it-1: maxSq: 3, n=13
        for(int i=maxSq; i> 0;i--){
            int curr = n-i*i;
            if (curr < 0)   
                continue;
            if(curr == 0) 
                //total is the number of numbers required to make up for the solution:
                result = total +1;
            else
                totalNumbers(curr, total+1);
        }
    }
}

//SOLUTION-2: Dynamic Programming
static class PerfectSquare_DP{


    public int findLeastSum(int n, int[] dp){

        if( n==0) 
            return 0;
        if(dp[n] != Integer.MAX_VALUE) 
            return dp[n];
        
        //find square root of the number
        int sq = (int)Math.sqrt(n);

        for(int i=sq; i>=1; i--){
            int curr = n-i*i; // 13- 3*3 = 4
            
            // originally: dp[0] = 25621111222333 and so on till dp[13]
            //Now in loop, recursion with curr = 4 this will again loop and we try to find the min

            //when all the recusrion will end then the Min will run: 
            dp[n] = Math.min(dp[n], findLeastSum(curr, dp)+ 1);
        }
        return dp[n];
    }

    public int numSquares(int n){
        if(n==0) return 0;
        int[] dp = new int[n+1];

        //fill the arrays with rmax values
        Arrays.fill(dp, Integer.MAX_VALUE);

        return findLeastSum(n, dp);
    }


}

public static void main(String args[]){
    PerfectSquares_Backtracking bt = new PerfectSquares_Backtracking();
    System.out.println(bt.numSquares(13));

    //SOLUTION-2:
    PerfectSquare_DP dp = new PerfectSquare_DP();
    System.out.println(dp.numSquares(13));
}

}