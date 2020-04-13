/*
Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/

//SAME STARTEGY AS USED FOR PERMUATION BASED QUESTIONS:
//BACKTRACKING RECURSION
//ALSO USED IN SUBSETS/PERMUTATION based questions

import java.util.*;
class subsets_duplicates{

    public List<List<Integer>> subsetsWithDup(int[] nums){
        List<List<Integer>> list = new ArrayList<>();

        //sort the numbers
        Arrays.sort(nums);

        //recursion:
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start){
        
        /*Output:
        [
        [2],
        [1],
        [1,2,2],
        [2,2],
        [1,2],
        []
        ]
        */
        //add [0] ->list in List<List>>
        
        //list[0] -> []
        //list[1] -> 
                    //[0] -> 1

        //list[2] ->//[0] -> 1 
                    //[1] ->2

        //list[3] ->  //[0] -> 1
                    //[1] -> 2
                    //[2]  -> 2  
        
        //////Now the List [] to try new combinations backtrack is finished and will continue once again:

        //list[4] -> [0] -> 2   

        //list[5] -> [0] -> 2
                    //[1] -> 2    

        //result structure to store the list of subsets possible:            
        list.add(new ArrayList<>(tempList));

        //num[0] = 1, num[1] = 2, num[2] = 2
        for(int i= start; i<nums.length;i++){

            //Happens when i=2 where num[1] = 2 and num[2] is also equal to 2
            //skip the duplicates in subsets
            if(i> start && nums[i] == nums[i-1]){                
                continue; 
            }

            //i=0, tempList[0] -> 1
            //i=1, tempList[1] -> 2
            //i=2, tempList[2] -> 2
            tempList.add(nums[i]);

            backtrack(list, tempList, nums, i+1);

            //remove element from templist from last:
            //once i=0, 1, 2 is done nw start remove the elements from tempList to try new combinations
            
            //accessed first when i=2
            // tempList prior: [0][1][2]
            // tempList now: []
            tempList.remove(tempList.size() -1);
        }

    }
}

class display_subsets_duplicates{
    public static void main(String args[]){
        subsets_duplicates obj = new subsets_duplicates();
        int[] arr_input = {1, 2, 2};
        System.out.println(obj.subsetsWithDup(arr_input));
    }
}