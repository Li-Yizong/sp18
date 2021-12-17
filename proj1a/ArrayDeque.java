public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    private static final int initialCapacity = 8;

    public ArrayDeque(){
        items = (T[])new Object[initialCapacity];
        size = 0;
        nextFirst = items.length - 1;
        nextLast = 0;
    }

    private int minusOne(int index) {
        index -= 1;

        if (index < 0) {
            index = items.length - 1;
        }

        return index;
    }

    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    public void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];

        int start = plusOne(nextFirst);

        for (int i = 0; i < size; i++) {
            newItems[i] = items[start];
            start = plusOne(start);
        }

        items = newItems;

        nextFirst = items.length - 1;
        nextLast = size;
    }

    public void addLast(T x) {
        if (size + 1 == items.length) {
            resize(items.length * 2);
        }

        items[nextLast] = x;
        size += 1;
        nextLast = plusOne(nextLast);
    }

    public void addFirst(T x) {
        if (size + 1 == items.length) {
            resize(items.length * 2);
        }

        items[nextFirst] = x;
        size += 1;
        nextFirst = minusOne(nextFirst);
    }

    public T removeLast(){
        if (size == 0) {
            return null;
        }

        nextLast = minusOne(nextLast);
        T rem = items[nextLast];
        items[nextLast] = null;

        if (items.length > 16 && size <= items.length / 4) {
            resize(items.length/2);
        }

        return rem;
    }

    public T removeFirst(){
        if (size == 0) {
            return null;
        }

        nextFirst = plusOne(nextFirst);
        T rem = items[nextFirst];
        items[nextFirst] = null;

        if (items.length > 16 && size <= items.length / 4) {
            resize(items.length/2);
        }

        return rem;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printArrayDeque(){
        int start = plusOne(nextFirst);
        for (int i = 0; i < size; i++){
            System.out.print(items[start] + " ");
            start = plusOne(start);
        }
    }

    public T get(int index) {
        return items[(nextFirst + index) % items.length];
    }
}
