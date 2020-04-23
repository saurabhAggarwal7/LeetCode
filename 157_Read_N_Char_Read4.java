/***
 * 
 * 
 * Given a file and assume that you can only read the file using a given method read4, implement a method to read n characters.
Method read4:
The API read4 reads 4 consecutive characters from the file, then writes those characters into the buffer array buf.
The return value is the number of actual characters read.
Note that read4() has its own file pointer, much like FILE *fp in C.
Definition of read4:

Parameter:  char[] buf

    Returns:    int
Note: buf[] is destination not source, the results from read4 will be copied to buf[]
Below is a high level example of how read4 works:
File file("abcdefghijk"); // File is "abcdefghijk", initially file pointer (fp) points to 'a'
char[] buf = new char[4]; // Create buffer with enough space to store characters
read4(buf); // read4 returns 4. Now buf = "abcd", fp points to 'e'
read4(buf); // read4 returns 4. Now buf = "efgh", fp points to 'i'
read4(buf); // read4 returns 3. Now buf = "ijk", fp points to end of file

Method read:
By using the read4 method, implement the method read that reads n characters from the file and store it in the buffer array buf. Consider that you cannotmanipulate the file directly.
The return value is the number of actual characters read.
Definition of read:
Parameters:	char[] buf, int n
    Returns:	int
Note: buf[] is destination not source, you will need to write the results to buf[]
Example 1:
Input: file = "abc", n = 4
Output: 3
Explanation: After calling your read method, buf should contain "abc". We read a total of 3 characters from the file, so return 3. Note that "abc" is the file's content, not buf. buf is the destination buffer that you will have to write the results to.
Example 2:
Input: file = "abcde", n = 5
Output: 5
Explanation: After calling your read method, buf should contain "abcde". We read a total of 5 characters from the file, so return 5.
Example 3:
Input: file = "abcdABCD1234", n = 12
Output: 12
Explanation: After calling your read method, buf should contain "abcdABCD1234". We read a total of 12 characters from the file, so return 12.
Example 4:
Input: file = "leetcode", n = 5
Output: 5
Explanation: After calling your read method, buf should contain "leetc". We read a total of 5 characters from the file, so return 5.
Note:
Consider that you cannot manipulate the file directly, the file is only accessible for read4 but not for read.
The read function will only be called once for each test case.
You may assume the destination buffer array, buf, is guaranteed to have enough space for storing n characters.
 */

/**
 * The read4 API is defined in the parent class Reader4. int read4(char[] buf);
 */

 /***
  * SOLUTION::::::
  We already have a n API which gives out 4 characters at a time after reading so we create a function read that read continously from read4 in the batches of 4
  Once the read is complete for one batch it checks for the number of characters read and it increments the bufferIndex as pr the number pf chatacters read
  Note that max 4characters can be read but when the cointent ends it can also read the left over 3 charcters depends upon %4
  *
  */

class Read4 {
    int read4(char[] buf) {
        // returns only 4 chars at a time from file
        return 4;
    }
}

class Read_from_Read4 extends Read4 {
    /**
     * 
     * @param buf Destination Buffer
     * @param n   NUMBER OF CHARACTERS TO BE READ CAN BE MORE THAN 4
     * @return ACTUAL CHARACTERS READ
     */
    int read(char[] input_buffer, int n) {
        int buffer_size = 4; // can read only 4 from read4 at a time
        int num_char_read = 0;
        int buffer_index = 0; // current pointer in File<>

        // character array oif 4 beacuse the sizd of each batch is 4:
        char[] result_buffer = new char[buffer_size];

        //call read4() method continously until it results 0 

        while ((num_char_read = read4(input_buffer)) > 0 && n > 0) {
            int num_chars = Math.min(num_char_read, buffer_size);

            for (int i = 0; i < num_chars && n > 0; i++) {
                result_buffer[buffer_index++] = input_buffer[i];
                n--;
            }

        }

        return buffer_index;
    }
}