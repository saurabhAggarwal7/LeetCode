/*Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.*/

//https://www.geeksforgeeks.org/find-the-smallest-window-in-a-string-containing-all-characters-of-another-string/

//Logic
/*
First check if the length of string is less than the length of the given pattern, if yes then “no such window can exist “.
Store the occurrence of characters of the given pattern in a hash_pat[].
Start matching the characters of pattern with the characters of string i.e. increment count if a character matches.
Check if (count == length of pattern ) this means a window is found.
If such window found, try to minimize it by removing extra characters from the beginning of the current window.
Update min_length.
Print the minimum length window.
*/

//Hashmap key: charcater and value is number of times it's repeating:
class MinWindowSubstring {

    static final int no_of_chars = 256; 

    static String findSubstring(String str, String pat){
        
        int len1= str.length();
        int len2 = pat.length();

        //base condition: check if pattern is more than string length:
        if(len2> len1) return "";
        
        //hashmaps for storing character as index and c ount as the value:
        int hash_pat[] = new int[no_of_chars];
        int hash_str[] = new int[no_of_chars];

        //store occurance of characters in the pattern:
        for (int i= 0; i<len2; i++){
            //character at "t" index goes to 2 because pattern has t count = 2
            hash_pat[pat.charAt(i)]++;
        }

        //window variables:
        int start = 0;
        int start_index = -1;
        int min_len = Integer.MAX_VALUE;

        int count =0;
        for (int j=0; j<len1;j++){

            //character at "t" index goes to 2 because pattern has t count = 2
            hash_str[str.charAt(j)]++;

            //If string's char matches with pattern's char then increment:
            if(hash_pat[str.charAt(j)] !=0 && hash_str[str.charAt(j)]<= hash_pat[str.charAt(j)]){
                count++;
            }

            //if all characters are matched:
            //then proceed to shorten the window:
            if(count == len2){
                // Try to minimize the window i.e., check if 
				// any character is occurring more no. of times 
				// than its occurrence in pattern, if yes 
				// then remove it from starting and also remove 
                // the useless characters. 

                //Either the character has 
                while((hash_str[str.charAt(start)] > hash_pat[str.charAt(start)]) || hash_pat[str.charAt(start)] == 0){
                    //repeat this until the characters count == 0 for the matched character or it's count > pattern's count (example- "t" in the pattern is repeated two times)

                    //check if the count in the string hashmap is more than required that from pattern hashmap:
                    //reduce the count value as well from the hashmap so that on next iteration it will get the updated value:
                    if(hash_str[str.charAt(start)] > hash_pat[str.charAt(start)]){
                        hash_str[str.charAt(start)]--;
                    }
                    start++;
                }

                //actual window size:
                int len_window = j-start +1;
                if(min_len > len_window){
                    min_len = len_window;
                    start_index = start;
                }
            }

        }

        //start_index will have the starting index for the resultant final window:
        if(start_index == -1){
            System.out.print("No such window exists");
            return "";
        }

        // Return substring starting from start_index 
		// and length min_len 
		return str.substring(start_index, start_index + min_len); 
    }

    public static void main(String[] args){
        String str = "this is a test string";
        String pattern = "tist";

        System.out.println("Smallest window is" + findSubstring(str, pattern));

        //Result is <<t stri>>
    }

}
