/**
 * Encode and Decode Strings
Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:
string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}

Machine 2 (receiver) has the function:
vector<string> decode(string s) {
  //... your code
  return strs;
}

So Machine 1 does:
string encoded_string = encode(strs);
and Machine 2 does:
vector<string> strs2 = decode(encoded_string);

strs2 in Machine 2 should be the same as strs in Machine 1.
Implement the encode and decode methods.

Note:
The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
Understand the problem:
This is just an implementation problem. The key is how to separate the list of strings during the serialization process so we can decode the string in the de-serialization process.

One way we can think of is to use the number at front. e.g. abcdef, we can store 6abcdef. 
However, what if the string also starts from numbers, e.g. 123abc. In this case, what we stored is 6123abc, which is wrong. Therefore, we need to use another divider to divide the length of the string with the string itself. In this solution, we just use '#'. 

One thing needs to be careful in this such kind problem is the length of the string, which is in the form of string, is not a single character. Therefore, we need to parse the string until we see the divider. 
 */

 /**
  * 
  */

import java.util.*;
class Encode_Decode {

    //[hey, what] ---> 3#hey4#what
    static String encode(List<String> strs) {
        if (strs == null || strs.size() == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
         
        for (String str : strs) {
            if (str == null || str.length() == 0) {
                sb.append("0#");
            } else {
                sb.append(str.length() + "#" + str);
            }
        }
         
        return sb.toString();
    }
 
    // Decodes a single string to a list of strings.
    //decode this ---> 3#hey4#what
    static List<String> decode(String s) {
        List<String> result = new ArrayList<>();
         
        if (s == null || s.length() == 0) {
            return result;
        }
         
        int i = 0;
        while (i < s.length()) {
            int j = i;
            while (j < s.length() && Character.isDigit(s.charAt(j))) {
                //yes its good capture the digit (length)
                j++;
            }
            
            //num capture which is the length of string
            int num = Integer.parseInt(s.substring(i, j));

            //
            i = j;
            i++; // skip '#'

            if (num == 0) {
                result.add("");
            } else {
                result.add(s.substring(i, i + num));
            }
             
            i += num;
        }
         
        return result; //[hey, what]
    }

    public static void main(String args[]){
        List<String> lst = new ArrayList<String>();
        lst.add("hey");
        lst.add("what");
        String encode = encode(lst);
        System.out.println(encode);
        System.out.println(decode(encode));
    }
}