package collection;

import java.util.Iterator;

public interface Collection<E> extends Iterator<E> {

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Object[] toArray();

    /**
     * CRUD
     * add
     * remove
     */
    void clear();
}
