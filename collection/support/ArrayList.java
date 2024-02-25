package collection.support;

import java.util.Arrays;

/**
 * 特点 :
 * 1. 自动扩容
 * 2. fail-fast 机制
 * @param <E>
 */
public class ArrayList<E>  {
    public static  Object[] tmp = {};

    public static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    private int size;

    transient Object[] elementData;

    // 版本号
    protected transient int modCount = 0;

    private double LoadFactor = 0.75d;


    private int count = 0;
    // add
    public void add(E element) {
        tmp[count] = element;
        count++;
    }
    // 删除，涉及到元素的移动
    public void remove(E element) {
        for(int i = 0; i < count; i++) {
            if(tmp[i] == element) {
                while(i < count) {
                    tmp[i] = tmp[i + 1];
                    i++;
                }
                break;
            }
        }
    }
    public void update(E element) {
        for(int i = 0; i < count; i++) {
            if(tmp[i].equals(element)) {
                tmp[i] = element;
            }
        }
    }

    // 扩容
    public void grow() {
        Object[] old = tmp;
        Object[] newArr = Arrays.copyOf(tmp,size * 2);
        tmp = newArr;
        size = size << 2;
    }

    public boolean check() {
        if(tmp.length > Math.floor(size * LoadFactor)) {
            grow();
            return true;
        }else{
            return false;
        }
    }
}
