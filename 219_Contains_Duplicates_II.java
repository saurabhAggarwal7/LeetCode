import java.util.HashMap;

/*
Given an array of integers and an integer k, find out whether there are 
two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.

Example 1:

Input: nums = [1,2,3,1], k = 3
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1
Output: true
Example 3:

Input: nums = [1,2,3,1,2,3], k = 2
Output: false
*/

//I-J = K and VALUE AT I AND J should be same
//TRICK: MAP PUT also returns value:

//A map put returns a value when it's already avaialble in map esle null
//Example:

//Before:
//{map} = {key=1, value = 0}
/*
Now put {key=1, value = 3}
map.put returns 0 i-e the previous value stored for the same key
//mai is updated now qith the new value with the same key
new map is {key=1, value =3 }
*/


//{1,2,3,1};
//I = 0 and Value is 1
//J = 3 and VALUE is also 1
//I-J = 3 and K is also given as 3


import java.util.*;
class contains_difference_elements{

    //arr = {1,2,3,1};
    //arr = {(0)1,(1)2,(2)3,(3)1};
    static boolean containsNearbyDuplicate(int[] nums, int k){
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int i=0; i<nums.length;i++){
            
            //{key, value} : {nums[i], i}
            //{key}--> nums[i]
            //{value}--> i

            //map key-> value
            //nums[i]-> i
            //{1->0, 2->1, 3->2}

            //now same key already exists i-e 1 so:
            //put updates the key with new value and returns the older value stored at that key
            //in this case ord=0 because it was the older value stored at key 1
            Integer ord = map.put(nums[i], i);
            if(ord != null && i-ord <=k){
                return true;
            }
        }

        return false;
    }


    //SOLUTION-2: Simple
    static boolean containsNearbyDuplicate_2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (i - map.get(nums[i]) <= k) 
                        return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public static void main(String args[]){
        int[] arr = {1,2,3,1};
        System.out.println(containsNearbyDuplicate(arr,3));
    }
}