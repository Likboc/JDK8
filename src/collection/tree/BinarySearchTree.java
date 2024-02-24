package collection.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : HuXuehao
 * @Description 二叉搜索树的java实现,以及相关的对外接口的实现
 * 二叉搜索树的特点：
 * 			任意一个节点node
 * 			node的左节点 leftNode
 * 			node的右节点 rightNode
 * 1. leftNode.val < node.val < rightNode.val
 *
 */
@SuppressWarnings("unchecked")
public class BinarySearchTree<E> {
    /*
     * size 是二叉搜索树的长度
     * root 是二叉搜索树的根节点 (root)
     * comparator 比较器对象，在该类中的compare(E e1, E e2)方法中使用
     * ArrayList<E> elements 用于存放遍历到的元素
     */
    private int size;
    protected Node<E> root;
    private java.util.Comparator<E> comparator;
    private ArrayList<E> elements = new ArrayList<>();

    /**
     * @Description 没有比较器说明 作为二叉树的节点 的对象已经实现了Comparable接口,
     * 并重写了compareTo()方法
     */
    public BinarySearchTree() {
        this(null); //调用有参的构造方法，并且参数是null
    }

    /**
     * @Description 说明你在创建对象的时候，就要给我传一个比较器，自定义比较的逻辑
     */
    public BinarySearchTree(java.util.Comparator<E> comparator) {
        this.comparator = comparator;
    }


    /**
     * @Description 获取二叉搜索树的大小(长度)
     */
    public int size() {
        return size;
    }

    /**
     * @Description 判断二叉搜索树是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @Description 清空二叉搜索树
     */
    public void clear() {
        root = null;
        size =0;
    }

    /**
     * @Description 向二叉搜索树中添加元素element.
     * 思路：【找到该元素的父节点应该是谁】！！，然后判断该节点是作为其父节点的左子树还是右子树存在。
     * 需要判断element是不是null;需要判断root节点是不是null;root是null那么说明
     * 添加的element是第一个元素.
     */
    public void add(E element) {
        elementNotNullCheck(element);

        //待插入的是第一个节点
        if(root == null) {
            root = CreatNewNode(element, null);
            size++;
            addAfterOpt(root); //看此方法的作用，此方法对于BinarySearchTree本身是没有任何作用的
            return;
        }

        //待插入的不是第一个节点
        Node<E> tempNode = root; //临时节点，用于寻找插入的位置
        Node<E> targetNodeParent = root; //记入待插入位置的父节点
        int cmp = 0; //比较的结果

        //如果说while之后，tempNode != null，说明我们已经找到element应在的位置。
        //并且在最后一次while开始执行时的tempNode就是执行完最后一次while时，tempNode
        //的父节点（仔细想一想！）
        while(tempNode != null) {
            //比较待插入元素与临时节点的大小
            cmp = compare(element, tempNode.element);
            targetNodeParent = tempNode; //更新父节点
            if(cmp < 0) {
                tempNode = tempNode.left;
            }else if(cmp >0 ){
                tempNode = tempNode.right;
            }else { //找到与待插入节点相等的节点时，覆盖原本的元素，并且退出add()
                tempNode.element = element;
                return;
            }
        }

        //创建待插入的节点insertedNode
        Node<E> insertedNode = CreatNewNode(element, targetNodeParent);

        //判断待插入的元素是其父节点的‘左节点’ 还是‘右节点’
        if(cmp > 0) {
            targetNodeParent.right = insertedNode;
        }else {
            targetNodeParent.left = insertedNode;
        }

        size++;
        addAfterOpt(insertedNode);//看此方法的作用，此方法对于BinarySearchTree本身是没有任何作用的
    }

    /**
     * @Description 该方法时给继承此类的VALTree类使用的，因为AVLTre需要在每次添加完成之后
     * 使二叉树回到平衡.
     * @param node:是新添加入的节点
     */
    protected void addAfterOpt(Node<E> node) { }


    /**
     * @Description 该方法时给继承此类的VALTree类使用的，因为AVLTre需要在每次删除完成之后
     * 使二叉树回到平衡.
     * @param node:是真正被删除的节点
     */
    protected void removeAfterOpt(Node<E> node) { }


