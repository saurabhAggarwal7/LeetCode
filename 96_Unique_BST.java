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

//Realted Questions:
//1) Count the number of expressions containing n pairs of parentheses which are correctly matched. For n = 3, possible expressions are ((())), ()(()), ()()(), (())(), (()()).
//2) 2) Count the number of possible Binary Search Trees with n keys
//3) 3) Count the number of full binary trees (A rooted binary tree is full if every vertex has either two children or no children) with n+1 leaves.

//All have same spl number series as: 
//n = 0, 1, 2, 3, … are 1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862,

//Fast Solution is Catalan Number Solution : O(n)
//https://www.geeksforgeeks.org/program-nth-catalan-number/
//The first few Catalan numbers for n = 0, 1, 2, 3, … are 1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862,
//Sum from i=0 to n {(C[i] * C[n-i])}
//For n=3, ans= 5
//C[0]*C[3] + C[1]*C[2] + C[2]*C[1]

//On(n2) solution:
class Solution {
    public static int number_Trees_DP(int n){
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

    public static int number_trees_catalan(int n){
        int a= 2*n; //6
        long res= 1; //1
        for (int i=0;i<n;i++){
            res= res*(a-i); //i=0, res=1*(6-0)=6 || i=1, res=6*(6-1)=30, || i=2, res= 15*(6-2)=60
            res = res/ (i+1); // i=0, 6/(0+1)=6 || i=1, res = 30/(1+1)=15 || i=2, res=60/(2+1)=20
        }
        long ans = res/(n+1); //res=20, n=3, 20/3+1 = (5) 
        return (int)ans;
    }

    //Best:
    public static int catalan_count_Trees(int n) { 
        int res = 0; 
          
        // Base case 
        if (n <= 1) { 
            return 1; 
        } 
        for (int i = 0; i < n; i++) { 
            res += catalan_count_Trees(i) * catalan_count_Trees(n - i - 1); 
        } 
        return res; 
    } 


    public static void main(String args[]){
        int val = number_Trees_DP(3);
        System.out.println(val);
        int val_2 = number_trees_catalan(3);
        System.out.println(val_2);

        //using Recursion:
        int val_3 = catalan_count_Trees(3);
        System.out.println(val_3);
    }
}



