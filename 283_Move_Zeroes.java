/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.
 */

class Move_Zeroes {
    public void moveZeroes(int[] nums) {

        int j=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] != 0){
                if(i!=j){ //reduce the nuimber of comparison operations by using i!=j
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                }
                j++;
            }
        }

    }
}