/**
 * Given an unsorted array return whether an increasing subsequence of length 3
 * exists or not in the array.
 * 
 * Formally the function should:
 * 
 * Return true if there exists i, j, k such that arr[i] < arr[j] < arr[k] given
 * 0 ≤ i < j < k ≤ n-1 else return false. Note: Your algorithm should run in
 * O(n) time complexity and O(1) space complexity.
 * 
 * Example 1:
 * 
 * Input: [1,2,3,4,5] Output: true Example 2:
 * 
 * Input: [5,4,3,2,1] Output: false
 */

class Increasing_triplet_Subsequence {

    static boolean increasingTriplet(int[] nums) {
        // start with two largest values, as soon as we find a number bigger than both,
        // while both have been updated, return true.
        int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= small) {

                //i-1: 1 < MAX_INTEGER, 
                //small =1 now
                
                small = n;
            } // update small if n is smaller than both
            else if (n <= big) {

                //i-2: 2<=INTEGER_MAX
                //big=2 now

                big = n;
            } // update big only if greater than small but smaller than big
            else
                //i-3: 3 so true its a triplet because its in sequence the tripleet so thet throid number will ebv returned
                return true; // return if you find a number bigger than both
        }
        return false;
    }

    public static void main(String args[]) {
        int[] arr = { 1, 2, 3, 4, 5 };
        System.out.println(increasingTriplet(arr));
    }
}