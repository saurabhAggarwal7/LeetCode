'''
There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.

Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1. If there exists a solution, it is guaranteed to be unique.

 

Example 1:

Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
Output: 3
Explanation:
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
Therefore, return 3 as the starting index.
Example 2:

Input: gas = [2,3,4], cost = [3,4,3]
Output: -1
Explanation:
You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 0. Your tank = 4 - 3 + 2 = 3
Travel to station 1. Your tank = 3 - 3 + 3 = 3
You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
Therefore, you can't travel around the circuit once no matter where you start.
 

Constraints:

n == gas.length == cost.length
1 <= n <= 105
0 <= gas[i], cost[i] <= 104
'''


'''
Problem Explanation:
You are given two arrays:

gas: gas available at each station.
cost: gas required to travel from each station to the next.
Your task is to find the index of the gas station from which you can start and complete the circular route. If no such station exists, return -1.

Key Insight:
To successfully complete the circuit:

The total gas collected must be greater than or equal to the total cost.
If you can't complete the circuit starting from a station, it means every station up to the failure point also won't work. You should start from the next station after that.
Example 1:
Input:
gas = [1, 2, 3, 4, 5]
cost = [3, 4, 5, 1, 2]

Explanation:
Start at index 3:

At station 3: gas = 4, cost to next station = 1, tank = 4 - 1 = 3.
At station 4: gas = 5, cost = 2, tank = 3 + 5 - 2 = 6.
At station 0: gas = 1, cost = 3, tank = 6 + 1 - 3 = 4.
At station 1: gas = 2, cost = 4, tank = 4 + 2 - 4 = 2.
At station 2: gas = 3, cost = 5, tank = 2 + 3 - 5 = 0.
You return to station 3 with 0 gas. So, start index is 3.

Example 2:
Input:
gas = [2, 3, 4]
cost = [3, 4, 3]

Explanation:

No station allows completion of the journey.
Return -1.


Logic Breakdown:
The problem involves finding the starting index of the gas station from where you can travel around the circular route and complete the circuit, using a greedy approach. Here's how we can break it down step by step:

1. Total Gas vs Total Cost:
Before jumping to the solution, think about the total gas and total cost:

Total gas: This is the sum of all the gas at each station.
Total cost: This is the sum of all the gas required to travel from each station to the next.
If the total gas is less than the total cost, it's impossible to complete the journey because we don't have enough gas overall, no matter where we start.
If the total gas is greater than or equal to the total cost, then it's possible to complete the circuit from some station.

2. Greedy Strategy:
Start from station 0 and try to move clockwise.
For each station, check if you can move to the next station with the gas you have in your tank.
Keep track of two things:
current_tank: The amount of gas you have after each move (this resets to 0 when you can't move to the next station).
total_tank: The total difference between the gas available and the gas required to travel, accumulated over all stations.
3. How the Algorithm Works:
Initialization:
Start by setting:

total_tank = 0: This keeps track of the overall gas balance across all stations.
current_tank = 0: This keeps track of the gas you have while traveling through stations.
start_station = 0: This is the index of the station from where you're considering starting the journey.
Iterate Through Each Station: For each station i:

Add the difference between gas[i] and cost[i] to both total_tank and current_tank.
gas[i]: The amount of gas you get at the station.
cost[i]: The amount of gas it costs to travel to the next station.
If current_tank becomes negative, it means you can't reach the next station from your current starting point.
Hence, you reset the start station to the next one (start_station = i + 1), and reset current_tank = 0 because you’re starting over from the next station.
Final Check: After iterating through all stations:

If total_tank >= 0, this means it's possible to complete the circuit, and start_station is the correct starting point.
If total_tank < 0, return -1, meaning it's impossible to complete the circuit.
4. Why This Works:
The key to this solution is the greedy strategy. If, at any point, current_tank goes negative, we know that no station in between the previous start and the current station can be the correct starting point.
Thus, we reset and try starting from the next station.
Because the total gas available is sufficient to meet the total cost, the algorithm ensures that if there's a solution, we will find it.

Example Walkthrough (for gas = [1,2,3,4,5] and cost = [3,4,5,1,2]):
Initialization:
total_tank = 0, current_tank = 0, start_station = 0.

Iteration:

i = 0:

gas[0] = 1, cost[0] = 3.
total_tank += (1 - 3) = -2, current_tank += (1 - 3) = -2.
Since current_tank < 0, we reset start_station = 1 and current_tank = 0.
i = 1:

gas[1] = 2, cost[1] = 4.
total_tank += (2 - 4) = -4, current_tank += (2 - 4) = -2.
Since current_tank < 0, we reset start_station = 2 and current_tank = 0.
i = 2:

gas[2] = 3, cost[2] = 5.
total_tank += (3 - 5) = -6, current_tank += (3 - 5) = -2.
Since current_tank < 0, we reset start_station = 3 and current_tank = 0.
i = 3:

gas[3] = 4, cost[3] = 1.
total_tank += (4 - 1) = -3, current_tank += (4 - 1) = 3.
current_tank is now positive, so we continue.
i = 4:

gas[4] = 5, cost[4] = 2.
total_tank += (5 - 2) = 0, current_tank += (5 - 2) = 6.
Continue traveling, and we can finish the circuit.
Conclusion:
total_tank >= 0, so the starting station is start_station = 3. We can complete the circuit starting from station 3.

Why this is Efficient:
Time Complexity: O(n)
We iterate over the stations only once, making it an efficient solution.
Space Complexity: O(1)
We only use a few variables for tracking, so the space usage is constant.

'''

#gas = [1, 2, 3, 4, 5] and cost = [3, 4, 5, 1, 2]
def can_complete_circuit(gas, cost):
    total_tank = 0 
    current_tank = 0 
    start_station_index = 0

    for i in range(len(gas)):
        total_tank = total_tank + (gas[i] - cost[i])
        current_tank = current_tank + (gas[i] - cost[i])

        if current_tank < 0:
            start_station_index = i + 1
            current_tank = 0 # total tank is not reset bcz it is for whole journey

    if total_tank >=0:
        return start_station_index
    else:
        return -1

if __name__ == "__main__":
    can_complete_circuit([1, 2, 3, 4, 5], [3, 4, 5, 1, 2])

