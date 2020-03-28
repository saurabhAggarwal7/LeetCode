import java.util.ArrayList;
import java.util.List;

//Vector is very useful if we don't know the size of an array in advance or we need one that can change the size over the lifetime of a program. Vector is synchronized.
/*abstractGiven a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
*/

class Unique_Combination_Sum{

    public static boolean solve(List<Integer> l, int target, int[] cand, int start, List<List<Integer>> ans){
        
        if(target<0) 
            return false;

        if(target == 0){
            ans.add(l);
            return true;
        }

        for(int i=start; i<cand.length;i++){
            //  add only ,if adding that element doesn't make target negative.
            if(target - cand[i] >=0 ){
                //  just add the element for now 
                l.add(cand[i]);

                if(!solve(new ArrayList<Integer>(l),target-cand[i],cand ,i,ans));
                
                // if following path return false then remove the elements we added previously, while backtracking
                 l.remove(l.size()-1);


            }
        }

        return false;
    }
    
    static List<List<Integer>> combinationSum(int[] cand, int target){
        
        //multiple compinations hence result will be of this type:
        List<List<Integer>> ans = new ArrayList<>();

        //
        List<Integer> l = new ArrayList<Integer>();
        solve(l, target, cand, 0, ans);

        //by reference:
        return ans;
    }

    public static void main(String args[]){
        int[] candidates = {2,3,6,7};
        int target = 7;
        System.out.println(combinationSum(candidates, target));
    }

}