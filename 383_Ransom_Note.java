/*Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true */

//TRICK: If it's same then 0 on Subtraction:
//Solution:

//1. ransome note is the substring, magzine is master string
//2. arr 128 because of the characters ascii codes form a-z characters
//3. loop through, arr[ch]++ means arr[a] repeated 3 times so the value at arr['a'] = 3 means arr[67] = 3
//4. similarly loop throiugh the same array again and -- the values form teh same array and if it becomes 0 becaus eof the same count hence its 0 else < 0 then negative


class ransom_note_match{
    static boolean canConstruct(String ransomNote, String magzine){
        char[] mc = magzine.toCharArray();
        char[] rc = ransomNote.toCharArray();
        int[] arr = new int[128];

        for(char ch: mc){
            arr[ch]++;
        }

        for(char ch : rc){
            if(--arr[ch]< 0)
                return false;
        }
        return true;
    }

    public static void main(String args[]){
        System.out.println(canConstruct("bcd", "zzzbcd"));
    }
}