
/**
 * Convert a non-negative integer to its english words representation. Given
 * input is guaranteed to be less than 231 - 1.
 * 
 * Example 1:
 * 
 * Input: 123 Output: "One Hundred Twenty Three" Example 2:
 * 
 * Input: 12345 Output: "Twelve Thousand Three Hundred Forty Five" Example 3:
 * 
 * Input: 1234567 Output: "One Million Two Hundred Thirty Four Thousand Five
 * Hundred Sixty Seven" Example 4:
 * 
 * Input: 1234567891 Output: "One Billion Two Hundred Thirty Four Million Five
 * Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 */

class integer_english_words {
    private final String[] LESS_THAN_20 = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
            "Nineteen" };
    private final String[] TENS = { "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty",
            "Ninety" };
    private final String[] THOUSANDS = { "", "Thousand", "Million", "Billion" };

    public String numberToWords(int num) {
        if (num == 0)
            return "Zero";

        int i = 0;
        String words = "";

        while (num > 0) {
            if (num % 1000 != 0)
                words = helper(num % 1000) + THOUSANDS[i] + " " + words;
            num /= 1000;
            i++;
        }

        return words.trim();
    }

    private String helper(int num) { 
        //891 //91 //1

        if (num == 0)
            return "";
        else if (num < 20)
            //itr-3: 1
            return LESS_THAN_20[num] + " ";
        else if (num < 100)
            //itr-2: 91
            return TENS[num / 10] + " " + helper(num % 10);
        else
            //itr-1: 891
            return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
    }
}

class integer_english_Words_implement{
    public static void main(String args[]){
        integer_english_words obj = new integer_english_words();
        obj.numberToWords(1234567891);
    }
}