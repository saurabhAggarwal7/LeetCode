/**
 * Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Note:

Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:

What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */

/**
 * FOLLOW UP ANSWERS:
 If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, read chunks of array that fit into the memory, and record the intersections.

An improvement can be sort them using external sort, read (let's say) 2G of each into memory and then using the 2 pointer technique, then read 2G more from the array that has been exhausted. Repeat this until no more data to read from disk.

External sort is a trick used to implement JOIN, basically called sort-merge join.
 */

/**
 * https://www.youtube.com/watch?v=lKuK69-hMcc&feature=youtu.be
 * 
 */

 /**
  * 
What if the given array is already sorted? How would you optimize your algorithm?
--> Go with the solution-2 here we are sorting the arrys first so we can remove the sorting part

What if nums1's size is small compared to nums2's size? Which algorithm is better?
--> Use the solution-1 with the hashmaps such that we have less computations

What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
--> Then divivde the arrays into many many different chunks and the apply hashmaps solutoon over them
  */

 import java.util.*;
 class intersection_arrays_Solution_one {

    static int[] intersect_1(int[] nums1, int[] nums2) {
        //Space Complexity: O(min(m, n)) becuase we are checking here first which one is less er array length
        //Time Complexity: O(m+n) because we are traversing the two entire arrays to entire length

        //Swap the numbers if smaller bigger thing:
        if(nums1.length > nums2.length){
            return intersect_1(nums2, nums1);
        }

        //Create a Frequency map for nums_1 which is smaller array
        Map<Integer, Integer> map = new HashMap();
        for (int i : nums1) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        List<Integer> list = new ArrayList();
        for (int i : nums2) {
            if (map.containsKey(i) && map.get(i) > 0) {
                list.add(i);
                map.put(i, map.get(i) - 1);
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    static int[] intersect_2(int[] nums1, int[] nums2) {
        //Space Complexity O(1)
        //Time Complexity O(mlogm+nlogn) due to sorting

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0;
        int j = 0;

        //Two pointers i-> nums_1 and j-> nums_2

        List<Integer> list = new ArrayList<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return list.stream().mapToInt(k -> k).toArray();
    }

    public static void main(String args[]){
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        //Output: [4,9]
        System.out.println(intersect_1(nums1, nums2));
        System.out.println(intersect_2(nums1, nums2));
    }
}
