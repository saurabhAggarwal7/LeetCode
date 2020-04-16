/*REGEX Expressions:

Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
Example 4:

Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
Example 5:

Input:
s = "mississippi"
p = "mis*is*ip*."
Output: True

*/

//REMEMBER:
//s = "a" means Length is 1
//s.substring(1) = "ississippi"

class regex_expression {

    static boolean isMatch(String s, String p) {

        // equal length both null:

        if (p.length() == 0) {
            // Iteration-2: s= "aa", p ="a"
            // now s="a", p ="" so return False

            //Iteration-2: s="aa" and p ="a*", 
            //this will take p ="" so return false

            return s.length() == 0;
        }

        // Iteration-1: s= "aa", p ="a"

        //Pattern has only 1 character and that is not * but alphabet or . then handle:
        // case:1
        if (p.length() == 1 || p.charAt(1) != '*') {
            // case-2:
            if (s.length() < 1 || (p.charAt(0) != '.' && s.charAt(0) != p.charAt(0))) {
                return false;
            }
            // Iteration-1 s= "aa", p ="a"
            // p.substring(1) : ""
            // s.substring(1): a

            //First character is traversed:
            //Iteration-1: s="mississippi", p ="mis*is*ipi."
                            //s.substring(1) = "ississippi"
                            //p.substring(1) = "is*is*ip*."
            
            //Iteration-2: s="mississippi", p ="mis*is*ipi."
            //s.substring(1) = "ssissippi"
            //p.substring(1) = "s*is*ip*."
            
            return isMatch(s.substring(1), p.substring(1));
        } else {
            
            // "* CASE:"
            //HERE CHECK FOR . also while traversing the entire string in while loop
            //REMEMBER: * is used for as many of the same characters hence we used while loop

            int len = s.length();
            int i = -1;

            while (i < len && (i < 0 || p.charAt(0) == '.' || p.charAt(0) == s.charAt(i))) {

                //Iteration-1: s="aa" and p ="a*", p.substring(2) will be ""

                if (isMatch(s.substring(i + 1), p.substring(2)))
                    return true;
                i++;
            }
            return false;
        }
    }

    public static void main(String args[]) {
        System.out.println(isMatch("aa", "a"));

        System.out.println(isMatch("aa", "a*"));

        System.out.println(isMatch("mississippi", "mis*is*ip*."));
    }
}