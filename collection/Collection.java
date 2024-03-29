package collection;

public interface Collection<E> {

    /**
     * 返回集合大小，为什么是int
     */
    int size();

    boolean isEmpty();

    boolean contains(Object o);

    /**
     * 提供集合转换为数组的方式
     * @return
     */
    Object[] toArray();

    boolean add(E e);
    boolean remove(Object o);

}