    /**
     * @Description 创建一个新节点
     * 如果说，其他的二叉树（A）继承了该类，那么A中的Node可能会有其他的一些元素和方法，所以A新创建的node
     * 就不能是该类中的node，要是A自己的node。所以只要A重写该方法，返回自己的node就可以了【因为该方法的返回值是Node<E>,
     * 所以A重写后的方法的返回值也是Node<E>,因此A自己的ANode<E>需要继承该类中的Node，这样A即使返回自己的ANode<E>也不会报错（用到的思想是'多态性'）】
     */
    protected Node<E> CreatNewNode(E element, Node<E> parentNode) {
        return new Node<>(element, parentNode);
    }

    /**
     * @Description 移除二叉搜索树的element元素
     * 思路：根据节点的度来判断怎么删除节点（BinarySearchTree的度只分为0 1 2）
     * 1. 如果说待删除的节点的度为0，那么只需要判读待删除节点是其父节点的哪个节点，并将其
     *    父节点的响应的指针域置为null就OK了，不要忘记维护相关的父节点。
     * 2. 如果说待删除的节点的度为1，那么只需要判读待删除节点是其父节点的哪个节点，将待删除节点的父节点
     *    对应的指针域指向待删除节点的唯一一个子节点就OK了，不要忘记维护相关的父节点。
     * 3. 如果说待删除的节点的度为2，那么只需要获取待删除节点的前驱节点，将前驱节点的element赋给待删除
     *    节点，然后将前驱节点删除掉（一个节点的前驱节点的度只可能是0 或 1，因为当前节点的前驱结点是其左子树的最右节点，
     *    那么删除该节点，我们就可以直接调用1， 2中相关方法就OK了 ）就OK了。
     */
    public void remove(E element) {
        if(!contains(element)) return; //判断待删除元素是否包含在该树中

        int nodeDegrees = getNodeDegrees(element);
        Node<E> node = getNode(element);

        if(nodeDegrees == 0) { //度为0
            removeDegreeZero(node);
        }else if(nodeDegrees == 1) {//度为1
            removeDegreeOne(node);
        }else {//度为2
            removeDegreeTwo(node);
        }

    }

    /*
     * @Description 删除node节点，且node节点的度为0
     * 思路：判断是不是根节点，判断该节点是其父节点的那个节点，将父节点相应的节点置为null
     */
    private void removeDegreeZero(Node<E> node) {
        //判断是不是根节点
        if(node == root) {
            root = null;
        }else if(node == node.parent.left) {
            node.parent.left = null;
        }else {
            node.parent.right = null;
        }

        removeAfterOpt(node);
    }

    /*
     * @Description 删除node节点，且node节点的度为1
     * 思路：判断该节点是其父节点的那个节点，当前待删除节点的下一个非空节点的父节点置为当前节点的父节点
     * (维护相关节点的父节点),并将当前待删除父节点相应的节点置为当前待删除节点的下一个非空节点
     *
     * 注意：删除节点后，要维护相关节点的父节点
     */
    private void removeDegreeOne(Node<E> node) {
        //判断是不是根节点
        if(node  == root) {
            if(node.left != null) {
                //让root的左节点取代root
                node.left.parent = null;
                root = node.left;
            }else {
                node.right.parent = null;
                root = node.right;
            }
        }else if(node == node.parent.left) { //该节点是其父节点的左节点
            //判断该节点的下一个非空节点是左还是右
            if(node.left != null) {
                //维护相关节点的父节点
                node.left.parent = node.parent;
                //将当前待删除父节点相应的节点置为当前待删除节点的下一个非空节点
                node.parent.left = node.left;

            }else {
                node.right.parent = node.parent;
                node.parent.left = node.right;
            }

        }else {//该节点是其父节点的右节点
            if(node.left != null) {
                node.left.parent = node.parent;
                node.parent.right = node.left;
            }else {
                node.right.parent = node.parent;
                node.parent.right = node.right;
            }
        }

        removeAfterOpt(node);
    }

    /*
     * @Description 删除node节点，且node节点的度为2
     * 思路：获取待删除节点的前驱节点，将前驱节点的值赋的element给待删除节点的element，
     *      删除待删除节点的前驱节点；前驱节点的度要么为0要么为1，所以在删除前驱节点是可以直接使用
     *      已经写好的removeDegreeZero()、removeDegreeOne()。
     */
    private void removeDegreeTwo(Node<E> node) {
        //获取当前元素的前驱节点
        Node<E> preNode = getPreNode(node);
        //获取前驱节点的度
        int preNodeDegrees = getNodeDegrees(preNode.element);

        //将被删除节点的element换成前驱节点的element
        node.element = preNode.element;

        //删除前驱节点 (前驱节点的度要么为0，要么为1)
        if(preNodeDegrees == 0) { //度为0
            removeDegreeZero(preNode);
        }else {//度为1
            removeDegreeOne(preNode);
        }

        removeAfterOpt(node);

    }

