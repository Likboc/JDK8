package collection.support;

/**
 * 特点 :
 * 1. 自动扩容
 * 2. fail-fast 机制
 * @param <E>
 */
public class ArrayList<E>  {
    public static final Object[] tmp = {};

    public static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    private int size;

    transient Object[] elementData;

    // 版本号
    protected transient int modCount = 0;

}
