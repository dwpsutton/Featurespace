package examples;

public class MyCollection<E>  {
    private Object[] elementData = new Object[10];
    private int size;
    public boolean add(E e) {
        elementData[size++] = e;
        return true;
    }
}
