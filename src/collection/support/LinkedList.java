package collection.support;

import lombok.Data;

/**
 * LinkedList 是一个双向链表,和ArrayList对比，查询性能较差，插入性能更强。
 */
public class LinkedList<E>{


    @Data
    class Node<E> {
        Node<E> prev;
        Node<E> next;
        E ele;
    }
}
