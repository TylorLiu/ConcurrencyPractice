package org.tylor.tutorial.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * Tylor 2020/12/11
 */
class LRUCache{

    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {}
        public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
    }
    Map<Integer,DLinkedNode> map;
    int capacity;
    DLinkedNode tail;
    DLinkedNode head;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap();
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.next = head;
    }

    public int get(int key) {
        DLinkedNode node = map.get(key);
        if(node==null)
            return -1;
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node =map.get(key);

        if(node ==null){
            if(map.size()>=capacity){
                removeEldestNode();
            }
            insertNode(new DLinkedNode(key,value));
        }else{
            node.value =value;
            map.put(key, node);
            moveToHead(node);
        }

    }

    void insertNode(DLinkedNode dNode){
        dNode.next = head.next;

        dNode.prev = head;
        dNode.next.prev =dNode;
        head.next = dNode;

        map.put(dNode.key, dNode);

    }
    void moveToHead(DLinkedNode dNode){
        dNode.prev.next = dNode.next;
        dNode.next.prev = dNode.prev;

        dNode.next = head.next;

        dNode.prev = head;
        dNode.next.prev =dNode;
        head.next = dNode;
    }

    void removeEldestNode(){
        DLinkedNode node = tail.prev;
        map.remove(node.key);
        tail.prev = node.prev;
        node.prev.next = tail;
    }

    public static void main(String[] args) {

//["LRUCache","put","put","put","put","get","get"]
//[[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]

        LRUCache cache = new LRUCache(2);
        cache.put(2,1);
        System.out.println(cache.map);
        cache.put(1,1);
        System.out.println(cache.map);
        cache.put(2,3);
        System.out.println(cache.map);
        cache.put(4,1);
        System.out.println(cache.map);
        cache.get(1);
        System.out.println(cache.map);
        cache.get(2);
        System.out.println(cache.map);


    }


}







// class LRUCache extends LinkedHashMap<Integer, Integer>{
//     int capacity;

//     public LRUCache(int capacity) {
//         super(capacity, 0.75f, true);
//         this.capacity = capacity;
//     }



//     @Override
//     protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest) {
//         return size()>capacity;
//     }
// }


// /**
//  * Your LRUCache object will be instantiated and called as such:
//  * LRUCache obj = new LRUCache(capacity);
//  * int param_1 = obj.get(key);
//  * obj.put(key,value);
//  */