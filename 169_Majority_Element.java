/**
 * Given an array of size n, find the majority element. The majority element is
 * the element that appears more than ⌊ n/2 ⌋ times.
 * 
 * You may assume that the array is non-empty and the majority element always
 * exist in the array.
 * 
 * Example 1:
 * 
 * Input: [3,2,3] Output: 3 Example 2:
 * 
 * Input: [2,2,1,1,1,2,2] Output: 2
 */

/*
 * 
 * THEORY::: http://www.cs.utexas.edu/~moore/best-ideas/mjrty/
 */
import java.util.*;

class majority_element {

    public int majorityElement(int[] num) {

        int major = num[0], count = 1;
        for (int i = 1; i < num.length; i++) {
            if (count == 0) {
                count++;
                major = num[i];
            } else if (major == num[i]) {
                count++;
            } else
                count--;

        }
        return major;
    }
}

class majority_elements_diff_Ways {
    // Sorting
    public int majorityElement1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    // Hashtable
    public int majorityElement2(int[] nums) {
        Map<Integer, Integer> myMap = new HashMap<Integer, Integer>();
        // Hashtable<Integer, Integer> myMap = new Hashtable<Integer, Integer>();
        int ret = 0;
        for (int num : nums) {
            if (!myMap.containsKey(num))
                myMap.put(num, 1);
            else
                myMap.put(num, myMap.get(num) + 1);
            if (myMap.get(num) > nums.length / 2) {
                ret = num;
                break;
            }
        }
        return ret;
    }

    // Moore voting algorithm
    public int majorityElement3(int[] nums) {
        int count = 0, ret = 0;
        for (int num : nums) {
            if (count == 0)
                ret = num;
            if (num != ret)
                count--;
            else
                count++;
        }
        return ret;
    }

    // Bit manipulation
    public int majorityElement(int[] nums) {
        int[] bit = new int[32];
        for (int num : nums)
            for (int i = 0; i < 32; i++)
                if ((num >> (31 - i) & 1) == 1)
                    bit[i]++;
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            bit[i] = bit[i] > nums.length / 2 ? 1 : 0;
            ret += bit[i] * (1 << (31 - i));
        }
        return ret;
    }
}