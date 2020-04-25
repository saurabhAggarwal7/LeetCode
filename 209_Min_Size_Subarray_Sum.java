/**
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

Example: 

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.
Follow up:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n). 
 */

class min_Size_subarray_Sum{

    static int minSubArrayLen(int s, int[] a) {
        if (a == null || a.length == 0)
          return 0;
        
        int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;
        
        while (j < a.length) {
            
           //1. j=0 sum = sum+ a[j] i-e a[0] so sum = 2 
           //2. j=1 sum+=arr[1] = 3
           //3. j=2 sum+arr[2] = 1
           //4. j=3 sum+arr[3]= 2
           //after 4 tries now sum = 8 now it can enter while loop
          sum += a[j++];
          
          while (sum >= s) {
            //5. min= Min(MAX, 4-0)   so min = 4 It means atkeast 4 elements will be required to make it equal to sum
            min = Math.min(min, j - i);

            //move the window forward so, i++ and remove from sum the value of a[i] that is why we took j-i in above and here also we subtract a[i++]
            sum -= a[i++];
          }
        }
        
        return min == Integer.MAX_VALUE ? 0 : min;
      }

     public static void main(String args[]){
         int[] nums = {2,3,1,2,4,3};
         System.out.println(minSubArrayLen(7, nums)); //answer is 2
         //7 = [4, 3] which is 2 elements
     }
 }