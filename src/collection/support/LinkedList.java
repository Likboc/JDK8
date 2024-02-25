package collection.support;

import lombok.Data;

/**
 * LinkedList 是一个双向链表
 */
public class LinkedList<E>{

    Object[] list;
    @Data
    class Node<E> {
        Node<E> prev;
        Node<E> next;
        E ele;
    }
}
