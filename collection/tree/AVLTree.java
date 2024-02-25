package collection.tree;


import java.util.Comparator;


/**
 * @author : HuXuehao
 * @Description AVLTree（二叉平衡树）继承 BinarySearchTree（二叉搜索树）
AVLTree是平衡二叉树的一种，它的平衡条件是平衡因子的绝对值不大于1

AVLTree的特点：
1.AVLTree 是二叉搜索树
2.AVLTree的任意一个节点的平衡因子的绝对值不大于1
平衡因子：左子树的高度 - 右子树的高度
 */
@SuppressWarnings("unchecked")
public class AVLTree<E> extends BinarySearchTree<E> {
    /**
     * @Description 没有比较器说明 作为二叉树的节点 的对象已经实现了Comparable接口,
     * 并重写了compareTo()方法
     */
    public AVLTree() {
        this(null); //调用有参的构造方法，并且参数是null
    }

    /**
     * @Description 说明你在创建对象的时候，就要给我传一个比较器，自定义比较的逻辑
     */
    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    /*
     * 增加父节点导致二叉树失去平衡，而且失去平衡的节点一定是在node的parent节点到root节
     * 点的连线上，最坏的是导致node的所有的祖先节点失衡，使的整个二叉树恢复平衡的做法是：找
     * 到距离node的parent节点最近的一个失去平衡的节点，将其翻转，将会整个二叉树恢复平衡。
     */
    @Override
    protected void addAfterOpt(Node<E> node) {

        while((node = node.parent) != null) {
            if(isBalance(node)) {
                //更新高度
                updataNodeHeight(node);
            }else {
                //恢复平衡（该节点恢复平衡在之后，整个二叉树恢复平衡，停止遍历）
                recoverBalance(node);
                break;
            }
        }
    }

    /*
     * 该方法与addAfterOpt(Node)的唯一的区别就是不要“break”。
     * 因为node节点被删除之后，只会导致其父节点或祖先节点中的一个失去平衡，将失衡的节点进行翻转之后，失衡节点回到平衡，
     * 但是有可能性导致恢复平衡的节点上面的节点失去平衡，最坏的可能性会导致node节点的parents与
     * root节点连线上的所有的节点都会失去平衡，所以需要遍历到根节点，来判断相关节点是否失去平衡
     *
     * 注意：node被删除之后，调用的 removeAfterOpt(Node<E> node)，那么在该方法中我使用
     * node.parent还是node的parent吗？ 答案是肯定的，如果你去BinarySearchTree的remove()
     * 的方法中看一看node.parent一直没有被操作，被操作的是node.parent.left或node.parent.
     * right,所以node.parent一直还在。
     */
    @Override
    protected void removeAfterOpt(Node<E> node) {

        while((node = node.parent) != null) {
            if(isBalance(node)) {
                //更新高度
                updataNodeHeight(node);
            }else {
                //恢复平衡
                recoverBalance(node);
            }
        }
    }

    /**
     * @Description 创建一个新节点
     * 如果说，其他的二叉树（A）继承了该类，那么A中的Node可能会有其他的一些元素和方法，所以A新创建的node
     * 就不能是该类中的node，要是A自己的node。所以只要A重写该方法，返回自己的node就可以了【因为该方法的返回值是Node<E>,
     * 所以A重写后的方法的返回值也是Node<E>,因此A自己的ANode<E>需要继承该类中的Node，这样A即使返回自己的ANode<E>也不会报错（用到的思想是'多态性'）】
     *
     * 这里的AVLTree就是上面说的A
     */
    @Override
    protected Node<E> CreatNewNode(E element, Node<E> parentNode) {
        return new AVLNode<>(element, parentNode);
    }

