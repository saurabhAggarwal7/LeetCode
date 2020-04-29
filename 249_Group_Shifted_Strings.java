/**
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], Return:
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
Note: For the return value, each inner list's elements must follow the lexicographic order.
things to consider: 1 does each group allow duplicates, such as ["a", "a"]. 2 how to calculate unique hash key.
For Java to do a mod operation, you need to do (26 + a)%26, if a == -25, then without 26+, you get a%26 == -25.
 */

import java.util.*;
class Group_Shifted_Strings {

    public List<List<String>> groupStrings(String[] strings) {
        Map<String,List<String>> map = new HashMap<>();

        for(String s : strings){
            String hash = getHash(s);
            if(map.containsKey(hash)){
                map.get(hash).add(s);
            }else{
                List<String> l = new ArrayList<>();
                l.add(s);
                map.put(hash, l);
            }
        }

        /**
         * MAIN FORMUAL FOR GROUPING THESE VALUES AS PER KEYS--- sb.append((s.charAt(i) - s.charAt(0) + 26) % 26);
         * map {key} 0.1.2 map {value}-> ["abc, bcd, xyz"]
         * 0.2.4.5 {key} map {value}-> ["acef"]
         * 0.25 {key} {value}-> ["az", "ba"]
         * 0. {key} {value}-> ['a', 'z']
         */

        return new ArrayList<List<String>>(map.values());
    }

    String getHash(String s){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< s.length(); i++){
            sb.append((s.charAt(i) - s.charAt(0) + 26) % 26);
            sb.append('.');// to make sure there is no overlap.
        }

        return sb.toString();
    }
}

class implement_group_shifted{
    public static void main(String args[]){
        String[] strings= {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
        Group_Shifted_Strings obj = new Group_Shifted_Strings();
        System.out.println(obj.groupStrings(strings));
    }
}