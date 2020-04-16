/*680. Valid Palindrome II

Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True
Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.
Note:
The string will only contain lowercase characters a-z. The maximum length of the string is 50000. */

//Greedy Approach:
//https://www.geeksforgeeks.org/remove-character-string-make-palindrome/

/*

Explaination:

We can solve this problem by finding the position of mismatch. We start looping in the string by keeping two pointers at both the ends which traverse towards mid position after each iteration, this iteration will stop when we find a mismatch, as it is allowed to remove just one character we have two choices here,

At mismatch, either remove character pointed by left pointer or remove character pointed by right pointer.

We will check both the cases, remember as we have traversed equal number of steps from both sides, this mid string should also be a palindrome after removing one character, so we check two substrings, one by removing left character and one by removing right character and if one of them is palindrome then we can make complete string palindrome by removing corresponding character, and if both substrings are not palindrome then it is not possible to make complete string a palindrome under given constraint.

*/

// Java program to check whether 
// it is possible to make string 
// palindrome by removing one character 
import java.util.*;

class valid_palindrome_II {

    // Method-1: check for palondorme using substring from low and high indexes:
    static boolean isPalindrome(String str, int low, int high) {
        while (low < high) {
            if (str.charAt(low) != str.charAt(high)) {
                return false;
            }
            low++;
            high--;
        }
        return true;
    }

    // Method-2:

    // {return} {-1}: NOT POSSIBLE
    // {return} {-2}: Plaindrome already
    // {return} return the character by removal of whcih can make the while string a
    // plaindrome
    static int possiblePalindromeByRemoval(String str) {

        int low = 0;
        int high = str.length() - 1;

        while (low < high) {
            // if both characters are equal move towards each other good:
            if (str.charAt(low) == str.charAt(high)) {
                high--;
                low++;
            } else {
                // characters are not equal then two cass: try to remove either from left or
                // right i-e i+1 and i-1
                if (isPalindrome(str, low + 1, high))
                    return low;

                if (isPalindrome(str, low, high - 1))
                    return high;

                // even after traversinga nd removealn of one character its not a plaindrome:
                return -1;
            }

        }
        // This will be returned if it's already a plaoindrome
        return -2;
    }

    public static void main(String args[]){
        String str = "abca";
        int idx = possiblePalindromeByRemoval(str);

        if(idx == -1)
            System.out.println("Not possible");
        else if(idx == -2)
            System.out.println("Its already a plaindrome");
        else
            System.out.println("Can be possible by removing character at index" + idx); 
            System.out.println("Characetr is " + str.charAt(idx));
    }

}
