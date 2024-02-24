package collection;

public interface List<E> extends Collection<E>{

    boolean retainAll(Collection<?> c);

    void sort();

    int indexOf(Object o);

    int lastIndexOf(Object o);

}
