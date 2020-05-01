/**
 * 
 * Question: /*
There is a fence with n posts, each post can be painted with one of the k colors.
You have to paint all the posts such that no more than two adjacent fence posts have the same color.
Return the total number of ways you can paint the fence.
Note:
n and k are non-negative integers.
Tags: Dynamic Programming
Similar Problems: (E) House Robber, (M) House Robber II, (M) Paint House, (H) Paint House II
*/


/*
Thoughts:
dp[i]: i ways to paint first i posts.
dp[i] depends on dp[i-1] or dp[i - 2]: no more than 2 adjacent same color means: dp[i] will differ from dp[i-1] or dp[i-2]
The remain color will be k
*/

 /*
Thoughts:
Inspiration(http://yuanhsh.iteye.com/blog/2219891)
Consider posts from 1 ~ n. Now we look at last post, marked n:
S(n) means: last 2 fence posts have same color.
	Note: S(n) will equal to whatever that's on n-1 position.
	Also, just because n and n-1 are same, that means n-2 and n-1 have to be differnet.
SO:
S(n) = D(n - 1)
D(n) means: last 2 fence posts have different color.
	Note: for n - 1, and n-2 positions, we have 2 different conditions:
	For example: xxy, or wxy, same 2 x's or different w vs. x.
So: 
D(n) = (k - 1) * (D(n - 1) + S(n - 1))
We can also create T(n) = S(n) + D(n); //T(n) is our totoal results. Will need to return T(n);
Use above equations to figure out T(n)
T(n) = S(n) + D(n) = D(n - 1) + (k - 1) * (D(n - 1) + S(n - 1))
	= D(n - 1) + (k - 1)(T(n - 1))
	= (k - 1) * (D(n - 2) + S(n - 2)) + (k - 1)(T(n - 1))
	= (k - 1)(T(n - 1) + T(n - 2))
	Since n-2 >=1, so n>=3. We need fiture out cases for n = 0,1,2,3
Note:
n == 1: just k ways
n == 0: just 0.
k == 0: just 0;
Besides these cases, we are okay. Btw, k does not really matter as long as it's >=1, it can be plugged in.
 */

 /**
  * tags: DP, Sequence DP
time: O(n)
space: O(n)
  */
class paint_fence {

    static int calc_numWays(int n, int k) {
        if(n <= 0) {
            return 0;
        }
        
        int sameColorCounts = 0;
        int differentColorCounts = k;
        
        for(int i = 2; i <= n; i++) {
            int temp = differentColorCounts;
            differentColorCounts = (sameColorCounts + differentColorCounts) * (k - 1);
            sameColorCounts = temp;
        }
        
        return sameColorCounts + differentColorCounts;
    }

    static int numWays_II(int n, int k) {
        // edge case
        if (n <= 1 || k <= 0) {
            return n * k;
        }
        // init dp[n+1]
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = k;
        dp[2] = k + k * (k - 1); // [1,2] same color + [1,2] diff color
        
        // process
        for (int i = 3; i <= n; i++) {
            dp[i] = (k - 1) * (dp[i - 1] + dp[i - 2]);
        }
        
        return dp[n];
    }

    public static void main(String args[]){

    }
}
