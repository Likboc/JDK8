package collection.support;

/**
 * 基于synchronized的线程安全类，读性能优于Vector
 */
public class CopyOnWriteArrayList<E> {

    private transient Object[] array;
    private Object Lock = new Object();

    public synchronized void add(E e) {

    }
    public E get(int index) {
        return (E) array[index];
    }

    /**
     * 遵循写时复制，读写分离的原则
     * @param newValue
     * @param index
     * @return
     */
    public E set(E newValue,int index) {
        synchronized (Lock) {
            Object[] os = array.clone();
            os[index] = newValue;
            return (E) array[index];
        }
    }



}
