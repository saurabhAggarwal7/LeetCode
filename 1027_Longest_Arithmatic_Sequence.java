/**
 * Given an array A of integers, return the length of the longest arithmetic subsequence in A.

Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1, and that a sequence B is arithmetic if B[j+1] - B[j] are all the same value (for 0 <= j < B.length - 1).

 

Example 1:

Input: [3,6,9,12]
Output: 4
Explanation: 
The whole array is an arithmetic sequence with steps of length = 3.
Example 2:

Input: [9,4,7,2,10]
Output: 3
Explanation: 
The longest arithmetic subsequence is [4,7,10].
Example 3:

Input: [20,1,15,3,10,5,8]
Output: 4
Explanation: 
The longest arithmetic subsequence is [20,15,10,5].
 

Note:

2 <= A.length <= 2000
0 <= A[j] <= 10000
 */

 /**
  * SOLUTION::
  The main idea is to maintain a map of differences seen at each index.

We iteratively build the map for a new index j, by considering all elements to the left one-by-one.
For each pair of indices (j,i) and difference d = A[j]-A[i] considered, we check if there was an existing chain at the index i with difference d already.

If yes, we can then extend the existing chain length by 1.
Else, if not, then we can start a new chain of length 2 with this new difference d and (A[i], A[j]) as its elements.
At the end, we can then return the maximum chain length that we have seen so far.
  */

  /**
   * At each index, the diff's found are stored. Then we use the previous index to populate the current index values. I wrote an example, hope it helps :

Example : [3,6,9,12]
//store diffs found at each index. Then add to previously found diff and create max.
/*
0 -> {{}}
1 -> {{3, 2}}  max = 2
2 -> {{6, 2}, {3,3}} (adding 2 from previous)} max = 3
3 -> {{9, 2}, {6, 3}} (adding 2 from previous) , {3, 4} (adding 3 from previous)} max = 4
*/

import java.util.*;
 class longest_arithmatic_sequence{

    static int longestArithSeqLength(int[] A) {

        int res = 2, n = A.length;
        HashMap<Integer, Integer>[] map = new HashMap[n];

        //create a list of hashmaps so 2 for loops used:

        for (int i = 0; i < A.length; i++) {
            map[i] = new HashMap<>();

            for (int j = 0; j < i; j++) {
                int difference = A[i] - A[j];

                //check if we already have some available map like "3" below: so update value with it's value+1
                //map {key} is difference , {value} is count+1
                int val = map[j].getOrDefault(difference, 1);

                map[i].put(difference, val + 1);
                res = Math.max(res, map[i].get(difference));
            }
        }
        /**
         * [9,4,7,2,10]
         * 
         * map: 
         * 
            0:map size=0
            1:map size=1
                0:map "-5":"2" ----> 4-9 = -5, 1+1
            2:map size=2
                0:map "-2":"2" ------> 7-9 =-2, 1+1
                1:map "3":"2" --------> 7-4 = 3, 1+1 ###############
            3:map size=3
                0:map "-2":"2" ------->2-4=-2, 1+1
                1:map "-5":"2" -------> 2-7=-5, 1+1
                2:map "-7":"2"---------> 2-9=-7, 1+1
            4:map size=4
                0:map "1":"2" ---------->10-9=1, 1+1
                1:map "3":"3", ---------> 10-7=3, already exists with value 2 so now 2+1 = 3 ##########
                2:map "6":"2"----------> 10-4=6, 1+1
                3:map "8":"2"-----------> 10-2=8 1+1
         * 
         * 
         * 

         */
        return res;
    }
     public static void main(String args[]){
        /**
         * Input: [9,4,7,2,10]
            Output: 3
            [4,7,10].
         */
        int[] arr = {9,4,7,2,10};
        System.out.println(longestArithSeqLength(arr));

     }
 }