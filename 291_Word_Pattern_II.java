//HARD---> RECURSION INSIDE THE IF STATEMENT, MAYBE SOPME EASY SOLUTION REQUIRED OR BETTER DEBUG

/**
 * This is the extension problem of Word Pattern I.

Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Examples:
pattern = "abab", str = "redblueredblue" should return true.
pattern = "aaaa", str = "asdasdasdasd" should return true.
pattern = "aabb", str = "xyzabcxzyabc" should return false.

DIFFERENCE BETWEEN I AND II IS THAT NOW HERE THE SPACE SPLITTING IS NOT THERE AND INSTEAD WE HAVE TO FIND A SUBSTRING THAT HAS THE SAME PATTERN
 */

import java.util.*;

class Word_Pattern_II {

    static boolean wordPatternMatch(String pattern, String str) {
        if(pattern.length()==0 && str.length()==0)
            return true;
        if(pattern.length()==0)
            return false;
     
        HashMap<Character, String> map = new HashMap<Character, String>();
        HashSet<String> set = new HashSet<String>();
        return helper(pattern, str, 0, 0, map, set);
    }
     
    static boolean helper(String pattern, String str, int i, int j, HashMap<Character, String> map, HashSet<String> set){
        if(i==pattern.length() && j==str.length()){
            return true;
        }
     
        if(i>=pattern.length() || j>=str.length())
            return false;
        
        //a    
        char c = pattern.charAt(i);

        //pattern = "abab", str = "redblueredblue"

        //j is i+1 from recursion
        //k is j+1 from for loop

        for(int k=j+1; k<=str.length(); k++){

            //substring from 1-2 ==> r
            String sub = str.substring(j, k);
            if(!map.containsKey(c) && !set.contains(sub)){

                //{key=a}, {value=r}
                map.put(c, sub);

                //{set=r}
                set.add(sub);

                //i=>i+1, j->k repeat this 
                if(helper(pattern, str, i+1, k, map, set))
                    return true;
                
                map.remove(c);
                set.remove(sub);
            }else if(map.containsKey(c) && map.get(c).equals(sub)){
                if(helper(pattern, str, i+1, k, map, set))
                    return true;
            }
        }
     
        return false;
    }

    public static void main(String args[]){
        System.out.println(wordPatternMatch("abab", "redblueredblue"));
    }
}