/*Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
] */

//THIS IS DIFFERENT FROM OTHER TRICKS AS SAME:
//tempList.add(s.substring(start, i + 1)); 

import java.util.*;
class palindrome_partitioning_backtrack{

    public List<List<String>> partition(String s){
        List<List<String>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), s, 0);
        return list;
    }

    public void backtrack(List<List<String>> list, List<String> tempList, String s, int start){
        //when the entore string is traversed add this result here:
        if(s.length() == start){
            list.add(new ArrayList<>(tempList));
        }  else{
            for(int i = start; i < s.length(); i++){
               if(isPalindrome(s, start, i)){
                  //TempList is from (Start to i+1)
                  tempList.add(s.substring(start, i + 1));
                  //Bactrack
                  backtrack(list, tempList, s, i + 1);
                  //remove from last
                  tempList.remove(tempList.size() - 1);
               }
            }
         }
    }

    public boolean isPalindrome(String s, int low, int high){
        while(low< high){
            if(s.charAt(low++) != s.charAt(high--))
                    return false;
        }
        return true;
    }
}

class palindrome_partitioning_display{
    public static void main(String args[]){
        String s = "aab";
        palindrome_partitioning_backtrack obj = new palindrome_partitioning_backtrack();
        System.out.println(obj.partition(s));
    }
}
