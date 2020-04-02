import java.util.*;

/*We are supposed to solve the problem in Time Complexity better than O(nlgn).
If that constraint were not present, here is what can be done.
Use Map (Dictionary) and store the frequency of the number.
Sort the map by values.
Pick only first k objects.
But since, the constraint is present, we can use the following algorithm:
Sample input: [1,1,1,2,2,3] and k = 2

1Use map/dictionary and store the frequency of the number and maximum frequency of all the numbers.
So at the end of this operation, for the sample problem, map would look like this: 1 → 3, 2 → 2, 3 →1. Also, maximum frequency will be 3.
2Now, since, we cannot use regular sorting approach, another thing that comes to mind is, bucket sort.
3Create a multi-storage bucket with (maximum frequency + 1)as its size. Now, based on frequency of the word, put it in the appropriate bucket level. In our example, Put 1 at level 3, put 2 at level 2 and put 3 at level 1.
4There might be more than one numbers with the same frequency. So, we can use linked list to store more than one elements at the same level.
5Now, iterate over the bucket elements and keep a counter to match with the input value k.
*/

class Top_K_Frequent_Elements{

    static List<Integer> topKElements(int[] nums, int k){
        int len = nums.length;
        int maxFreq = 0;

        //Step:1: Use map/dictionary and store the frequency of the number and maximum frequency of all the numbers.
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i=0; i<len;i++){
            //{key} is the number in array
            //{value} is the map's value gaain, + 1 {counter}
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
            maxFreq = Math.max(maxFreq, map.get(nums[i]));
        }

        //Step-3:Create a multi-storage bucket with (maximum frequency + 1)as its size. 
        
        List<Integer> [] bucketList = new LinkedList[maxFreq+1];
        for(int i=0; i<= maxFreq;i++){
            bucketList[i] = new LinkedList<>();
        }

        //Put elements in buckets by iterating over the map:
        for(Integer key: map.keySet()){
            int freq = map.get(key);
            //In our example, Put 1 at level 3, put 2 at level 2 and put 3 at level 1.
            ////Now, based on frequency of the word, put it in the appropriate bucket level. 
            bucketList[freq].add(key);
        }

        //Step-5: Now, iterate over the bucket elements and keep a counter to match with the input value k.

        //outer loop is to loop backwards from maxFreq to 0
        //Because we dont know till what point we need to loop back.
        // same numbers with same frequency can also exists

        //Inner loop makes sure that K=2 is only returned and not anything else extra
        //ct takes care of k=2 values
        int ct = 0;
        List<Integer> ans = new LinkedList<>();
        for(int i=maxFreq; i>=0;i--){
            List<Integer> currentList = bucketList[i];

            for(Integer j: currentList){
                if(ct < k){
                    ans.add(j);
                    ct++;
                } else{
                    return ans;
                }
            }
        }

        return ans;
    } 
    public static void main (String args[]){
        int[] arr = {1,1,1,2,2,3};
        int k= 2;
        List<Integer> ans = topKElements(arr, k);
        System.out.println(ans);

    }
}