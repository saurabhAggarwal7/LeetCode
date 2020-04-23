/**
 * 158 LeetCode Java: Read N Characters Given Read4 II – Call multiple times Add
 * to List QuestionEditorial Solution – Hard
 * 
 * Problem: The API: int read4(char *buf) reads 4 characters at a time from a
 * file.
 * 
 * The return value is the actual number of characters read. For example, it
 * returns 3 if there is only 3 characters left in the file.
 * 
 * By using the read4 API, implement the function int read(char *buf, int n)
 * that reads n characters from the file.
 * 
 * Note: The read function may be called multiple times. Thoughts The difference
 * between this question and the first version is that the read() function will
 * be called multiple times. The trouble here will be as the following example
 * if using the first version solution: file: “abcdefg” read(3) read(2) read(2)
 * should be “abc” “de” “fg” but using first version solution it will print
 * “abc” “ef” “”
 * 
 * This is because when you use read4() to read, the pointer to read file has
 * already moved to “e” after the first call of read4(). So it’s not correct any
 * more.
 * 
 * In order to solve, we need to persist the characters that has been already
 * read by using read4 but it’s not put into the result of read().
 * 
 * In the solution below, I am using a buf4[] to store the characters read by
 * using read4 and also a buf4Size and buf4Index to keep track of the size of
 * the buf4 and the next character to use in buf4[].
 * https://cheonhyangzhang.wordpress.com/2016/12/22/158-leetcode-java-read-n-characters-given-read4-ii-call-multiple-times-add-to-list-questioneditorial-solution-hard/
 * 
 */

 /////NOTE for ease of undersatnding I have considered the inpiut parameter has the all the charaters and discarded the Destination Buffer thing

class Read4_v2 {
    int read4(char[] buf) {
        // returns only 4 chars at a time from file
        return 4;
    }
}

class Read_from_Read4_v2 extends Read4_v2 {
    /**
     * 
     * @param buf Destination Buffer
     * @param n   NUMBER OF CHARACTERS TO BE READ CAN BE MORE THAN 4
     * @return ACTUAL CHARACTERS READ
     */
    int read(char[] input_buffer, int n) {
        int buffer_size = 4; // can read only 4 from read4 at a time
        int buffer_index = 4; // current pointer in File<>

        // character array oif 4 beacuse the sizd of each batch is 4:
        char[] result_buffer = new char[buffer_size];

        int i = 0;

        /**
         * TRICK::: we need to persist the characters that has been already read by
         * using read4 but it’s not put into the result of read().
         */

        // n can be 2, 3, 4, 1 etc..
        // reas(2) read (1) read(2)
        while (i < n) {

            // initially both are 4
            if (buffer_index >= buffer_size) {

                // get new buffer size(as per the number of chrcters being read from read4)
                buffer_size = read4(input_buffer);

                // reset the buffer index: because we dont know how many to take even though we read 4
                buffer_index = 0;

                // read ends:
                if (buffer_size == 0)
                    break;

            } // end of if statement for buffer and buffer size

            result_buffer[i] = input_buffer[buffer_index];
            buffer_index++;
            i++;

        }
        return i;
    }

}
