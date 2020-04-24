/**
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
    ...
Example 1:

Input: 1
Output: "A"
Example 2:

Input: 28
Output: "AB"
Example 3:

Input: 701
Output: "ZY"
 */

class excel_column_name {
    
    static String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();

        //n= 30 result is AD
        //this is s astring so build a string based on different sectoons of n values
        //initially value of n is 30

        while(n>0){

            //itr-1: n= 30
            //itr-2: n= 1

            n--; //n=29 //n=0
            char value = (char)('A' + n % 26); //A+ 4 = D // value = 'A' + 0 = A
            result.insert(0, value); //D //A but we are inserting at the beginning of the string builder so it will be AD not DA
            n /= 26; //now n is 1 //n= 0 now so loop ends
        }

        return result.toString();
    }

    public static void main(String args[]){
        System.out.println(convertToTitle(30));
    }
}

