import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<E> {

    private class ArrayIterator implements Iterator<E> {

        private int j = 0;
        private boolean removable = false;

        @Override
        public boolean hasNext() {
            return j < size;
        }

        @Override
        public E next() {
            if (j == size) {
                throw new NoSuchElementException("No next element");
            }

            removable = true;
            return data[j++];
        }

        @Override
        public void remove() {
            if (!removable) {
                throw new IllegalStateException("noting to remove");
            }

            ArrayList.this.remove(j - 1);
            j--;
            removable = false;
        }
    }

    public Iterator<E> iterator() {
        return new ArrayIterator();
    }

    public static final int CAPACITY = 16;
    private E[] data;
    private int size = 0;

    public ArrayList() {
        this(CAPACITY);
    }

    public ArrayList(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E get(int index) {
        checkIndex(index, size);
        return data[index];
    }

    public E set(int index, E e) {
        checkIndex(index, size);
        E temp = data[index];
        data[index] = e;
        return temp;
    }

    public void add(int index, E e) {
        checkIndex(index, size + 1);
        if (size == data.length) {
            resize(2 * data.length);
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = e;
        size++;
    }

    public E remove(int index) {
        checkIndex(index, size);
        E temp = data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
        return temp;
    }

    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
        if (i < 0 || i >= n) {
            throw new IndexOutOfBoundsException("Illegal Index:" + i);
        }
    }

    protected void resize(int capacity) {
        E[] temp = (E[]) new Object[capacity];
        for (int k = 0; k < data.length; k++) {
            temp[k] = data[k];
        }
        data = temp;
    }
}
