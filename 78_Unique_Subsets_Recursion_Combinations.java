/*Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]*/

//[[1, 2, 3, 4], [1, 2, 3], [1, 2, 4], [1, 2], [1, 3, 4], [1, 3], [1, 4], [1], [2, 3, 4], [2, 3], [2, 4], [2], [3, 4], [3], [4], []]
import java.util.ArrayList;
import java.util.List;

class subsets {

    static List<List<Integer>> subsets(int[] nums){
      List<List<Integer>> l = new ArrayList<>();
      subsets_compute(nums, 0, new int[nums.length], l);
      return l;
    }

    static void subsets_compute(int[] nums, int index, int[] temp, List<List<Integer>> subsets){
      if(index == nums.length){
        List<Integer> l = new ArrayList<>();
        add(l, temp, nums);
        subsets.add(l);
        return;
      }
      temp[index]=1;
      subsets_compute(nums, index+1, temp, subsets);
      temp[index]=0;
      subsets_compute(nums, index+1, temp, subsets);
    }

    static void add(List<Integer> l, int[] temp, int[] nums){
      for(int i=0; i<temp.length;i++){
        if(temp[i] == 1){
          l.add(nums[i]);
        }
      }
    }
    


	public static void main (String[] args) { 

        int arr[] = {1, 2, 3, 4}; 
        System.out.println(subsets(arr)); 
        } 
}