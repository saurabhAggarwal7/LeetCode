
/**
 * Given an integer, write a function to determine if it is a power of two.
 * 
 * Example 1:
 * 
 * Input: 1 Output: true Explanation: 20 = 1 Example 2:
 * 
 * Input: 16 Output: true Explanation: 24 = 16 Example 3:
 * 
 * Input: 218 Output: false
 * 
 */

 /**
  * SOLUTION:: Power of 2 means only one bit of n is '1', so use the trick n&(n-1)==0 to judge whether that is the case
  It's a bitwise operation between a number and its previous number. Only way this expression could ever be false is if n is a power of 2, so essentially you're verifying if it isn't a power of 2.

  It's figuring out if n is either 0 or an exact power of two.

It works because a binary power of two is of the form 1000...000 and subtracting one will give you 111...111. Then, when you AND those together, you get zero, such as with:

  1000 0000 0000 0000
&  111 1111 1111 1111
  ==== ==== ==== ====
= 0000 0000 0000 0000
Any non-power-of-two input value (other than zero) will not give you zero when you perform that operation.

For example, let's try all the 4-bit combinations:

     <----- binary ---->
 n      n    n-1   n&(n-1)
--   ----   ----   -------
 0   0000   0111    0000 *
 1   0001   0000    0000 *
 2   0010   0001    0000 *
 3   0011   0010    0010
 4   0100   0011    0000 *
 5   0101   0100    0100
 6   0110   0101    0100
 7   0111   0110    0110
 8   1000   0111    0000 *
 9   1001   1000    1000
10   1010   1001    1000
11   1011   1010    1010
12   1100   1011    1000
13   1101   1100    1100
14   1110   1101    1100
15   1111   1110    1110
You can see that only 0 and the powers of two (1, 2, 4 and 8) result in a 0000/false bit pattern, all others are non-zero or true.
  */

class Power_Of_Two {

    boolean isPowerOfTwo(int n) {
        return (n > 0 && ((n & (n - 1)) == 0));
    }
}