    /**
     * @Description 元素element响应节点的度
     */
    private int getNodeDegrees(E element) {
        Node<E> node = getNode(element);
        if(node.left != null && node.right != null) {
            return 2;
        }else if(node.left == null && node.right == null) {
            return 1;
        }
        return 0;
    }

    /**
     * @Description 元素element是否包含在二叉搜索树中
     * 其实就是通过二叉搜索树的特征来寻找目标节点，分为以下的四种情况
     * 1. element和当前的Node节点的element元素相等
     * 2. element比当前的Node节点的element元素大，且当前节点的右边不为空
     *    那么就将当前节点的右节点作为新的当前的节点
     * 3. element比当前的Node节点的element元素小，且当前节点的左边不为空
     *    那么就将当前节点的左节点作为新的当前的节点
     * 4. 如果不满足上边的1,2,3；那么就直接报错就OK了
     */
    public boolean contains(E element) {
        Node<E> tempNode = root;

        while(true) {
            //判断element与当前节点element的大小
            int compare = compare(element, tempNode.element);
            if(compare == 0) { //相等
                return true;
            }else if(compare > 0 && tempNode.right != null){
                //element大，且当前节点存在右子树
                tempNode = tempNode.right;
            }else if(compare < 0 && tempNode.left != null){
                //element小，且当前节点存在左子树
                tempNode = tempNode.left;
            }else {
                //element大，且当前节点不存在右子树  或 element小，且当前节点不存在左子树
                return false;
            }
        }
    }

    /**
     * @Description 获取二叉所搜树的高度
     */
    public int getTreeHeight() {
        return getNodeHeight(root);
    }

    /**
     * @Description 获取二叉所搜树某个节点的高度 (非递归:借助 非递归实现层序遍历的思想)
     * 思路:通过观察层序遍历我们可以发现，当一层中的节点被遍历完成之后，此时队列中
     *     存放的都是下一层的节点。
     *     我们可以通过这个规律设计一个算法：使用一个变量height来表示节点的高度，
     *     使用另一个变量levelSize表示当前层数的节点数。每遍历一个元素，levelSize--，
     *     当levelSize等于0时，height++ ，并将队列中的元素的数量重新赋值给levelsize.
     *
     * 注意：通常的层序遍历是从root节点遍历的，所以入栈的第一个元素是root既获取的高度是root
     *     的高度，也就时整个树的高度，但是我们这个方法是为了获取到任意节点的高度，所以第一个入
     *     栈的就是getNodeHeight(Node<E> node)方法中的node，这点一定要注意！
     */
    public int getNodeHeight(Node<E> node) {
        if(node == null) return 0;
        int height = 0; //默认高度

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node); //node入栈
        int leveSize = queue.size();//leveSize，目标节点独立成为一层

        while(queue.size() != 0) {
            Node<E> visitedNode = queue.poll();//出队即访问
            leveSize--; //当前层数剩余节点数

            if(visitedNode.left != null) {
                queue.offer(visitedNode.left);
            }
            if(visitedNode.right != null) {
                queue.offer(visitedNode.right);
            }

            //当前层数遍历完成
            if(leveSize == 0) {
                height++; //节点的高度+1
                //将下一层的节点数赋给 levelSize
                leveSize = queue.size();

            }
        }

