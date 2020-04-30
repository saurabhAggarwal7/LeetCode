/**
 * https://leetcode.com/articles/palindrome-permutation-ii/
 * 
 * Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

Example 1:

Input: "aabb"
Output: ["abba", "baab"]
Example 2:

Input: "abc"
Output: []
 */

 /**
  * SOLUTION::
  It might be possible that no palindromic permutation could be possible for the given string ss. Thus, it is useless to generate the permutations in such a case. Taking this idea, firstly we check if a palindromic permutation is possible for ss. If yes, then only we proceed further with generating the permutations. To check this, we make use of a hashmap mapmap which stores the number of occurences of each character(out of 128 ASCII characters possible). If the number of characters with odd number of occurences exceeds 1, it indicates that no palindromic permutation is possible for ss. To look at this checking process in detail, look at Approach 4 of the article for Palindrome Permutation.

Once we are sure that a palindromic permutation is possible for ss, we go for the generation of the required permutations. But, instead of wildly generating all the permutations, we can include some smartness in the generation of permutations i.e. we can generate only those permutations which are already palindromes.

One idea to to do so is to generate only the first half of the palindromic string and to append its reverse string to itself to generate the full length palindromic string.

Based on this idea, by making use of the number of occurences of the characters in ss stored in mapmap, we create a string stst which contains all the characters of ss but with the number of occurences of these characters in stst reduced to half their original number of occurences in ss.

Thus, now we can generate all the permutations of this string stst and append the reverse of this permuted string to itself to create the palindromic permutations of ss.

In case of a string ss with odd length, whose palindromic permutations are possible, one of the characters in ss must be occuring an odd number of times. We keep a track of this character, chch, and it is kept separte from the string stst. We again generate the permutations for stst similarly and append the reverse of the generated permutation to itself, but we also place the character chch at the middle of the generated string.

In this way, only the required palindromic permutations will be generated. Even if we go with the above idea, a lot of duplicate strings will be generated.

In order to avoid generating duplicate palindromic permutations in the first place itself, as much as possible, we can make use of this idea. As discussed in the last approach, we swap the current element with all the elements lying towards its right to generate the permutations. Before swapping, we can check if the elements being swapped are equal. If so, the permutations generated even after swapping the two will be duplicates(redundant). Thus, we need not proceed further in such a case.

Complexity Analysis*****

TIME COMPLEXITY
O[(n/2 +1)!] 

Atmost n/2! permutations need to be generated in the worst case. Further, for each permutation generated, string.reverse() function will take n/4 time.

Space complexity : O(n). The depth of recursion tree can go upto n/2 in the worst case.
  */

  /**
   * use case example:::
   * s="AAALYAL"
   * st="AAL"
   * ch="Y"
   * 
   * A1A2L
   * 1. swap a1 with a1: <A1A2L>
   *    1.1: swap A2 with A2 <same>
   *    1.2 : swap A2 with L: <ALAYALA>
   * 
   * 2. A1A2L
   *    2.1 X
   * 
   * 3. LA2A1
   *    3.1: swap A2 with A2: <LA2A1>: <LA2A1>
   */

import java.util.*;
class Palindrome_Permutation_II {
    static Set <String> set = new HashSet<>();

    //If the number of characters with odd number of occurences exceeds 1, it indicates that no palindromic permutation is possible for s
    static boolean canPermutePalindrome(String s, int[] map) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
            if (map[s.charAt(i)] % 2 == 0)
                count--;
            else
                count++;
        }
        return count <= 1;
    }

    static List < String > generatePalindromes(String s) {
        int[] map = new int[128];
        char[] st = new char[s.length() / 2];

        //check if can permute else return:
        if (!canPermutePalindrome(s, map))
            return new ArrayList < > ();
        char ch = 0;
        int k = 0;

        for (int i = 0; i < map.length; i++) {

            //find a character ch whose count in map is odd times. this ch will be used a sthe middle element in the string
            if (map[i] % 2 == 1)
                ch = (char) i;
            
            //traverse just 1/2 of elements we know the other hlaf of palindrome will be same
            //using tis other half create an array st
            for (int j = 0; j < map[i] / 2; j++) {
                st[k++] = (char) i;
            }
        }

        //send st, ch to permute, add permuatation in set and return::
        permute(st, 0, ch);
        return new ArrayList < String > (set);
    }

    static void permute(char[] st, int l, char ch) {
        //st = ['a', 'b'] which is the 1/2 of original character array s = ["a, a, b, b"]
        //new String([]('s', 't')) = "st"

        if (l == st.length) {
            //Result is "(st)+(ch)+(st.reverse())"
            set.add(new String(st) + (ch == 0 ? "" : ch) + new StringBuffer(new String(st)).reverse());
        } else {
            for (int i = l; i < st.length; i++) {
                //i=0, l=0 and both have 'a'
                if (st[l] != st[i] || l == i) {

                    //swap and permute in recursion to create a combination of [st+ch+st_reverese()]
                    swap(st, l, i);
                    permute(st, l + 1, ch);

                    //for next iteration back to normal
                    swap(st, l, i);
                }
                int test=1; //for debug
            }
            int test=1; //for debug
        }
    }

    static void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    public static void main(String args[]){
        System.out.println(generatePalindromes("aabb"));
    }
}
