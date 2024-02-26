package collection.support;

import java.util.Arrays;

/**
 * 特点 :
 * 1. 自动扩容
 * 2. fail-fast 机制
 * @param <E>
 */
public class ArrayList<E>  {

    // 元素数量，可充当指针用
    private int size;
    // 存储数据数组
    transient Object[] elementData;
    // CAS版本号
    protected transient int modCount = 0;

    /**
     * 增加元素
     * @param element
     * @param elementData
     * @param size
     */
    public void add(E element,Object[] elementData,int size) {
        if(elementData.length == size) {
            this.grow();
        }
        elementData[size] = element;
        this.size++;
    }

    /**
     * 删除元素
     * @param index
     */
    public void remove(int index) {
        for(int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
    }

    /**
     * 更新元素
     * @param element
     */
    public void update(E element,int index) {
        elementData[index] = element;
    }

    /**
     * 扩容为原来2倍
     */
    public void grow() {
        elementData = Arrays.copyOf(elementData,elementData.length * 2);
    }


}
