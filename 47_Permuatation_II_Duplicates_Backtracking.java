/*Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
] */

//SAME TECHNIQUE BACKTRACKING USING RECURSION
//HERE NOTE: NO SAME COMBINATION BUT SAME NUMBERS TO MAKE COMBINATION CAN EXISTS

import java.util.*;
class permutation_duplicate_nums{

    public List<List<Integer>> permute(int[] nums){
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    public void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, boolean[] used){
        if(tempList.size() == nums.length){
            //only when the size in temp array equals then add, all 3-3 combinations need to be created:
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i=0;i<nums.length; i++){

                //1. check for used element here, dupliactes in num[i] also exists so:
                if(used[i]) 
                    continue;

                //check for if the previous element is duplicate and is also not used previously    
                if(i> 0 && nums[i] == nums[i-1] && !used[i-1])
                    continue;
                
                //once these boundry conditions are met means they are used nums[i] now and in array of used there entry is true or false now
                used[i] = true;
                tempList.add(nums[i]);

                //recursion:
                backtrack(list, tempList, nums, used);

                //once the backtracking is complete the used element is false so that new element can make some neow combinatoon:
                used[i] = false;

                //remove the element from the last:
                tempList.remove(tempList.size() -1);
            }
        }
    }

}

class permuatation_duplicate_nums_display{
    public static void main(String args[]){
        int [] nums ={1, 1, 2};
        permutation_duplicate_nums obj = new permutation_duplicate_nums();
        System.out.println(obj.permute(nums));
    }
}