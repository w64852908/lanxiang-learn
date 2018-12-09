package code;

/**
 * Created by lanjing on 2018/12/9.
 */


/**
 * Implement a trie with insert, search, and startsWith methods.
 * <p>
 * Example:
 * <p>
 * Trie trie = new Trie();
 * <p>
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 * trie.search("app");     // returns true
 * Note:
 * <p>
 * You may assume that all inputs are consist of lowercase letters a-z.
 * All inputs are guaranteed to be non-empty strings.
 * <p>
 * <p>
 * 要求：实现一颗字典树
 * <p>
 * 思路：定义字典树中的节点，保存了一个TrieNode[26]的子节点数组，下标对应对应的字符-'a'
 * 插入：从根节点往下遍历子节点数组，如果要插入的字符为null，就初始化一个节点，放到数组上，继续遍历，遍历完成之后，在当前节点上存储对应的word
 * 查找：和插入类似，但是word中某个字符不在节点中的话，直接return false。如果遍历的所有路径都存在，则最后判断word是否equals
 * 前缀查找：查找prefix在树上的所有路径是否存在，如果都存在的话，说明存在此prefix的word。并不需要找到一个存在的word
 */
public class _208ImplementTriePrefixTree {

    private class TrieNode {
        //存储子节点
        TrieNode[] children = new TrieNode[26];
        //存储一条记录，对应一个单词
        String item = "";
    }

    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public _208ImplementTriePrefixTree() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        if (null == word || word.length() == 0) {
            return;
        }
        TrieNode p = this.root;
        char[] chars = word.toCharArray();
        for (char c : chars) {
            if (null == p.children[c - 'a']) {
                p.children[c - 'a'] = new TrieNode();
            }
            p = p.children[c - 'a'];
        }
        p.item = word;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        if (null == word || word.length() == 0) {
            return false;
        }
        TrieNode p = this.root;
        char[] chars = word.toCharArray();
        for (char c : chars) {
            if (null == p.children[c - 'a']) {
                return false;
            }
            p = p.children[c - 'a'];
        }
        //反过来equals，反之p的item为null
        return word.equals(p.item);
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        if (null == prefix || prefix.length() == 0) {
            return false;
        }
        TrieNode p = this.root;
        char[] chars = prefix.toCharArray();
        for (char c : chars) {
            if (null == p.children[c - 'a']) {
                return false;
            }
            p = p.children[c - 'a'];
        }
        return true;
    }

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
}
