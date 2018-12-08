package code;


import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following
 * operations: get and put.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
 * it should invalidate the least recently used item before inserting a new item.
 * <p>
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * <p>
 * Example:
 * <p>
 * LRUCache cache = new LRUCache( 2  //capacity  )
 * <p>
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // returns 1
 * cache.put(3,3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4,4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 */


/**
 * 要求：设计一个local cache
 * <p>
 * 思路：双向链表，一个元素put时，检查缓存大小，如果超过了，清除掉双向链表尾部节点
 * get时，把当前节点删除，然后set到链表头部，返回
 */
public class _146LRUCache {

    private Map<Integer, DoubleListNode> map = new HashMap<>();

    //双向链表头结点
    private DoubleListNode head;

    //双向链表尾结点
    private DoubleListNode end;

    //cache的大小
    private int capacity;

    //cache目前的数量
    private int len;

    public _146LRUCache(int capacity) {
        this.capacity = capacity;
        len = 0;
    }

    //查询，需要把被查询数据放到双向链表头部
    public int get(int key) {
        if (map.containsKey(key)) {
            DoubleListNode node = map.get(key);
            removeNode(node);
            setHead(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        //如果key存在的话，更新value，并且把节点放到双向链表头部
        if (map.containsKey(key)) {
            DoubleListNode oldNode = map.get(key);
            oldNode.val = value;
            removeNode(oldNode);
            setHead(oldNode);
        } else {
            DoubleListNode newNode = new DoubleListNode(key, value);
            //还不需要清除缓存，把新节点放到头部，len++
            if (len < capacity) {
                setHead(newNode);
                map.put(key, newNode);
                len++;
            } else {
                //缓存已满，把尾节点从map里面remove
                map.remove(end.key);
                //end变更为其上一个节点
                end = end.pre;
                if (null != end) {
                    end.next = null;
                }
                //新插入数据放到链表头节点
                setHead(newNode);
                map.put(key, newNode);
            }
        }
    }

    //解除当前节点和上下游的关系，同时如果这个节点是头结点或者尾节点的话，需要处理好对应的指向关系
    private void removeNode(DoubleListNode node) {
        if (null == node) {
            return;
        }
        DoubleListNode pre = node.pre;
        DoubleListNode next = node.next;
        if (null != pre) {
            pre.next = next;
        } else {
            this.head = next;
        }

        if (null != next) {
            next.pre = pre;
        } else {
            this.end = pre;
        }
    }

    //把一个节点置为头结点
    private void setHead(DoubleListNode node) {
        if (null == node) {
            return;
        }
        node.next = head;
        node.pre = null;
        if (null != head) {
            head.pre = node;
        }
        head = node;
        //如果尾节点为null，说明链表里面还只有一个元素，end也指向当前节点
        if (null == end) {
            end = node;
        }
    }

    private class DoubleListNode {

        int key;

        int val;

        DoubleListNode pre;

        DoubleListNode next;

        public DoubleListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
