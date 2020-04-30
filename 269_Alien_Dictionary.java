/**
 * 
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:
Given the following words in dictionary,
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".
---->> Means we have to derive a relation from the string that which character preceeds or exceeds the following chacater


Example 2:
Given the following words in dictionary,
[
  "z",
  "x"
]
The correct order is: "zx".
Example 3:
Given the following words in dictionary,
[
  "z",
  "x",
  "z"
]
The order is invalid, so return "".
Note:
You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.

SOlution: https://github.com/Cee/Leetcode/blob/master/269%20-%20Alien%20Dictionary.java

 */

 /**
  * Solution Steps:
  1. create frequency map/degree map with all unique characters as 0
  2. loop through dict and get next and current word
    2.1 find next and current charcter as per words length
    2.2 if charcters are not same then {check if c2 is in set}
    2.3 if not then put (c1, set) where set.add(c2) in Map<charcter, Set<>>
    2.4 also increment the degree count of character c2 for hashmap degree
  3.find that characters whose degree is 0 from the degree map so that we get the starting point of result
  4. while LL of q is not empty, add the character whose degree was 0 to result
    4.1 find next of this chacter from the map Hashmap and decrement it's count in degree Hashmap
    4.2 Once its next's degree reaches 0 then add this also in q LL and repaeat the process

  */

 import java.util.*;
class alien_dictionary {

    static String alienOrder(String[] words) {
        Map<Character, Set<Character>> map = new HashMap<>();
        Map<Character, Integer> degree = new HashMap<>(); //frequency map

        String result = "";
        if (words == null || words.length == 0) { return result; }

        // Degree char = 0
        for (String s: words) {
            for (char c: s.toCharArray()) {
                //{degree hashmap}: create the key of all the words and put value->0 
                degree.put(c, 0);
            }
        }
        
        for (int i = 0; i < words.length - 1; i++) {
            String curr = words[i]; //wrt
            String next = words[i + 1]; //wrf
            int min = Math.min(curr.length(), next.length()); //3

            for (int j = 0; j < min; j++) {
                char c1 = curr.charAt(j); //w //r //t
                char c2 = next.charAt(j); //w //r //f

                if (c1 != c2) { //when j=2 it's //t and //f

                    //for unique charcetrs across c1 and c2 i-e current and next find the associted set and if not add in the set 
                    Set<Character> set = map.getOrDefault(c1, new HashSet<>());
                    if (!set.contains(c2)) {
                        set.add(c2);

                        //put in set
                        //update count of characters in degree hashmap
                        map.put(c1, set);
                        degree.put(c2, degree.get(c2) + 1); // update c2, c1 < c2
                    }
                    break;
                }
            }
        }

        /**
         * Core t order is "wertf"
         */

         //map's key is character 1st anf=d map's value is character Next
         //as we can see [rt, tf, er, we]

        /**
         * map hashmap i-e {string, Set<String>} is:
         * 
         * 0-> "r" {t} value
         * 1-> "t" {f} value
         * 2-> "e" {r} value
         * 3-> "W" {e} value
         */
        
        /**
         * degree map is:
         * { r=1, t=1, e=1, f=1, w=0 }
         */

         /**
          * q linkedlist is "w"
          */

        LinkedList<Character> q = new LinkedList<>();
        for (char c: degree.keySet()) {
            if (degree.get(c) == 0) {
                q.add(c);//w degree of w =0
            }
        }
        
        //we start traversing from place where we have frequency of elements as 0
        //then to their next element
        //then that element once becomes 0 as frequency then so on....

        while (!q.isEmpty()) {
            char c = q.poll(); //w //e

            //Final result is wertf
            result += c; //w //we

             //yes 3-> "W" {e} value 
             //yes 2-> "e" {r} value
            if (map.containsKey(c)) {
                for (char next: map.get(c)) {
                    //next = e
                    //next = r

                    degree.put(next, degree.get(next) - 1); //e, 0 //r, 0
                    if (degree.get(next) == 0) { //yes //yes

                        //insert e now in Linkedlist and while loop goes on
                        //insert r now in Linkedlist and while loop goes on
                        q.offer(next); 
                    }
                }
            }
        }
        
        return result.length() == degree.size() ? result : "";
    }

    public static void main(String args[]){
        String[] dict = {
            "wrt",
            "wrf",
            "er",
            "ett",
            "rftt"
          };
        System.out.println(alienOrder(dict));
    }
}