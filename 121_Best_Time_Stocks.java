/**
 
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:

Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
Example 2:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */

 //KADANE's Algorithm:
 //All the straight forward solution should work, but if the interviewer twists the question slightly by giving the difference array of prices, Ex: for {1, 7, 4, 11}, if he gives {0, 6, -3, 7}, you might end up being confused.

 /*
 KADANE's ALGORITHM:
 https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
 Largest Sum Contiguous Subarray

 Initialize:
    max_so_far = 0
    max_ending_here = 0

Loop for each element of the array
  (a) max_ending_here = max_ending_here + a[i]
  (b) if(max_ending_here < 0)
            max_ending_here = 0
  (c) if(max_so_far < max_ending_here)
            max_so_far = max_ending_here
return max_so_far
 
 */

 class max_profit{

    static int max_profit(int[] prices){
        int max_curr =0;
        int max_so_far =0;

        for(int i=1;i<prices.length;i++){
            max_curr = Math.max(0, max_curr+= prices[i] - prices[i-1]);
            max_so_far = Math.max(max_curr, max_so_far);
        }
        return max_so_far;
    }

    public static void main(String args[]){
        int[] prices = {0, 6, -3, 7};
        System.out.println(max_profit(prices));
    }
 }

