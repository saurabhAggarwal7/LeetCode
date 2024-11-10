'''
Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.

 

Example 1:

Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:

Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
 

Constraints:

1 <= nums.length <= 105
-231 <= nums[i] <= 231 - 1
0 <= k <= 105
 

Follow up:

Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
Could you do it in-place with O(1) extra space?

'''

'''
Key Observations:
Effective rotations: If k is greater than the length of the array (n), rotating k times is equivalent to rotating k % n times.
For example, rotating an array of size 7 by 10 steps is the same as rotating it by 10 % 7 = 3 steps.

Three solutions:
Extra array (O(n) space): Use a temporary array to store rotated values.
Cyclic replacement (O(1) space): Use in-place rotations by swapping elements.
Reverse method (O(1) space): Reverse sections of the array.
'''

'''
Solution 1: Reverse Method (In-Place, O(1) Extra Space)
Steps:

Reverse the entire array.
Reverse the first k elements.
Reverse the remaining n - k elements.
This gives the desired result with minimal extra space.

[1,2,3,4,5, 6, 7] k=3 o/p => [5,6,7,1,2,3,4]

k=3 n=7 and n-k = 4
1.[7,6,5,4,3,2,1] 
2.[5,6,7, 4, 3, 2,1]
3.[5,6,7,1,2,3,4]

'''

def rotate(nums, k):
    n = len(nums)
    k=k%n #handles cases where k>n no need for extra computation

    #helper function: reverse array
    def reverse (start, end):
        while start <end:
            #This properly swaps elements without overwriting values prematurely.
            nums[start], nums[end] = nums[end], nums[start]  # Correct swap
            start = start+1
            end = end -1
    
    #reverse entire array:
    reverse(0, n-1) #reverse (start, end)

    #reverse only first k elements
    reverse(0, k-1)

    #reverse n-k elements
    reverse (k, n-1)


def test_rotate():
    test_cases = [
        ([1, 2, 3, 4, 5, 6, 7], 3, [5, 6, 7, 1, 2, 3, 4]),
        ([-1, -100, 3, 99], 2, [3, 99, -1, -100])
    ]

    for nums, k, expected in test_cases:
        rotate(nums, k)
        assert nums == expected, f"Failed for {nums} with k={k}"
    print (f"everyyhing passed 100%")

if __name__ == "__main__":
    test_rotate()





