'''
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

 

Example 1:


Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9
 

Constraints:

n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105
'''

'''
Problem Explanation:
You are given an array height where each element represents the height of a bar in an elevation map. Your task is to calculate the total amount of water that can be trapped between these bars after it rains.

Key Observations:
Water is trapped between two bars if there is a taller bar on both the left and right sides of a given bar.
The amount of water trapped above a bar is determined by the minimum of the maximum height of the bars to its left and right, minus its own height.
Approach:
We can solve this problem using different methods:

Brute Force (O(n²)): Calculate the water trapped for each bar by iterating to find the maximum heights on the left and right.
Dynamic Programming (O(n) time, O(n) space): Pre-compute the maximum heights from the left and right for each bar.
Two-Pointer Approach (O(n) time, O(1) space): Use two pointers to optimize the space usage while maintaining O(n) time complexity.
We'll focus on the Two-Pointer Approach, which is efficient and intuitive.


'''

def trap(height):
    '''
    type: height List[int]
    rType: int
    '''
    if not height:
        return 0

    left, right = 0, len(height) - 1
    left_max, right_max = 0, 0
    water_trapped = 0

    while left < right:
        if height[left] < height[right]:
            if height[left] >= left_max:
                left_max = height[left]
            else:
                water_trapped += left_max - height[left]
            left += 1
        else:
            if height[right] >= right_max:
                right_max = height[right]
            else:
                water_trapped += right_max - height[right]
            right -= 1

    return water_trapped

if __name__ == "__main__":
    #black box in figure's height complete not points on graph
    trap([0,1,0,2,1,0,1,3,2,1,2,1])

