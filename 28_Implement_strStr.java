/*Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2
Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1
Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().*/

class implement_str {

    //Solution-1:
    public int strStr(String haystack, String needle) {

        int size = needle.length();

        //cond-1:
        if (haystack.length() < size)
            return -1;

        //cond-2:
        if (needle.isEmpty())
            return 0;

        //find the length of the window during the traversal and do substring
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0) && haystack.length() - i >= size
                    && haystack.substring(i, i + size).equals(needle))
                return i;
        }

        return -1;
    }

    //Solution:2
    public int strStr_2(String haystack, String needle) {
        if(needle.length()==0)
            return 0;
        if(haystack.contains(needle))
            return haystack.indexOf(needle);
         return -1;
    }
}