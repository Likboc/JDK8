package collection.tree;

/**
 * Binary Tree
 * @param <T>
 */
public class BinaryTree<E> {
    protected Node<E> root;



    protected static class Node<E> {

        public E element;
        public Node<E> left;
        public Node<E> right;
        public Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeafNode() {
            return this.left == null && this.right ==null;
        }
    }

}
