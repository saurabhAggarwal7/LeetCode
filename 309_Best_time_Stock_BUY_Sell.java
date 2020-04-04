/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

Input: [1,2,3,0,2]
Output: 3 
Explanation: transactions = [buy, sell, cooldown, buy, sell]



Solution explaination
Clearly we can either buy, sell, or cooldown (i.e do nothing with) a stock.
Conditions as per the problem:

Cooldown immediately follows a sell.
Cannot sell without buying
Cannot hold more than 1 stock at a time.
Goal: Maximise profit with the above 3 operations (Buy, Sell, Cooldown).

*/

//DUMMY EXAMPLE explaination:
/*
Input: [1,2,3,0,2]
Output: 3 
Explanation: transactions = [buy, sell, cooldown, buy, sell]
index =0 buy, index=1 se;;, profit = +1
index=2, col down nothing done when value was 3

index=3, value was 0, so buy it 
index=4 value is 2, Profit will be +2 + 1 = +3
*/

class best_time_stock_buy_sell{

    static int max_profit(int[] prices){

        if(prices.length == 0) return 0;

        //maintain 3 arrays based on each day (i)'s transaction'
        //each day either BUY/SELL/OR COOLDOWN will be done so:
        int[] profitAfterBuying = new int[prices.length];
        int[] profitAfterSelling = new int[prices.length];
        int[] profitAfterDoingNothing = new int[prices.length];

        //DAY-1 i=0
        profitAfterBuying[0] = -prices[0]; //After buying the stock at index 0, profit is neg, because we have not sold it yet.
        profitAfterDoingNothing[0] = 0;
        profitAfterSelling[0] = 0;

        //i=1 because we have already initalized the i=0 values above:
        for(int i=1;i<prices.length;i++){

            //NOTE: i-1 is Previous
            //NOTE: i is Current

            //1:
            /*profitAfterBuying[i] - To buy a stock at index i , a cooldown is presumed at or before index i-1.
            Therefore profitAfterBuying[i] = Math.max(profitAfterBuying[i-1],profitAfterDoingNothing[i-1] - prices[i]);
            Where profitAfterBuying[i-1] - Is the profit after buying stock at index i-1.
            profitAfterDoingNothing[i-1] - prices[i] - profit after buying the stock at index i, with profit earned till i-1, given cooldown at i-1.
            */

            profitAfterBuying[i] = Math.max(profitAfterBuying[i-1], profitAfterDoingNothing[i-1] - prices[i]);

            //2:
            /*
            profitAfterDoingNothing[i] - Profit after cooldown at i, a cooldown usually follows either another cooldown or sell.
            Therefore profitAfterDoingNothing[i] = Math.max(profitAfterDoingNothing[i-1],profitAfterSelling[i-1]);
            Where profitAfterDoingNothing[i-1] - Profit earned given cooldown at i
            profitAfterSelling[i-1] - Profit earned given a stock at index i is sold.
            */
            profitAfterDoingNothing[i] = Math.max(profitAfterDoingNothing[i-1], profitAfterSelling[i-1]);
            
            //3:
            /*
            profitAfterSelling[i] - To sell a stock at index i, we should have bought a stock at i-1
            ThereforeprofitAfterSelling[i] = Math.max(profitAfterBuying[i-1] + prices[i],profitAfterSelling[i-1]);
            Where profitAfterBuying[i-1] + prices[i] - Total profit after selling at index i
            profitAfterSelling[i-1]- Profit after selling at i-1, will equal profitAfterSelling[i] if selling a stock at index i is not profitable.
            */
            profitAfterSelling[i] = Math.max(profitAfterBuying[i-1] + prices[i], profitAfterSelling[i-1]);
        }

        int totalProfit = Math.max(profitAfterSelling[prices.length-1], profitAfterDoingNothing[prices.length -1]);
        return totalProfit;
    }

    public static void main(String args[]){
        int[] prices = {1,2,3,0,2};
        System.out.println(max_profit(prices));
    }
}