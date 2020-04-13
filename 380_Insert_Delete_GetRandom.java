import java.util.HashMap;

/*

Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();

*/

import java.util.*;

class new_ds_insert_delete_random {
    ArrayList<Integer> nums;
    HashMap<Integer, Integer> locs;
    // random in built class java:
    Random random = new Random();

    public new_ds_insert_delete_random() {
        nums = new ArrayList<Integer>();
        locs = new HashMap<Integer, Integer>();
    }

    // Method-1: INSERT:
    public boolean insert(int val) {
        if (locs.containsKey(val)) {
            return false;
        }
        // insert in both hashmap and array. note in hashmap the size is also inserted
        // in the map along with the value to be inserted

        // {key}-> actual value to be inserted
        // {value}-> size of the array
        locs.put(val, nums.size());
        nums.add(val);
        return true;
    }

    // METHOD-2: REMOVE:
    public boolean remove(int val) {
        if (!locs.containsKey(val)) {
            return false;
        }

        // get teh size of the array when it was inteserted or gets the position of this
        // element in array
        int loc = locs.get(val);

        if (loc < nums.size() - 1) {
            // not the last one than swap the last one with this val
            // SWAP Last and current one:
            int lastone = nums.get(nums.size() - 1);
            nums.set(loc, lastone);
            locs.put(lastone, loc);
        }

        // REMOVE FROM LAST NOW:
        locs.remove(val);
        nums.remove(nums.size() - 1);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        //get some random number from map {key}
        return nums.get(random.nextInt(nums.size()));
    }
}

class new_ds_insert_delete_random_display {

}