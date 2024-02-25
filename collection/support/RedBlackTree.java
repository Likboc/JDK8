package collection.support;

import lombok.Data;

@Data
public class RedBlackTree<T> {

    private final Node<T> root;
    private volatile boolean overrideMode = true;
    public RedBlackTree() {
        this.root = new Node<T>();
    }

    public <E> void insert(Node<E> node) {

    }

    public T addNode(Node<T> node) {
        return null;
    }

    // 查找方法
//    public T find(T value) {
//        Node<T> dataRoot = getRoot();
//        while(dataRoot != null) {
//            int cmp = dataRoot.getValue().compareTo(value);
//            if(cmp<0){
//                dataRoot = dataRoot.getRight();
//            }else if(cmp>0){
//                dataRoot = dataRoot.getLeft();
//            }else{
//                return dataRoot.getValue();
//            }
//        }
//        return null;
//    }


    @Data
    class Node<E> {
        E value;
        boolean isRight;
        Node<E> parent;
        Node<E> left;
        Node<E> right;
    }

}
