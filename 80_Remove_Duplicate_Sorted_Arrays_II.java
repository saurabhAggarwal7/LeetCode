
/*
Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:

Given nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.

It doesn't matter what you leave beyond the returned length.
Example 2:

Given nums = [0,0,1,1,1,1,2,3,3],

Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.

It doesn't matter what values are set beyond the returned length.
Clarification:

Confused why the returned value is an integer but your answer is an array?

Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.

Internally you can think of this:

// nums is passed in by reference. (i.e., without making a copy)
int len = removeDuplicates(nums);

// any modification to nums in your function would be known by the caller.
// using the length returned by your function, it prints the first len elements.
for (int i = 0; i < len; i++) {
    print(nums[i]);
}

*/

class Remove_Duplicate_Sorted_Arrays_II {

    static int removeduplicates_generic(int[] nums, int k){
        int i=0;
        //nums: {1,1,1,2,2,3};
        for(int n: nums){
            if(i < k || n > nums[i-k]){
                nums[i] = n;
                i++;
            }
        }
        return i;
        //REMEMBER: THIS SOLUTION DOESNOT ACTUALLY RESULT ARRAY INTO NON DUPLICATES BUT JUST TOTAL LENGTH
        //NUMS RETURNED WILL BE = {1, 2,3(TILL THIS PART I++), 2, 2, 3(NO I++ HERE)} THATS WHY WE GET 3
        //FOR OTHERS IT WONT GO INSIDE THE IF CONDITION SO ONLY 3 AS THE VALUE OF I
    }

    public static void main(String args[]){
        int[] arr = {1,1,1,2,2,3};

        //by default: 1: no duplicates
        System.out.println(removeduplicates_generic(arr, 1));

        //2 duplicates allowed so k=2 here
        System.out.println(removeduplicates_generic(arr, 2));
    }
}