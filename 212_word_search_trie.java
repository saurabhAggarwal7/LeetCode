import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class word_search_trie {
    static Set<String> result = new HashSet<>();
    static  List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        for (String word :words) {
            root.insert(root, word);
        }
        /**
         * [["a","a"]]
         * ["a"]
         */
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j <board[i].length; j++) {
                traversal(board, i, j, root);
            }
        }
        List<String> res = new ArrayList<>();
        res.addAll(result);
        return res;
    }

    static void traversal(char[][] board, int x, int y, TrieNode trie) {
        // access the border
        if (x < 0 ||  y < 0 || x > board.length - 1 || y > board[x].length - 1  || board[x][y] == '1') {
            return;
        }
        // pruning
        if (trie.children[board[x][y] - 'a'] == null) {
            return;
        }

        if (trie.children[board[x][y] - 'a'].idx_ch == board[x][y]) {
            System.out.println("CHARACTER" + board[x][y] + "ROW" + x + "COLUMN" + y);
        }

        if (trie.children[board[x][y] - 'a'].word.length() > 0) {
            result.add(trie.children[board[x][y] - 'a'].word);
        }
        char temp = board[x][y];
        board[x][y] = '1';
        traversal(board, x - 1, y, trie.children[temp - 'a']);
        traversal(board, x + 1, y, trie.children[temp - 'a']);
        traversal(board, x, y - 1, trie.children[temp - 'a']);
        traversal(board, x, y + 1, trie.children[temp - 'a']);
        // backtrack
        board[x][y] = temp;

    }

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = "";
        char idx_ch;
        String idx_pos = "";
        String idx_val = "";

        void insert(TrieNode root, String word) {
            TrieNode currNode = root;
            char[] chs = word.toCharArray();

            //String start_idx_char = "";
            //String start_idx_value = "";
            for (int i = 0; i < chs.length; i++) {
                int idx = chs[i] - 'a';
                if (currNode.children[idx] == null) {
                    currNode.children[idx] = new TrieNode();
                }
                currNode = currNode.children[idx];
                currNode.idx_ch = chs[i];
                currNode.idx_pos = currNode.idx_pos + chs[i];
                currNode.idx_val = currNode.idx_val + idx;
                //start_idx_char = start_idx_char + "|" + 
            }
            currNode.word = word;

        }
    }

    public static void main(String[] args) {
        char[][] board = {
            {'a', 'b', 'c', 'd'},
            {'p', 'r', 'a', 't'},
            {'k', 'i', 't', 'a'},
            {'a', 'n', 'd', 'y'}
        };

        String[] words = {"andy", "cat", "dog"};

        System.out.println(findWords(board, words));
	}

}