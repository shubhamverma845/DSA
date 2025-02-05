public class LinkedStack<E> {
    private SinglyLinkedList<E> list = new SinglyLinkedList<>();

    public void push(E element) {
        list.addFirst(element);
    }

    public E pop() {
        return list.removeFirst();
    }

    public E top() {
        return list.first();
    }

    public int size() {
        return list.getSize();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
