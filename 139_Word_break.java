/**
 * 
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
 */

 import java.util.*;
 class word_break{
    static boolean wordBreak(String s, List<String> dict){
        //DFS:
        List<Integer> set = new ArrayList<Integer>();
        return dfs(s, 0, dict, set);
    }

    static boolean dfs(String s, int index, List<String> dict, List<Integer> set){
        //exit condition: when entire length is traveresed and completed:
        if(index == s.length())
            return true;
        
        //check memory if this has already been traveresed
        if(set.contains(index))
            return false;
        
        //recursion:
        for(int i=index+1;i<=s.length();i++){

            //index=0, i=1:
            String t = s.substring(index, i);

            //a
            //p
            //p

            if(dict.contains(t));
                if(dfs(s, i, dict, set))
                    return true;
                else 
                    set.add(i);
        }
        set.add(index);
        return false;
    }

    public static void main(String args[]){
        String s = "applepenapple";
        List<String> wordDict = new ArrayList<String>(Arrays.asList("apple", "pen"));
        wordBreak(s, wordDict);
    }
 }