/*Given an array of integers, find if the array contains any duplicates.

Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.

Example 1:

Input: [1,2,3,1]
Output: true
Example 2:

Input: [1,2,3,4]
Output: false
Example 3:

Input: [1,1,1,3,3,4,3,2,4,2]
Output: true*/

import java.util.*;

class containsDuplicate{
    public boolean contains_duplicate(int[] nums){
        
        //create a hasset:
        Set<Integer> set = new HashSet<Integer>();
        for(int i: nums){
            if(!set.add(i))
                //contains same i, return true for dupliacte exists
                return true;
            return false;
        }
        return false;
    }
}






















