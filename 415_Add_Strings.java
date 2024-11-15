/**
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.

 */

 /**
  * IMPORTANT EXPLAINIATION:
  Complexity Analysis
Time Complexity: `O(m + n)` (Average Case) and `O(m + n)` (Worst Case) where `m` and `n` are the total number of characters in the first and second input respectively. The algorithm evaluate each character for potential carry.

Auxiliary Space: `O(m + n)` space is used where `m` and `n` are the total number of characters in the first and second input respectively. Converting both input to character array required extra space.

Algorithm
Approach: Iterative

The while loop will run as long as there are characters left in one of the strings or when there is a carry in remaining. Starting from right to left, each character is converted to integer by the mean of offsetting its ASCII value. If the shorter string is exhausted first, the value will be forced to `0` as default from there onwards. Sum for that particular position is conveniently calculated and a modulus of `10` will extract the digit portion in case the sum is bigger than 10. Carry in is extracted by flooring the number after division by `10`. StringBuilder is used due to its efficiently in inserting character to existing StringBuilder object. If normal String is used then each insertion by + operation will have to copy over the immutable String object which is highly inefficient.
  */

class add_strings_method {

    static String addStrings(String num1, String num2) {
        int carry = 0;

        //Traverse from backwards to take care of carry
        int i = num1.length() - 1, j = num2.length() - 1;

        //StringBuilder:: is used due to its efficiently in inserting character to existing StringBuilder object. If normal String is used then each insertion by + operation will have to copy over the immutable String object which is highly inefficient.
        StringBuilder sb = new StringBuilder();

        while (i >= 0 || j >= 0) {
            int n1 = 0, n2 = 0;

            if (i >= 0) {
                //Starting from right to left, each character is converted to integer by the mean of offsetting its ASCII value
                n1 = num1.charAt(i) - '0';
            }

            if (j >= 0) {
                //Starting from right to left, each character is converted to integer by the mean of offsetting its ASCII value
                n2 = num2.charAt(j) - '0'; 
            }

            //Sum for that particular position is conveniently calculated and a modulus of `10` will extract the digit portion in case the sum is bigger than 10. Carry in is extracted by flooring the number after division by `10`
            int sum = n1 + n2 + carry;
            carry = sum / 10;
            sb.append(sum % 10);
            i--;
            j--;
        }
        
        if (carry != 0) {
            sb.append(carry);
        }
        
        //285 --> 582 reversed becasuse of for loop going from back to first to take care of carry as well
        return sb.reverse().toString(); 
    }

    public static void main(String args[]){
        System.out.println(add_strings_method.addStrings("123", "459"));
    }
}