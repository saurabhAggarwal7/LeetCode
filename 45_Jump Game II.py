'''
You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].

Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:

0 <= j <= nums[i] and
i + j < n
Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].

 

Example 1:

Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [2,3,0,1,4]
Output: 2
 

Constraints:

1 <= nums.length <= 104
0 <= nums[i] <= 1000
It's guaranteed that you can reach nums[n - 1].
'''


'''
This problem is about finding the minimum number of jumps to reach the last index of an array where each element represents the maximum forward jump distance from that position.

Solution Explanation
The problem can be solved using a greedy algorithm:

Use two pointers:
current_jump_end: The farthest you can go with the current number of jumps.
farthest: The farthest position reachable from the current position.
Iterate through the array and update the farthest position.
When you reach current_jump_end, you must jump, so increment the jump count and update current_jump_end.

'''

def jumps(nums):
    jumps_count = 0
    current_jumps_end = 0
    farthest_i_reachable = 0

    #Input: nums = [2,3,1,1,4]
    # i = [0, 1, 2, 3, 4]
    for i in range(len(nums) -1):
        farthest_i_reachable = max(farthest_i_reachable, i + nums[i])

        # it means your jump ended now you have to make a jump_count again
        if i == current_jumps_end:
            jumps_count += 1

            #your current jump ended at farthest you can go
            current_jumps_end = farthest_i_reachable

    return jumps_count



if __name__ == "__main__":
    print(jumps([2,3,1,14]))