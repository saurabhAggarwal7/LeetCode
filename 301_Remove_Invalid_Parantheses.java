
/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
Example 3:

Input: ")("
Output: [""]
 * 
 * Key Points:

Generate unique answer once and only once, do not rely on Set.
Do not need preprocess.
Runtime 3 ms.

Explanation:
We all know how to check a string of parentheses is valid using a stack. Or even simpler use a counter.
The counter will increase when it is ‘(‘ and decrease when it is ‘)’. Whenever the counter is negative, we have more ‘)’ than ‘(‘ in the prefix.

To make the prefix valid, we need to remove a ‘)’. The problem is: which one? The answer is any one in the prefix. However, if we remove any one, we will generate duplicate results, for example: s = ()), we can remove s[1] or s[2] but the result is the same (). Thus, we restrict ourself to remove the first ) in a series of concecutive )s.

After the removal, the prefix is then valid. We then call the function recursively to solve the rest of the string. However, we need to keep another information: the last removal position. If we do not have this position, we will generate duplicate by removing two ‘)’ in two steps only with a different order.
For this, we keep tracking the last removal position and only remove ‘)’ after that.

Now one may ask. What about ‘(‘? What if s = ‘(()(()’ in which we need remove ‘(‘?
The answer is: do the same from right to left.
However a cleverer idea is: reverse the string and reuse the code!
Here is the final implement in Java.
 */

import java.util.*;
class Remove_Invalid_Parantheses {
    static List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();

        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }
    
    static void remove(String s, List<String> ans, int last_i, int last_j,  char[] par) {
        for (int stack = 0, i = last_i; i < s.length(); ++i) {

            if (s.charAt(i) == par[0]) 
                stack++;

            if (s.charAt(i) == par[1]) 
                stack--;

            if (stack >= 0) 
                continue;

            //*******Till this opint we tend to romove all the cases with ( and ) pattern ends */    

            //stack=-1
            //s= "()())()"
            //last_i=0
            //i=4
            //last_j=0
            //char[] par = ['(' and ')']   
            //par[0] = '('
            //par[1] = ')'

            for (int j = last_j; j <= i; ++j)
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
                    remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
            return;
        }

        String reversed = new StringBuilder(s).reverse().toString();

        //s= "()())()"
        //reversed: ")())(("
        if (par[0] == '(') // finished left to right
            remove(reversed, ans, 0, 0, new char[]{')', '('});

        else // finished right to left
            ans.add(reversed);
    }

    public static void main(String args[]){
        System.out.println(removeInvalidParentheses("()())()"));
    }
}