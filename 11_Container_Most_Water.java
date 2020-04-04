/*
Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.
*/

/*Input: [1,8,6,2,5,4,8,3,7]
Output: 49*/

class Container_Most_Water{

    //TWO Pointer approach one from right and other from left:
    static int get_max_area(int [] height){
        int maxArea = 0;
        int l=0;
        int r=height.length -1;

        while(l< r){
            //Area is height * indexes differences means L*B
            //also note two pointers so Left and Right, one height can be min and other can be biggere so take the min of the two else the water will spill out
            maxArea = Math.max(maxArea, Math.min(height[l], height[r]) * (r-l));
            
            //means good we want right to be bigger and also find left end to be bigger so move ahead to find its counter big end
            if(height[l] < height[r]){
                l++;
            } else{
                r--;
            }
        }
        return maxArea;
    } 
    public static void main (String args[]){
        int[] height = {1,8,6,2,5,4,8,3,7};
        get_max_area(height);
    }
}



