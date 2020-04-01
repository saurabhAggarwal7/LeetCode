import java.util.HashMap;

/*
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
*/

//Algorithm:
/*
The idea behind this approach is as follows: If the cumulative sum(repreesnted by sum[i] for sum upto ith index) upto two indices is the same, the sum of the elements lying in between those indices is zero. 
Extending the same thought further, if the cumulative sum upto two indices, say i and j is at a difference of k i.e. if sum[i] - sum[j] = k, the sum of elements lying between indices i and j is k.
Based on these thoughts, we make use of a hashmap which is used to store the cumulative sum upto all the indices possible along with the number of times the same sum occurs. We store the data in the form: (sum_i, no. of occurences of sum_i)
​We traverse over the array nums and keep on finding the cumulative sum. Every time we encounter a new sum, we make a new entry in the hashmap corresponding to that sum. If the same sum occurs again, we increment the count corresponding to that sum in the hashmap. 
Further, for every sum encountered, we also determine the number of times the sum that is {sum-k} has occured already, since it will determine the number of times a subarray with sum k has occured upto the current index. We increment the count by the same amount.

After the complete array has been traversed, the countcount gives the required result.
*/

class SubArray_Sum_K{
    static int subarraySum(int[] nums, int k){
        int count =0, sum =0;

        //map's value is count, key is sum so map is --> {sum, count}
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0,1);

        for(int i=0;i<nums.length;i++){
            sum+= nums[i];
            if(map.containsKey(sum-k)){
                count+= map.get(sum-k);
            }
            int value = map.getOrDefault(sum, 0)+1;
            map.put(sum, value);
        }
        return count;
    }

    public static void main(String args[]){
        int[] arr = {1, 1, 1};
        System.out.println(subarraySum(arr, 2));
    }
}