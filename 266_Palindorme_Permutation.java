/**
 * 
 * Given a string, determine if a permutation of the string could form a palindrome.

For example, “code” -> False, “aab” -> True, “carerac” -> True.

Hint:

Consider the palindromes of odd vs even length. What difference do you notice? Count the frequency of each character. If each character occurs even number of times, then it must be a palindrome. How about character which occurs odd number of times?

Analysis

A native solution is to generate the permutation of the string, then check whether it is a palindrome. 

A better solution is suggested from the above hint.

If each character occurs even numbers, then a permutation of the string could form a palindrome. 
If only one character occurs odd number of times, it can also form a palindrome. like aba, abbba. 
We can use a HashTable to count the frequency of the characters in the string. A better one is to use a HashSet, if a character is in the HashSet, and we see it again, we remove it from the HashSet. This means the character occurs even times. At the end, There are two valid possibilities:

1. The HashSet is empty, which means all the characters occur even times, returning true;

2. The HashSet contains only one character, which means only one character occurs odd times. returning true.

Otherwise, there are more than one characters occurring odd times. returning false;

 * http://www.learn4master.com/interview-questions/leetcode/leetcode-palindrome-permutation
 * 
 * Theory HashSet Vs hashmap::
 * HashSet does not allow duplicate elements that means you can not store duplicate values in HashSet. HashMap does not allow duplicate keys however it allows to have duplicate values. HashSet permits to have a single null value. HashMap permits single null key and any number of null values.
 */

import java.util.HashSet;
import java.util.Set;

 class palindrome_permutation{

    static boolean canPermutePalindrome(String s) {
        Set<Character> set = new HashSet<Character>();
        for(int i = 0; i < s.length(); i++){

            // occur even time, remove it from the set
            Character cur = s.charAt(i);
            if(set.contains(cur)){
                set.remove(cur);
            } else {
                // occur odd time
                set.add(cur);
            }
        }
        // only at most one character can occur odd times
        return set.size() <= 1;
    }

     public static void main(String args[]){
         //
     }
 }