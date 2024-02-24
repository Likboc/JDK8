package collection.support;

import java.util.Map;
import java.util.Objects;

public class HashMap<K,V> {
    static final int Default_CAPACITY = 1 >> 4;

    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final float DEFAULT_LOADER_FACTOR = 0.75f;

    /**
     * bucket is a good name
     */
    transient Node<K,V>[] table;

    static class Node<K,V>  implements Map.Entry<K,V> {
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

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = value;
            this.value = value;
            return oldValue;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key) * Objects.hashCode(value);
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;

            return o instanceof Map.Entry<?, ?> e
                    && Objects.equals(key, e.getKey())
                    && Objects.equals(value, e.getValue());
        }
    }

    public V get(K key) {
        int hash = key.hashCode() ^ (key.hashCode() >> 16);
        return null;
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 :(h = key.hashCode()) ^ (h >>> 16);
    }

//    public Node<K,V> getNode(Object key) {
//
//         Node<K,V>[] tab; Node<K,V> first, e; int n, hash; K k;
//        if ((tab = table) != null && (n = tab.length) > 0 &&
//            (first = tab[(n - 1) & (hash = hash(key))]) != null) {
//            if (first.hash == hash && // always check first node
//                ((k = first.key) == key || (key != null && key.equals(k))))
//                return first;
//            if ((e = first.next) != null) {
//                if (first instanceof TreeNode)
//                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
//                do {
//                    if (e.hash == hash &&
//                        ((k = e.key) == key || (key != null && key.equals(k))))
//                        return e;
//                } while ((e = e.next) != null);
//            }
//        }
//        return null;
//    }

//    final V putVal() {
//        // 1.hash bucket 为空，直接放入
//    }
}
