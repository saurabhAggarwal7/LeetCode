/**
 * 
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]
 */

//THEORY + TRICK ::::

/**
 * 
 There are several ways to improve the naive dfs method: (1) memo using hashmap (like the one above) (2) DP (3) preprocess the string using word break I DP array to determine whether to go on or not (4) precompute the max length of all words in the dictionary to reduce the number of recursive calls. These are all good approaches when not all combinations are valid, but won't change the time complexity O(2^n) in the worse case scenario where all combinations of the string are correct (e,g, s=aaa, dic=[a, aa, aaa]). Some might argue that they reduce the number of recursive/iterative calls to n^2 using memo or DP just like word break I. However, the time complexity of each recursive call in this approach is not linear anymore. Imagine the length of sublist is 2^(n-1). Optimization only happens when the return value is a integer or boolean. This is why we don't use DP/memo to solve subsets/permutation problem because all combinations are valid. In addition, the code below combines (1) and (4) and beats 99% as the solution above suffers the problem that the dictionary size might be too large
 */

/**
 * //reduce the # of iterations using maxLen
    for(int i=start; i<start+maxLen&&i<s.length(); i++)
    instead of 
    for(int i=start; i<s.length(); i++)
 */

/**
 * s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
 "pine apple pen apple",
 "pineapple pen apple",
 "pine applepen apple"
]

Each Value in The list of string i also unique sentences
so the intention here is to create unique set of sentences as well as unique set of words in those sentences
definately we need to traverse using 2 for loops but how can we reduce the complexity of teh two loops is th trick above mentioned above
*/

import java.util.*;

class word_break_II {

    static int maxLen = 0;

    /**
     * 
     * @param s
     * @param wordDict
     * @return
     */
    static List<String> wordBreak(String s, List<String> wordDict) {

        // step-1: create a hasset from the word deict which is given to get the unique
        // set of values
        // get the max len as well during this loop:
        Set<String> hs = new HashSet<>();
        for (String w : wordDict) {
            hs.add(w);
            if (w.length() > maxLen)
                maxLen = w.length();
        }

        // create a map ::: {key}=> Integer {value}=>List<String>
        Map<Integer, List<String>> map = new HashMap<>();
        return helper(hs, s, 0, map);
    }

    /**
     * hs = ["apple", "pen", "applepen", "pine", "pineapple"]
     */
    static List<String> helper(Set<String> hs, String s, int start, Map<Integer, List<String>> map) {

        // itr-3 recursive call: start is i+1 = 4 now for next run
        // itr-4: recursive call: start is i+1 = 5 now for next run

        // map has the value at this {key}=>{start}
        if (map.containsKey(start))
            //start = 12
            //start = 9
            //End recursion finally and execute next value in stack
            return map.get(start);

        // this is the {value} in the hashmap:
        List<String> list = new ArrayList<>();
        if (start == s.length())
            //start = 17
            list.add("");

        // reduce the # of calls using maxLen:
        // Note even though there are 2 for loops doesnt mean that O(n^2) is achieved,
        // if it goes to O(n) both times then only its there

        // itr-1: s.length() =17 / maxLen=9 /start=0

        // OUTPUT:

        /**
         * s = "pineapplepenapple" wordDict = ["apple", "pen", "applepen", "pine",
         * "pineapple"] Output: [ "pine apple pen apple", "pineapple pen apple", "pine
         * applepen apple" ]
         */

        for (int i = start; i < start + maxLen && i < s.length(); i++) {
            if (hs.contains(s.substring(start, i + 1))) {

                // itr-3: i=3 : s.substring(start, i + 1):: "pine"
                // itr-4: i=4: s.substring(start, i + 1):: "apple"
                List<String> nexts = helper(hs, s, i + 1, map);

                for (String next : nexts) {
                    if (next == "") {
                        // reaches the end:

                        // i=16: s.substring(start, i + 1) + next = apple, next = "" nexts =""
                        list.add(s.substring(start, i + 1) + next);
                    } else {

                        // start-9: s.substring(start, i + 1) + " " next = pine apple
                        // next : apple
                        // s.substring(start, i + 1) = pine
                        // next= apple

                        // start-4: s.substring(start, i + 1)=> apple,
                        // s.substring(start, i + 1) + " " + next = "apple pen apple"
                        // next = "pen apple"

                        // AGAIN start =4 :
                        // .substring(start, i + 1)=> applepen
                        // .substring(start, i + 1) + " " + next => applepen apple
                        // next = "apple"

                        // Note this two times 4 as start will make the list to contain 2 elements
                        // so list--> [0] = {'apple pen apple'} [1]-> {'applepen apple'}

                        list.add(s.substring(start, i + 1) + " " + next);
                    }
                }
            }
        }

        // once the recursion ends, not always that it will go inside the 2nd loop
        // becuse the List<nexts> may be empty so it comeout of the loop and
        // go into map so

        // start=17: after recusrion 1st call here and not indie 2nd loop
        // list = ""
        // map{17}=> ""
        //start = 17, list-> ""
        //map {17}-> ""

        // start = 12: list-> apple, 
            //map {12}-> {value}-> "apple"
            //    {17}-> "" {value} 

        // start = 9: list-> pine apple 
                    //map {12}-> {value}-> "apple"
            //            {17}-> "" 
            //            {9}-> {value}-> "pine apple"

        
        //start = 4     
            //map         {12}-> {value}-> "apple"
            //            {17}-> "" 
            //            {9}-> {value}-> "pine apple"   
            //            {4}-> {value}-> [0] = {'apple pen apple'} [1]-> {'applepen apple'}       


        map.put(start, list);

        //After vsiting 4 times here to return list because of the previous recusrion calls the last call has map:
        /**
         * map {
         * key-> {0} {value}->
                        [0] "pine apple pen apple",
                        [1]"pineapple pen apple",
                        [2]"pine applepen apple"
            key-> {17} {value}-> ""
            {12}-> {value}-> "apple"
            {9}-> {value}-> "pine apple"   
            {4}-> {value}-> [0] = {'apple pen apple'} [1]-> {'applepen apple'}              
         * 
         * }
         */
        return list;
    }

    public static void main(String args[]) {
        String s = "pineapplepenapple";
        List<String> wordDict = new ArrayList<String>(Arrays.asList("apple", "pen", "applepen", "pine", "pineapple"));
        System.out.println(wordBreak(s, wordDict));
    }
}
