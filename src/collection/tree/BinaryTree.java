package collection.tree;

/**
 * 基本BST，提供查询
 * @param <E>
 */
public class BinaryTree<E> {

    protected Node<E> root;

    /**
     * 确认是否含有该节点
     * @param node
     * @return
     */
    public boolean contains(Node node) {
        Node<E> root = this.root;
        while (root != null) {

            int compare = ((Comparable<E>)node.element).compareTo(root.element);

            if(node.element.equals(root.element)) {
                return true;
            } else if(compare > 0) {
                root = root.right;
            } else if(compare < 0) {
                root = root.left;
            }
        }
        return false;
    }

    public void add(Node<E> node) {
        if(contains(node)) {
            return;
        }
        Node<E> root = this.root;


    }

    protected static class Node<E>{

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
