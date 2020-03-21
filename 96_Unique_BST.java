/*Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

Example:

Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3*/


//Using N find the total number of unique trees that are possible to create

//On(n2) solution:
class Solution {
    public static int number_Trees_brute(int n){
        int arr[] = new int[n+1];
        arr[0] = 1;
        arr[1] = 1;
        for(int i=2;i<=n;i++){
            for(int j=0; j<i; j++){
                arr[i] += arr[j] * arr[i-j-1];
            }
        }
        return arr[n];
    }

    public static void main(String args[]){
        int val = number_Trees_brute(3);
        System.out.println(val);
    }
}

//Fast Solution is Catalan Number Solution : O(n)
//https://www.geeksforgeeks.org/program-nth-catalan-number/