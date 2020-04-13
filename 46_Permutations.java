//BASED on Same Startegy REcursion with Backtracking:
//ALSO USED IN PERMUTAIONS/SUBSETS/POSSIBLE Combinations

/*
Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
*/

//Note that here the combinations taht will be generated will be unique and the given numbers are also unique (BOTH UNIQUE)

//i= 0, then backtrack between i=2 and i=3 to get different set of combinations
//playing around for loop i's value and recursion backtracking along to get different combinations

import java.util.*;
class permutations_find{
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // Arrays.sort(nums); // not necessary
        backtrack(list, new ArrayList<>(), nums);
        return list;
     }
     
     private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){

        //list[0]  //-> 0 ->1
                //-> 1-> 1
                //-> 2 -> 3
        
        //list[1] //-> 0->1, 1-> 3, 2-> 2

        //list
        if(tempList.size() == nums.length){
           list.add(new ArrayList<>(tempList));
        } else{
           for(int i = 0; i < nums.length; i++){ 
              if(tempList.contains(nums[i])) continue; // element already exists, skip
              tempList.add(nums[i]);
              backtrack(list, tempList, nums);

              //remove the element from temporary list from the last so that every time we get unique combination
              //whenever the length 3 hits in the temp list elements are added in the result list
              
              tempList.remove(tempList.size() - 1);
           }
        }
     } 
    }

class permutations_display{
    public static void main(String args[]){
        int[] arr = {1, 2, 3};
        permutations_find obj = new permutations_find();
        System.out.println(obj.permute(arr));
    }
}


