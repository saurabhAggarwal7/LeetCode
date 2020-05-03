/**
 * Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Example 1:

Input: pattern = "abba", str = "dog cat cat dog"
Output: true
Example 2:

Input:pattern = "abba", str = "dog cat cat fish"
Output: false
Example 3:

Input: pattern = "aaaa", str = "dog cat cat dog"
Output: false
Example 4:

Input: pattern = "abba", str = "dog dog dog dog"
Output: false
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters that may be separated by a single space.
 */

 //SOLUTION: I go through the pattern letters and words in parallel and compare the indexes where they last appeared.

 //TRICK: MAP.PUT(..)
 //It returns the previous value associated with key, or null if there was no mapping for key.

 //DONT DEFINE <STRING, STRING>() FOR THE MAP AND PUT THE VALUES PARALELLY IN THE MAP AND COMPARE

 //TRICK IS PARALLEL MAPS TO COMPARE BETWEEN TWO VALUES

import java.util.*;
class Word_Pattern {
        static boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length())
            return false;
        Map map = new HashMap<>();
        for (Integer i=0; i<words.length; ++i)
            /**
             * i=0, {key}=dog {key}=a value for both is 0, both returns null bcz no value was there previously
             * i=1  {key}=cat {key}=b value for both is 1, return null both
             * i=2  {key} = dog {key}=a, value for both now becomes 2 from 0 and both returns 0
             */
            if (map.put(pattern.charAt(i), i) != map.put(words[i], i))
                return false;
        return true;
    }

    public static void main(String args[]){
        System.out.println(wordPattern("abba", "dog cat cat dog"));
    }
}