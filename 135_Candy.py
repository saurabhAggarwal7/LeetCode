'''
There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
Return the minimum number of candies you need to have to distribute the candies to the children.

 

Example 1:

Input: ratings = [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
Example 2:

Input: ratings = [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
The third child gets 1 candy because it satisfies the above two conditions.
 

Constraints:

n == ratings.length
1 <= n <= 2 * 104
0 <= ratings[i] <= 2 * 104
'''

'''
SOLUTION::

Problem Explanation:
You are given an array ratings where each element represents the rating of a child. Your task is to distribute candies to the children such that:

Each child gets at least one candy.
A child with a higher rating than their neighbor gets more candies than that neighbor.
The goal is to find the minimum number of candies required to satisfy these rules.

Key Observations:
Local Minimums:
Children with lower ratings than both their neighbors can receive the minimum of 1 candy.

Increasing Sequences:
If a child has a higher rating than their left neighbor, they should get more candies than the neighbor.

Decreasing Sequences:
Similarly, if a child has a higher rating than their right neighbor, they should also get more candies.

Approach:
We'll use a two-pass greedy algorithm:

Left-to-Right Pass:

Ensure that each child gets more candies than the previous child if their rating is higher.
Right-to-Left Pass:

Ensure that each child gets more candies than the next child if their rating is higher.
This ensures that both constraints are satisfied.

Example Walkthrough:
Input: ratings = [1, 0, 2]
Step 1: Left-to-Right Pass

Start with 1 candy for each child: [1, 1, 1]
Compare each child with their left neighbor:
ratings[1] = 0 < ratings[0] = 1 → No change.
ratings[2] = 2 > ratings[1] = 0 → Give more candies to ratings[2]: [1, 1, 2].
Step 2: Right-to-Left Pass

Compare each child with their right neighbor:
ratings[1] = 0 < ratings[2] = 2 → No change.
ratings[0] = 1 > ratings[1] = 0 → Give more candies to ratings[0]: [2, 1, 2].
Final Result: Total candies = 2 + 1 + 2 = 5.

Dry Run:
For ratings = [1, 2, 2]:

Initial Candies: [1, 1, 1].

Left-to-Right Pass:

Compare ratings[1] > ratings[0]: [1, 2, 1].
Compare ratings[2] == ratings[1]: No change.
Right-to-Left Pass:

Compare ratings[1] > ratings[2]: No change.
Compare ratings[0] < ratings[1]: No change.
Final candies = [1, 2, 1], sum = 4.

Complexity:
Time Complexity: O(n), where n is the length of ratings. We traverse the array twice.
Space Complexity: O(n) for the candies array.
'''

def candy(ratings):
    n = len(ratings)
    candies = [1] * n # Create a array of 1 candy each 

    #L to R:
    #If R>L  give more candy to left
    for i in range(1, n):
        if ratings[i] > ratings[i-1]:
            candies[i] = candies[i-1] + 1
    
        '''
    This is a Right-to-Left Pass in the array. Let's break it down:

range(n - 2, -1, -1):
n - 2: Start from the second-to-last index.
-1: Stop before reaching index -1 (i.e., stop at index 0).
-1: Decrease the index by 1 in each iteration (moving backward through the array).
In simpler terms, this loop iterates backward through the array, starting from the second-to-last element (n - 2) and ending at the first element (0).
    '''
        
    #R to L
    for i in range(n-2, -1, -1):
        #If L>R
        if ratings[i] > ratings[i+1]:
            #max(candies[i], candies[i + 1] + 1) ensures that the current child keeps their original candies or receives more if needed.
            candies[i] = max(candies[i], candies[i + 1] + 1) 
    return sum(candies)