    /**
     * @Description 恢复平衡
     * @param grandparent 不平衡且高度最低的节点
     */
    private void recoverBalance(Node<E> grandparent) {
        /* 对于不平衡节点grandparent而言，parent、node有以下特点
         * parent 插入元素的父节点 :grandparent的 较高 子树的根节点
         * node 插入元素节点 parent的 较高 子树的根节点
         */
        Node<E> parent  = ((AVLNode<E>)grandparent).tellerChild();
        Node<E> node  = ((AVLNode<E>)parent).tellerChild();

        //判断方向
        String dir = judgeDirection(grandparent,parent,node);

        //进行翻转
        if("LL".equals(dir)) {
            rotateRright(grandparent);

        }else if("RR".equals(dir)) {
            rotateLeft(grandparent);

        }else if("LR".equals(dir)) {
            rotateLeft(parent);
            rotateRright(grandparent);

        }else { //RL
            rotateRright(parent);
            rotateLeft(grandparent);

        }
    }

    /*
     * 对node节点进行右旋转
     */

    private void rotateRright(Node node) {
        Node<E> tempNode = node.left; //备份目前的node的左节点left

        node.left = tempNode.right; //将node是左指针指向node的左节点的右子树
        if(node.left != null) node.left.parent = node; //更新父节点

        tempNode.right = node; //将tempNode的右指针指向node
        tempNode.parent = node.parent; //更新父节点

        //取代node的位置
        if(node.parent.left == node) { //node是其父节点的left时
            node.parent.left = tempNode;
        }else if(node.parent.right == node){//node是其父节点的right时
            node.parent.right = tempNode;
        }else { //node是根节点（root）
            root = tempNode;
        }

        node.parent = tempNode; //更新node的父节点

        //更新高度
        updataNodeHeight(tempNode);
        updataNodeHeight(node);
    }

    /*
     * 对node节点进行左旋转
     */
    private void rotateLeft(Node node) {
        Node<E> tempNode = node.right; //备份目前的node的左节点left

        node.right = tempNode.left; //将node是左指针指向node的左节点的右子树
        if(node.right != null) node.right.parent = node; //更新父节点

        tempNode.left = node; //将tempNode的右指针指向node
        tempNode.parent = node.parent; //更新父节点

        //取代node的位置
        if(node.parent.left == node) { //node是其父节点的left时
            node.parent.left = tempNode;
        }else if(node.parent.right == node){//node是其父节点的right时
            node.parent.right = tempNode;
        }else { //node是根节点（root）
            root = tempNode;
        }

        node.parent = tempNode; //更新node的父节点

        //更新高度
        updataNodeHeight(tempNode);
        updataNodeHeight(node);
    }
    /**
     *
     * @Description 判断parent是grandparent的那个节点，node是parent的那个节点
     * @param grandparent 高度最低不平衡节点（插入元素的父节点的父节点）
     * @param parent 插入元素的父节点
     * @param node 插入元素节点
     * @return 判断的结果
     */
    private String judgeDirection(Node<E> grandparent, Node<E> parent, Node<E> node) {
        if(grandparent.left == parent && parent.left == node) return "LL";
        if(grandparent.right == parent && parent.right == node) return "RR";
        if(grandparent.left == parent && parent.right == node) return "LR";
        return "RL";
    }

    /**
     * @Description 更新节点的高度
     * @param node
     */
    private void updataNodeHeight(Node<E> node) {
        ((AVLNode<E>)node).updataSelfHeight();
    }

    /**
     * @Description 判断该节点是否平衡
     * @param node
     */
    private boolean isBalance(Node<E> node) {
        //abs 取绝对值
        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }

    //定义AVLTree特有的Node，并继承BinarySearchTree中的Node
    private class AVLNode<E> extends Node<E> {
        int height = 1; //每个节点被创建之后节点高度默认为1

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        //获取该节点的平衡因子
        public int balanceFactor() {
            int leftHeight = left == null? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null? 0 : ((AVLNode<E>)right).height;

            return leftHeight - rightHeight;
        }

        //更新当前节点的高度
        public void updataSelfHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            height = Math.max(leftHeight, rightHeight) + 1;
        }

        //返回当前节点比较高的子节点
        public Node<E> tellerChild(){
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            if(leftHeight > rightHeight) return left;
            if(leftHeight < rightHeight) return right;

            //如果我的左右子树高度相等，如果我本身是我父节点的左节点，那么就返回我的左节点，反之
            return this == this.parent.left? left : right;
        }
    }
}



