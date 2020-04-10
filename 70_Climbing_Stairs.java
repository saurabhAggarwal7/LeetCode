/*

You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.

Example 1:

Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step

*/

class climbing_staris_steps{

    //SOLUTION-1 BEST FASTEST:
    static int best_sount_stairs(int n){
        int[] t = new int[n+1];
        return helper(n, t);
    }

    static int helper(int n, int[] t){
        //number of steps:1
        if(n == 1)
            //store t[1] = 1
            return t[n] = 1;
        if(n ==2)
            // store t[2] = 2
            return t[n] = 2;
        if(t[n] !=0)
            return t[n];
        
        //n= 3:
        //t[3] = helper(2, t[0, 0, 0, 0]) + helper(1, t[0, 0, 0, 0])    
        return t[n] = helper(n-1, t) + helper(n-2, t);
    }

    //SOLUTION-2:
    //2: O(2^n)
    static int climbStairs(int n) {
        return climb_Stairs(0, n);
    }
    static int climb_Stairs(int i, int n) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        return climb_Stairs(i + 1, n) + climb_Stairs(i + 2, n);
    }

    //SOLUTION-3:
    //MEMOIZATION: O(n):

    //This soulution is almost similar to Solution-1 except that we are storing here in memo[i] while in solution-1 we were not stiring and simply running the stuff recursively
    static int climbStairs_memo(int n){
        int[] memo = new int[n+1];
        return climbStairs_memo(0, n, memo);
    }

    static int climbStairs_memo(int i, int n, int memo[]){
        if(i > n)
            return 0;
        if( i==n)
            return 1;
        if(memo[i] > 0)
            return memo[i];
        
        memo[i] = climbStairs_memo(i+1, n, memo) + climbStairs_memo(i+2, n, memo);
        return memo[i];
    }


    //SOLUTION-4:
    //Dynamic Programming O(n):
    static int climb_stairs_dp(int n){
        if(n ==1)
            return 1;

        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;

        //Sum of previous two elements
        for(int i=3; i<=n;i++)
            dp[i] = dp[i-1] + dp[i-2];

        return dp[n];

    }

    public static void main(String args[]){
        System.out.println(best_sount_stairs(3));
    }

}



