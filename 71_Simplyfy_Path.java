/*Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.

In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level.

CONDITIONS---
Note that the returned canonical path must always begin with a slash /, 
and there must be only a single slash / between two directory names. 
The last directory name (if it exists) must not end with a trailing /. Also, the canonical path must be the shortest string representing the absolute path.

 

Example 1:

Input: "/home/"
Output: "/home"
Explanation: Note that there is no trailing slash after the last directory name.
Example 2:

Input: "/../"
Output: "/"
Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
Example 3:

Input: "/home//foo/"
Output: "/home/foo"
Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
Example 4:

Input: "/a/./b/../../c/"
Output: "/c"
Example 5:

Input: "/a/../../b/../c//.//"
Output: "/c"
Example 6:

Input: "/a//b////c/d//././/.."
Output: "/a/b/c" 

*/

//The main idea is to push to the stack every valid file name (not in {"",".",".."}), 
//popping only if there's smth to pop and we met "..". I don't feel like the code below needs any additional comments.

//Theory:
/*
It provides the support of resizable array and helps in restriction-free capacity, so to grow the array according to the usage.
Array deques prohibit the use of Null elements and do not accept any such elements.
Any concurrent access by multiple threads is not supported.
In the absence of external synchronization, Deque is not thread-safe.

add(element): Adds an element to the tail.
addFirst(element): Adds an element to the head.
addLast(element): Adds an element to the tail.
*/

import java.util.*;

class simplify_paths {
    static String simplify_paths(String path) {

        // Dequeue:
        Deque<String> stack = new LinkedList<>();
        Set<String> skip = new HashSet<>(Arrays.asList("..", ".", ""));

        // The dir contains --> "", a, b, c, d
        // but .. is encountered so we popped and removed d
        // Stack--> LIFO so stack {d, c, b, c} => pop => {c, b, a}
        for (String dir : path.split("/")) {
            if (dir.equals("..") && !stack.isEmpty())
                stack.pop();
            else if (!skip.contains(dir))
                stack.push(dir);
        }

        String res = "";
        for (String dir : stack)
            //dir = c
            //dir= b
            //dir = a

            //While adding we first added the dir then reult so we got opposite of stack.

            // res= /c
            // res=//b/c
            // res==/a/b/c
            res = "/" + dir + res;

        return res.isEmpty() ? "/" : res;
    }

    public static void main(String args[]) {
        System.out.println(simplify_paths("/a//b////c/d//././/.."));
    }
}