
/**
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

Example:

Given n = 5, and version = 4 is the first bad version.

call isBadVersion(3) -> false
call isBadVersion(5) -> true
call isBadVersion(4) -> true

Then 4 is the first bad version. 
 */

 /**
  * Before this problem, I have always use

  mid = (start+end)) / 2;
To get the middle value, but this can caused OVERFLOW !

when start and end are all about INT_MAX , then (start+end) of course will be overflow !

To avoid the problem we can use

  mid =  start+(end-start)/2;
Here is the AC implementation

// Forward declaration of isBadVersion API.
bool isBadVersion(int version);
  */

class First_bad_version {

    static boolean isBadVersion(int version){
        //Given n = 5, and version = 4 is the first bad version.
        if(version == 4)
            return true;
        return false;
    }

    //SIMPLE SEARCH BY DIVIDE N CONQUEER
    static int firstBadVersion(int n) {
        int start = 1, end = n;
        while (start < end) {
            int mid = start + (end-start) / 2; //3
            if (!isBadVersion(mid)) 
                start = mid + 1; //start = 4 now
            else 
                end = mid;            
        }        
        return start;
    }

    public static void main(String args[]){
        System.out.println(firstBadVersion(5));
    }
}