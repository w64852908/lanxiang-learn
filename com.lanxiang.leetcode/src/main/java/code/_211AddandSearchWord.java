package code;

/**
 * Created by lanjing on 2019-02-14.
 */

/**
 * Design a data structure that supports the following two operations:
 * <p>
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it
 * can represent any one letter.
 * <p>
 * Example:
 * <p>
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 * <p>
 * 要求：设计一个数据结构，能够实现词典的插入、查询功能，其中.可以代替任何字母
 * <p>
 * 思路：和题目208类似，不同的是search方法需要支持.匹配任何字母的逻辑
 * <p>
 * 一旦有了'.'，就需要查找所有的子树，只要有一个返回true，整个search函数就返回true，典型的DFS的问题，
 */
public class _211AddandSearchWord {

    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public _211AddandSearchWord() {
        this.root = new TrieNode();
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        if (null == word || "".equals(word)) {
            return;
        }
        TrieNode p = this.root;
        for (char c : word.toCharArray()) {
            if (null == p.child[c - 'a']) {
                p.child[c - 'a'] = new TrieNode();
            }
            p = p.child[c - 'a'];
        }
        p.word = word;
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return dfsSearch(word.toCharArray(), 0, this.root);
    }

    private boolean dfsSearch(char[] chs, int k, TrieNode node) {
        if (k == chs.length) {
            return null != node.word && !"".equals(node.word);
        }
        if (chs[k] != '.') {
            return node.child[chs[k] - 'a'] != null && dfsSearch(chs, k + 1, node.child[chs[k] - 'a']);
        } else {
            for (int i = 0; i < node.child.length; i++) {
                if (node.child[i] != null) {
                    if (dfsSearch(chs, k + 1, node.child[i])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    class TrieNode {

        public String word;

        public TrieNode[] child = new TrieNode[26];

    }

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
}
