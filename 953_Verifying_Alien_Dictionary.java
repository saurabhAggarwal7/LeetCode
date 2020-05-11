/**
 * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.

Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 */

 class verify_lexical_order_alien{
    static boolean isAlienSorted(String[] words, String order) {
        int[] dict = new int[26];

        //Step-1: create your custom A-Z map based on order string i-e create a dict based on String of order given, that will tell us which character is at which order
        for (int i = 0; i < dict.length; i++) {
            int idx = order.charAt(i) - 'a'; // h-'a'=7 //l-'a' =11
            dict[idx] = i; //dict[7] = 0 //dict[11] = 1
        }

        //Step-2:  apply this dict on the words array and compare
        for (int i = 0; i < words.length -1; i++) {

            //if the next word exceeds then result will be more than 0 so FALSE, order is broken:
            if(compare(words[i], words[i +1], dict) > 0)
                return false;
        }

        return true;
    }

    static int compare(String word1, String word2, int[] dict)
    {
        int L1 = word1.length(); //hello
        int L2 = word2.length(); //leetcode
        //{word-1: "hello", word-2: "leetcode"}

        int min = Math.min(L1,L2);
        for (int i = 0; i < min; i++) {
            int c1 = word1.charAt(i) - 'a'; //h-a //7
            int c2 = word2.charAt(i) - 'a';//l-a  //11

            //aim is to get dict[c1] to be less than dict[c2] that proves that c2 is after c1
            if(c1 != c2)
                return dict[c1] - dict[c2]; //0-1 = -1
        }
        return L1 == min ? -1 : 1;
    }

    public static void main(String args[]){
        String[] words = {"hello","leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        System.out.println(isAlienSorted(words, order));
    }
 }