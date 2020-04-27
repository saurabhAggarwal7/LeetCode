/**
 * 
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

Follow up:
Could you solve it in linear time?

Example:

Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 
Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 

Constraints:

1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
1 <= k <= nums.length
 */

 /**
  * The poll() method of Queue Interface returns and removes the element at the front the container. It deletes the element in the container. The method does not throws an exception when the Queue is empty, it returns null instead.

  The java.util.ArrayDeque.pollLast() method in Java is used to retrieve or fetch and remove the last element of the Deque. The peekLast() method only retrieved the element at the end but the pollLast() also removes the element along with the retrieval. It returns NULL if the deque is empty.
  */

import java.util.*;
class Sliding_Window_Problem {
    static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) {
            return nums;
        }
        int[] result = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<Integer>();
        for (int i = 0; i < n; i++) {
            int window = i-k+1; //window widthk k= 3
            if (!dq.isEmpty() && dq.peek() < window) {
                dq.poll();
            }
            while (!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
                dq.pollLast();
            }

            //i=0 window = -2
            //i=1 window = -1
            //insert:
            dq.offer(i);

            //i=2, window =0
            if (window >= 0) { 
                //i=2, window =0, result[0] => nums[1]
                result[window] = nums[dq.peek()];
            }
        }
        return result; //[3, 3, 5, 5, 6, 7]
    }

    static int[] maxSlidingWindow_2(int[] a, int k) {		
		if (a == null || k <= 0) {
			return new int[0];
		}
		int n = a.length;
		int[] r = new int[n-k+1];
		int ri = 0;
		// store index
		Deque<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < a.length; i++) {
			// remove numbers out of range k
			while (!q.isEmpty() && q.peek() < i - k + 1) {
				q.poll();
			}
			// remove smaller numbers in k range as they are useless
			while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
				q.pollLast();
			}
			// q contains index... r contains content
			q.offer(i);
			if (i >= k - 1) {
				r[ri++] = a[q.peek()];
			}
		}
		return r;
	}

    public static void main(String args[]){
        int[] arr = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        System.out.println(maxSlidingWindow(arr, k));
    }
}