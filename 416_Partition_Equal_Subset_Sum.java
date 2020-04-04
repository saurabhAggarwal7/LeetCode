/*
Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:

Each of the array element will not exceed 100.
The array size will not exceed 200.
 

Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
 

Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.
*/

//Solution: memoization: DP:

import java.util.*;

class partition_Equal_Sum{


    static boolean canPartition(int[] nums) {
        int sum =0;

         //compute the sum of the array for all the numbes that will be need to split across two across sum/2 using DP approach using memoization
        for(int num : nums){
            sum+=num;
        }
        
        //create a memo store where we will store {}index,target as String with Boolean as True/false for it to be partioned or not
        Map<String, Boolean> memo = new HashMap<>();
        
        //Boundry case condition:
        //check if sum of all numbers is even number then only, aplit is possible:
        if(sum%2 == 0){
            return util(nums, 0, sum/2, memo);
        } else{
            return false;
        }
    }

    //@nums: nums
    //@index: 0
    //@target: sum/2
    //@Map<String, Boolean> memo
    static boolean util(int[] nums, int index, int target, Map<String, Boolean> memo ){

        ////target is sum/2 initially:
        String state = index+","+target;
        if(memo.containsKey(state)){
                return memo.get(state);
        }
        
         if(target == 0){
            return true;
        }
        
           if(index >= nums.length || target < 0){
            return false;
        }
        
        //Boolean condition1 = util(nums, index+1, target-nums[index], memo);
        //Boolean condition2 = util(nums, index+1, target, memo);
         boolean res =  util(nums, index+1, target-nums[index],memo) || util(nums, index+1, target, memo);
        
        memo.put(state, res);
        return res;
        
    }

    public static void main(String args[]){
        int[] arr ={1, 5, 11, 5};
        System.out.println(canPartition(arr));
        
    }
}



