/**
 * /*
Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
For example, given nums = [-2, 0, 1, 3], and target = 2.
Return 2. Because there are two triplets which sums are less than 2:
[-2, 0, 1]
[-2, 0, 3]
Follow up:
Could you solve it in O(n2) runtime?
Tags: Array Two Pointers
Similar Problems:(M) 3Sum, (M) 3Sum Closest
*/


/*
Thoughts:
Similar to 3 sum, but ofcourse, this one check on '<' so we can not use HashMap anymore.
Basic concept is to fix first number, then check for the rest two numbers, see if they addup < target.
When checking j and k, realize something nice:
	if nums[j] + nums[k] < target - nums[i], that means for all index <= k will work, so directly add (k - j) to result (that's: index = j+1, j+2, ....,k)
	also, move j forward for next round.
OR, if three-add-up >= target, since j can only increase, we do k-- to make the three-add-up smaller
Note:
Don't forget to sort, otherwise the sequence/order is unpredictable
*/

import java.util.*;
class smaller_3_sum {

    static int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length <= 2) {
        	return 0;
        }

        //sorting of array
        Arrays.sort(nums);

        //result
        int rst = 0;

        for (int i = 0; i < nums.length - 2; i++) {

            //when i=0 j=1 //when i=1, j=2
            int j = i + 1; 

            //3//3 for i=1
            int k = nums.length - 1; 
            
        	while (j < k) { //1<3
        		if (nums[i] + nums[j] + nums[k] >= target) {
                    //now 2 for i=0 case //still 2 for i=1 case
        			k--; 
        		} else {
                     //(3-1) = 2 for i=0
                    rst += (k - j);
                     //now 2 for i=0
        			j++;
        		}
        	}
        }//END for
        return rst;
    }

    public static void main(String args[]){
        int[] nums = {-2, 0, 1, 3};
        System.out.println(threeSumSmaller(nums, 2));
    }
}