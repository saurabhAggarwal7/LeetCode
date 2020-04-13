/*
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]*/

import java.util.*;
class combinations_sum_non_duplicates{
    public List<List<Integer>> permute(int[] nums, int target){
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    public void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start){
        if(remain < 0) 
            return;

        else if(remain == 0)
            list.add(new ArrayList<>(tempList));

        else{
            for(int i=start; i<nums.length;i++){

                //check for duplicates adjacent in input and skip them :
                if(i> start && nums[i] == nums[i-1])
                    continue;

                //add in temp list: 
                tempList.add(nums[i]);

                //recursion:
                //remain = remain-nums[i]
                //start next will be i+1
                backtrack(list, tempList, nums, remain-nums[i], i+1);

                //remove elements from back
                tempList.remove(tempList.size() -1);
            }
        }//ends

    }
}

class combination_sum_non_dupliactes_display{
    public static void main(String args[]){
        int[] nums = {10,1,2,7,6,1,5};
        combinations_sum_non_duplicates obj = new combinations_sum_non_duplicates();
        int target = 8;
        System.out.println(obj.permute(nums, target));

    }
}

