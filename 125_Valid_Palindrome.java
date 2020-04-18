/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true
Example 2:

Input: "race a car"
Output: false
*/

class valid_palindrome {

    static boolean isPlaindrome(String s) {
        char[] ch = s.toCharArray();
        int i = 0;
        int j = ch.length - 1;
        while (i < j) {
            if(!Character.isLetterOrDigit(ch[i]))
                i++;
            else if(!Character.isLetterOrDigit(ch[j]))
                j--;
            else if(Character.toLowerCase(ch[i++]) != Character.toLowerCase(ch[j--])){
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]) {
        System.out.println(isPlaindrome("A man, a plan, a canal: Panama"));
    }
}
