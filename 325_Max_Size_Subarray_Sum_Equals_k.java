
/**
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

Note:
The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

Example 1:
Given nums = [1, -1, 5, -2, 3], k = 3,
return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)


1 -1 5 -2 3
<---sum->

sum is [1 + -1 + 5 + -2]
sum" = [1 + -1]
sum - sum" = k
3 + 0 = 3
 */

 /**
  * TRICK: PUT {SUM"} IN MAP AS THE {KEY} and {INDEX} as {VALUE}
  can get (SUM-K) IN O(1) TIME COMPLEXITY
  */
 
 import java.util.*; 
class max_size_subarray_Sum_k{

    static int maxSubArrayLen(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int result = 0;
        int sum = 0;
     
        for(int i=0; i<nums.length; i++){
            sum += nums[i];
            if(map.containsKey(sum - k)){

                /**
                 * i=3 then also result is 4 
                 */

                /**
                 * //i=4
                 * sum-k = 6-3 = 3
                 * {key=3} it's {value=3} in map
                 * i-map.get(sum - k) 4-3 =1
                 * value from previous =4 as max
                 * MAP::::::
                 
                 *  "0":"-1"
                    "1":"0"
                    "3":"3"
                    "5":"2"
                 */
                result = Math.max(result, i - map.get(sum - k));
            }
            map.putIfAbsent(sum, i);
        }
     
        return result;
    }

    public static void main(String args[]){
        int[] nums = {1, -1, 5, -2, 3};
        int k = 3;
        System.out.println(maxSubArrayLen(nums, k));
    }
}