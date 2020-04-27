//contains duplicate::

/**
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the 
 * absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.

Example 1:

Input: nums = [1,2,3,1], k = 3, t = 0
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1, t = 2
Output: true
Example 3:

Input: nums = [1,5,9,1,5,9], k = 2, t = 3
Output: false
 */

 //K INDEX DIFFERENCE
 //T IS VALUE DIFFERENCE

 //nums[i]-nums[j] = atmost t
 //i-j = atmost k
 //[1,2,3,1], k = 3, t = 0 YES 1 at index 0 and 1 at index 3

 import java.util.HashMap;

 class contains_duplicate_III{
     boolean contains_nearby_almost_duplicate(int[] nums, int k, int t){
         if(k< 1 || t< 0)
                return false;
        
        HashMap<Long, Long> buckets = new HashMap<>();
        for(int i=0;i<nums.length;i++){

        //negative nums will cause edge cases of bucket targetting and bucket comparing, so reposition all nums, so that each num 
        //is converted to be positive

        //nums[i] must be converted to long firstly, since 
        //nums[i] - Integer.MIN_VALUE may already overflowed
        long normalizedNum = (long)nums[i] - Integer.MIN_VALUE;

         //choose bucket size to be t + 1, that's because:
        //bucket size == t: t = 0 would be an edge case, need to be processed separately
        //bucket size == t + 2: two nums with difference == t + 1 > t may fall into the same bucket, which will not be detected
        //bucket size == t - 1: instead of merely comparing bucket with bucket-1 and bucket+1, bucket-2 and bucket+2 also need to 
        // be compared, cause nums with difference == t may fall into bucket-2 and bucket+2
        
        //convert t to long, since t can be Integer.MAX_VALUE, causing overflow
        long bucketId = normalizedNum / ((long)t + 1);

        if(buckets.containsKey(bucketId) 
        || (buckets.containsKey(bucketId-1) && normalizedNum - buckets.get(bucketId-1) <= t)
        || (buckets.containsKey(bucketId+1) && buckets.get(bucketId+1) - normalizedNum <= t)) return true;
     
     //max distance is k, so bucket size is maintained to be no more than k
     if(buckets.size() == k){
         long outdatedBucket = ((long)nums[i-k] - Integer.MIN_VALUE) / ((long)t + 1);
         buckets.remove(outdatedBucket);
     }
        //If bucketId already exists in buckets, buckets.size() will < k, but that's ok, since this is normal case that two nums 
         //in the distance of [0, k] falled into the same bucket. What matters is that all nums in buckets must be in the distance
        //of [0, k]. So each loop pop the outdated bucket is a must. Whether the bucket size == k doesn't matter.
        buckets.put(bucketId, normalizedNum);


        }

        return false;
     }
 }


