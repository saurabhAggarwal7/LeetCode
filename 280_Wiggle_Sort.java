/**
 * // Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

// For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
 */


 //nums[i-1] > nums[i] --ODD SWAP
 //nums[i-1] < nums[i] --EVEN SWAP

class Wiggle_Sort {
     void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
     
        for (int i = 1; i < nums.length; i++) {
            if (i % 2 == 1) {
                //odd:
                if (nums[i - 1] > nums[i]) {
                    //prev is more than current
                    nums= swap(nums, i - 1, i);
                }
            } else {
                //even:
                if (nums[i - 1] < nums[i]) {
                    //current is more than previous
                    nums = swap(nums, i - 1, i);
                }
            }
        }
        System.out.println(nums.toString());
    }

     int[] swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
        return nums;
    }

    
}

class wiggle_Sort_implement{
    public static void main(String args[]){
        int[] nums =  {3, 5, 2, 1, 6, 4};

        //result is nums only in place changed no extra space is consumed:
        Wiggle_Sort obj = new Wiggle_Sort();
        obj.wiggleSort(nums);
        
    }

}