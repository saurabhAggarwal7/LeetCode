'''

Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.

Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:

Change the array nums such that the first k elements of nums contain the elements which are not equal to val. The remaining elements of nums are not important as well as the size of nums.
Return k.
Custom Judge:

The judge will test your solution with the following code:

int[] nums = [...]; // Input array
int val = ...; // Value to remove
int[] expectedNums = [...]; // The expected answer with correct length.
                            // It is sorted with no values equaling val.

int k = removeElement(nums, val); // Calls your implementation

assert k == expectedNums.length;
sort(nums, 0, k); // Sort the first k elements of nums
for (int i = 0; i < actualLength; i++) {
    assert nums[i] == expectedNums[i];
}
If all assertions pass, then your solution will be accepted.

 

Example 1:

Input: nums = [3,2,2,3], val = 3
Output: 2, nums = [2,2,_,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 2.
It does not matter what you leave beyond the returned k (hence they are underscores).
Example 2:

Input: nums = [0,1,2,2,3,0,4,2], val = 2
Output: 5, nums = [0,1,4,0,3,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4.
Note that the five elements can be returned in any order.
It does not matter what you leave beyond the returned k (hence they are underscores).
 

Constraints:

0 <= nums.length <= 100
0 <= nums[i] <= 50
0 <= val <= 100

'''

'''
Problem Explanation in Simple Words:
You are given an array of integers called nums and a value val. The goal is to remove all occurrences of val from the array in-place (i.e., without using extra space for another array), and return the number of elements in the array that are not equal to val.

Key Points:
In-place removal means you modify the original array itself instead of creating a new one.
The order of the elements that are not equal to val can change.
You return the number of valid elements (i.e., elements that are not equal to val).
Example:
Input:

python
Copy code
nums = [3, 2, 2, 3]
val = 3
Goal: Remove all 3s from nums and return how many elements are left that are not 3.

Expected Output:

python
Copy code
k = 2  # There are 2 elements left which are not 3.
nums = [2, 2, _, _]  # The first two elements in nums are 2, 2. The rest can be anything (e.g., `_`).
The array nums will become [2, 2] in the first two positions, but the remaining positions can be ignored. You just need to return how many valid elements (that are not val) are there.
Dry Run Step-by-Step:
Let's take an example to do a dry run of the solution.

Example:

python
Copy code
nums = [0, 1, 2, 2, 3, 0, 4, 2]
val = 2
Step 1: Initialization
k = 0: This pointer will keep track of the index where the next valid element should be placed (elements that are not equal to val).
nums = [0, 1, 2, 2, 3, 0, 4, 2] and val = 2.
Step 2: Iterating through the array:
First element (nums[0] = 0):

nums[0] != val, so it's a valid element.
Place it at nums[k] (which is nums[0]).
Increment k → k = 1.
Now, nums = [0, 1, 2, 2, 3, 0, 4, 2].
Second element (nums[1] = 1):

nums[1] != val, so it's a valid element.
Place it at nums[k] (which is nums[1]).
Increment k → k = 2.
Now, nums = [0, 1, 2, 2, 3, 0, 4, 2].
Third element (nums[2] = 2):

nums[2] == val, so we skip this element.
Fourth element (nums[3] = 2):

nums[3] == val, so we skip this element.
Fifth element (nums[4] = 3):

nums[4] != val, so it's a valid element.
Place it at nums[k] (which is nums[2]).
Increment k → k = 3.
Now, nums = [0, 1, 3, 2, 3, 0, 4, 2].
Sixth element (nums[5] = 0):

nums[5] != val, so it's a valid element.
Place it at nums[k] (which is nums[3]).
Increment k → k = 4.
Now, nums = [0, 1, 3, 0, 3, 0, 4, 2].
Seventh element (nums[6] = 4):

nums[6] != val, so it's a valid element.
Place it at nums[k] (which is nums[4]).
Increment k → k = 5.
Now, nums = [0, 1, 3, 0, 4, 0, 4, 2].
Eighth element (nums[7] = 2):

nums[7] == val, so we skip this element.
Step 3: Final Array
At the end of the loop:

k = 5 (there are 5 valid elements).
The first k = 5 elements of nums are [0, 1, 3, 0, 4].
The remaining elements in nums are irrelevant, and they can be anything (they don't matter).

Step 4: Return Value
Return k = 5 (the count of valid elements).
The final array looks like this (but the remaining elements don't matter):
python
Copy code
nums = [0, 1, 3, 0, 4, _, _, _]
'''


def remove_element(nums, val):
    k =0 #pointer
    for i in range(len(nums)):
        if nums[i] != val:
            nums[k] = nums[i]
            k +=1
    return k