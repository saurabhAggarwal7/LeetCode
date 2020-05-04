
/**
 * 
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)

 */

import java.util.*;

class NestedInteger {
    int val;
    List<Integer> val_list;

    // constructors
    public NestedInteger(int val) {
        this.val = val;
    }

    public NestedInteger(Integer[] arr) {
        this.val_list = Arrays.asList(arr);
    }

    // methods:
    public boolean isInteger() {
        if(this.val == (int)this.val){
            return true;
        }
        return false;
    }

    public int getInteger() {
        if(isInteger()){
            return this.val;
        }
        return -1;
    }

    public List<NestedInteger> getList() {
        ////////////////////////////////////////issue
        return null;

    }

}

class Nested_list_Weight_Sum {

    static int depthSum(List<NestedInteger> nestedList) {
        return helper(nestedList, 1);
    }

    static int helper(List<NestedInteger> nestedList, int depth) {
        if (nestedList == null || nestedList.size() == 0)
            return 0;

        int sum = 0;
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                sum += ni.getInteger() * depth;
            } else {
                sum += helper(ni.getList(), depth + 1);
            }
        }

        return sum;
    }

    public static void main(String args[]) {
        // [[1,1],2,[1,1]]
        List<NestedInteger> nestedList = new ArrayList<NestedInteger>();
        Integer[] arr1 = {1,1};
        Integer[] arr2 = {1, 1};
        nestedList.add(new NestedInteger(arr1));
        nestedList.add(new NestedInteger(2));
        nestedList.add(new NestedInteger(arr2));
        System.out.println(depthSum(nestedList));
    }
}