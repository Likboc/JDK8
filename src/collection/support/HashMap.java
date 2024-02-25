package collection.support;

/**
 * 基于数组 + 链表/TreeNode 生成经典 map结构
 * 解决hash冲突方法：
 * 1.链表
 * 2.红黑树
 * 扩容机制省略。
 * @param <K>
 * @param <V>
 */
public class HashMap<K,V> {

    /**
     * bucket is a good name
     */
    private Node<K,V>[] table;

    static class Node<K,V> {
        final K key;
        final int hash;
        V value;
        Node<K,V> next;
        Node(int hash,K key,V value,Node<K,V> next) {
            this.key = key;
            this.hash = key.hashCode();
            this.value = value;
            this.next = next;
        }
    }



    public V get(K key) {
        int hash = key.hashCode() ^ (key.hashCode() >> 16);
        return null;
    }

    /**
     * 使用& 代替 % 获取索引
     * @param key
     * @param value
     */
    public void put(K key,V value) {
        int index = hash(key) & (table.length - 1);
        Node node = new Node<>(index,key,value,null);
        if(table[index] != null) {
            table[index].next = node;
        }else{
            table[index] = node;
        }
    }

    /**
     * 进行高低16位异或，使得hash分布更均匀
     * @param key
     * @return
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 :(h = key.hashCode()) ^ (h >>> 16);
    }

}
