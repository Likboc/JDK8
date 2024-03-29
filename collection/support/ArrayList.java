package collection.support;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 最常用List实现类
 * 1. 自动扩容
 * 2. 实现Iterator,提供 fail-fast 机制， 通过modcount 和 列表容量实现线程安全
 */
public class ArrayList<E> implements Iterable<E> {

    // 元素数量
    private int size;
    // 默认容量为 10
    private final static int DEFAULT_CAPACITY = 10;
    private final static double LOAD_FACTOR = 1.5;
    // 存储数据数组，使用transient 解决空元素问题
    transient Object[] elementData;
    // CAS版本号，实现fail-fast机制
    protected transient int modCount = 0;

    /**
     * 增加元素
     * @param element , 需要添加的元素
     * @param elementData , 源数据数组
     * @param size , 当前数组元素数量
     */
    public void add(E element,Object[] elementData,int size) {
        if(elementData.length == size) {
            // 扩容
            this.grow();
        }
        elementData[size] = element;
        this.size++;
    }

    /**
     * 扩容为原来2倍
     */
    public void grow() {
        // 1.5 倍扩容
        elementData = Arrays.copyOf(elementData,(int) (elementData.length * LOAD_FACTOR));
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        int expectedModcount = modCount;
        @Override
        public boolean hasNext() {
            return false;
        }

        /**
         * fail fast 机制实现
         * @return
         */
        @Override
        public E next(){
            if(modCount != expectedModcount ) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return null;
        }
    }
}
