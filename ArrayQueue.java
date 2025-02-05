import java.util.Arrays;
import java.util.Queue;

public class ArrayQueue<E> {

    private E[] data;
    private int f = 0;
    private int size = 0;

    public ArrayQueue(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(E e) throws IllegalStateException {
        if (size == data.length) {
            throw new IllegalStateException("Queue is full");
        }

        int last = (f + size) % data.length;
        data[last] = e;
        size++;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }

        return data[f];
    }

    public E dequeue() {
        if (isEmpty()) {
            return null;
        }

        E ans = data[f];
        data[f] = null;
        f = (f + 1) % data.length;
        size--;
        return ans;
    }

}