        return height;
    }

    /**
     * @Description 获取二叉搜索树某个节点的高度 (递归)
     * 思路：利用层序遍历。
     * 1. 如果当前的节点为null，那么当前节点的高度为0
     * 2. 当前节点的高度就是 = max(当前节点的左节点的高度，当前节点的右节点的高度) + 1
     */
    public int getHeight(Node<E> node) {
        if(node == null) return 0;
        return Math.max(getHeight(node.left), getHeight(root.right)) + 1;
    }

    /**
     * @Description 判断二叉所搜树是不是完全二叉树
     * 思路：【完全二叉树的判定】
     * 1.如果当前节点存在右子树，不存在左子树，那么说明不是完全二叉树
     * 2.如果当前节点存在左子树和右子树，那么说明它最起码不违法
     * 3.当前的节点没有子树，或者只有左子树，那么其后面的节点应该都是叶子结点，否则不是完全二叉树
     */
    public boolean isCompleteBinaryTree() {
        if(root == null) return false;
        boolean yezi = false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while(queue.size() != 0) {
            Node<E> visitedNode = queue.poll();//出队即访问

            //如果当前应该是叶子结点，但是它却不是叶子结点，那么返回false
            if(yezi == true && !visitedNode.isYezi()) return false;

            if(visitedNode.left != null) {
                queue.offer(visitedNode.left);
            }else if(visitedNode.right != null){
                //当前节点存在右子树，不存在左子树
                return false;
            }

            if(visitedNode.right != null){
                queue.offer(visitedNode.left);

            }else {
                //当前节点只有左子树，或者当前的节点没有子树;那么后面的节点（下一个）
                //应该是叶子。
                yezi = true;
            }
        }

        return true;
    }

    /**
     * @Description 二叉搜索树的前序遍历
     * ArrayList<E> elements 用于存放遍历到的元素
     */
    public ArrayList<E> preorderTraversal()  {
        canTraversal();
        elements.clear(); //清空elements集合中的元素

        preorderTraversal(root, elements);
        return elements;
    }
    private void preorderTraversal(Node<E> node, ArrayList<E> elements) {
        if(node == null) return;

        elements.add(node.element); //将遍历的元素添加到elements中
        preorderTraversal(node.left,elements);
        preorderTraversal(node.right,elements);
    }

    /**
     * @Description 二叉搜索树的中序遍历
     * ArrayList<E> elements 用于存放遍历到的元素
     */
    public ArrayList<E> inorderTraversal()  {
        canTraversal();
        elements.clear(); //清空elements集合中的元素

        inorderTraversal(root, elements);
        return elements;
    }
    private void inorderTraversal(Node<E> node, ArrayList<E> elements) {
        if(node == null) return;

        inorderTraversal(node.left, elements);
        elements.add(node.element); //将遍历的元素添加到elements中
        inorderTraversal(node.right, elements);
    }

    /**
     * @Description 二叉搜索树的后序遍历
     * ArrayList<E> elements 用于存放遍历到的元素
     */
    public ArrayList<E> postorderTraversal()  {
        canTraversal();
        elements.clear(); //清空elements集合中的元素

        postorderTraversal(root, elements);
        return elements;
    }
    private void postorderTraversal(Node<E> node, ArrayList<E> elements) {
        if(node == null) return;

        postorderTraversal(node.left, elements);
        postorderTraversal(node.right, elements);
        elements.add(node.element); //将遍历的元素添加到elements中
    }

    /**
     * @Description 二叉搜索树的层序遍历 (借助队列实现)
     * ArrayList<E> elements 用于存放遍历到的元素
     */
    public ArrayList<E> levelorderTraversal()  {
        canTraversal();
        elements.clear(); //清空elements集合中的元素
        levelorderTraversal(root, elements);
        return elements;
    }
    private void levelorderTraversal(Node<E> node, ArrayList<E> elements) {
        Queue<Node<E>> queue = new LinkedList<>();
        //将根节点入队
        queue.offer(node);

        while(queue.size() != 0) {
            Node<E> visitedNode = queue.poll();//出队即访问
            elements.add(visitedNode.element); //将遍历的元素添加到elements中

            if(visitedNode.left != null) {
                queue.offer(visitedNode.left);
            }
            if(visitedNode.right != null) {
                queue.offer(visitedNode.right);
            }
        }
    }

    /**
     * @Description 二叉搜索树的层序遍历 (借助队列实现)：用户自定义操作逻辑
     * 操作逻辑在用户的Visitor的匿名实现类中自定义
     *
     * 这里我就只写了levelorder的自定义操作逻辑，其他的三种遍历并没有定义操作逻辑，而是通过
     * 返回遍历到的元素让用户自己处理
     */
    public void levelorder(Visitor<E> v) {
        canTraversal();
        if(v == null) return;
        levelorder(root, v);
    }
    private void levelorder(Node<E> node ,Visitor<E> v) {
        Queue<Node<E>> queue = new LinkedList<>();
        //将根节点入队
        queue.offer(node);

        while(queue.size() != 0) {
            Node<E> visitedNode = queue.poll();//出队即访问、
            //将元素传给Visitor中的visit方法，具体怎么使用，需要用户在调用该遍历方法时
            //传递的匿名抽象实现类，操作逻辑由该类中的visit方法来实现
            if(v.stop) return; //检测到返回值是true，停止
            v.stop = v.visit(visitedNode.element); //将返回值保存到v中的stop

            if(visitedNode.left != null) {
                queue.offer(visitedNode.left);
            }
            if(visitedNode.right != null) {
                queue.offer(visitedNode.right);
            }
        }
    }

    /**
     * @Description 获取某个元素的前驱元素
     */
    public E getPreElement(E element) {
        if(!contains(element)) return null;
        Node<E> node = getNode(element);

        //getPreNode(node).element == element 表示没有前驱节点
        return getPreNode(node).element == element? null : getPreNode(node).element;
    }
    /**
     * @Description  获取某个元素的前驱节点，二叉搜索树中node的前驱节点就是
     * node的左子树中的最大节点
     */
    private Node<E> getPreNode(Node<E> node){
        if(node.left == null) return node;
        Node<E> preNode = node.left;

        while(preNode.right != null) {
            preNode = preNode.right;
        }
        return preNode;

    }

    /**
     * @Description 获取某个元素的后继元素
     */
    public E getPostElement(E element) {
        if(!contains(element)) return null;
        Node<E> node = getNode(element);

        return getPostElement(node).element == element? null : getPostElement(node).element;
    }
    /**
     * @Description  获取某个元素的后继节点，二叉搜索树中node的后继节点就是
     * node的右子树中的最小节点
     */
    private Node<E> getPostElement(Node<E> node){
        if(node.right == null) return node;
        Node<E> postNode = node.right;

        while(postNode.left != null) {
            postNode = postNode.left;
        }
        return postNode;

    }

    /**
     * @Description 获取某个元素对应的节点（借助层序遍历）
     */
    private Node<E> getNode(E element){
        Node<E> targetNode = null;
        Queue<Node<E>> queue = new LinkedList<>();
        //将根节点入队
        queue.offer(root);

        while(queue.size() != 0) {
            Node<E> visitedNode = queue.poll();//出队即访问
            if(visitedNode.element == element) {
                targetNode = visitedNode;
                break;
            }

            if(visitedNode.left != null) {
                queue.offer(visitedNode.left);
            }
            if(visitedNode.right != null) {
                queue.offer(visitedNode.right);
            }
        }
        return targetNode;
    }

    /**
     * @throws Exception ： BinarySearchTree must not be null！
     * @Description 判断二叉搜索树是否可以被遍历
     */
    private void canTraversal() {
        if(isEmpty()) {
            try {
                throw new Exception("BinarySearchTree must not be null！");
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1); //结束程序的执行
            }
        }
    }

    /**
     * @Description 比较函数
     * 如果返回值是0，那么e1 = e2; 如果返回值是小于0，那么e1 < e2;
     * 如果返回值是大于0，那么e1 > e2;
     */
    private int compare(E e1, E e2) {
        //如果用户传了比较器Comparator，那么就是用用户的比较逻辑
        if(comparator != null) {
            return comparator.compare(e1, e2);
        }

        /*
         * 如果说用户没有传比较器，那么说明用户自己的对象是实现了Comparable接口的并且
         * 自己已经写好了compareTo(E e)中的逻辑,那么我就直接使用用户自己写的比较逻辑，
         * 如果说用户没有实现Comparable结果，那么java自己就会报错。
         * (Comparable<E>)e1 :强制将e1转换成实现了Comparable的对象
         */
        return ((Comparable<E>)e1).compareTo(e2);
    }

    /**
     * @Description null元素检验
     */
    private void elementNotNullCheck(E element) {
        if(element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    /**
     * @Description 一个静态抽象类 Visitor
     */
    public static abstract class Visitor<E>{
        boolean stop; //用于判断是否需要终止遍历
        //返回值默认是false，表示不终止遍历
        abstract boolean visit(E element);
    }

    /**
     * @Description 内部节点类Node,一定是要protected，因为Node<E>可能
     * 会被继承
     */
    protected static class Node<E>{
        public E element;
        public Node<E> left;
        public Node<E> right;
        public Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isYezi() {
            return this.left == null && this.right ==null;
        }
    }
}

