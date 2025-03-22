import javax.xml.stream.events.EndElement;
import java.util.Comparator;

public class HeapAdaptablePriorityQueue<K, V> extends HeapPriorityQueue<K, V> {

    protected static class AdaptablePQEntry<K, V> extends PQEntry<K, V> {
        private int index;

        public AdaptablePQEntry(K key, V value, int j) {
            super(key, value);
            this.index = j;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int j) {
            this.index = j;
        }
    }


    public HeapAdaptablePriorityQueue() {
        super();
    }

    public HeapAdaptablePriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    protected AdaptablePQEntry<K, V> validate(Entry<K, V> entry) throws IllegalArgumentException {
        if (!(entry instanceof HeapAdaptablePriorityQueue.AdaptablePQEntry<K, V>)) {
            throw new IllegalArgumentException("Invalid entry");
        }

        AdaptablePQEntry<K, V> locator = (AdaptablePQEntry<K, V>) entry;

        int j = locator.getIndex();

        if (j > heap.size() || heap.get(j) != locator) {
            throw new IllegalArgumentException("Invalid entry");
        }

        return locator;
    }


    protected void swap(int i, int j) {
        super.swap(i, j);
        ((AdaptablePQEntry<K, V>) heap.get(i)).setIndex(j);
        ((AdaptablePQEntry<K, V>) heap.get(j)).setIndex(i);
    }

    public Entry<K, V> insert(K key, V value) {
        checkKey(key);

        Entry<K, V> newest = new AdaptablePQEntry<>(key, value, heap.size());
        heap.add(newest);
        upHeap(heap.size() - 1);
        return newest;
    }

    public void remove(Entry<K, V> entry) {
        AdaptablePQEntry<K, V> locator = validate(entry);

        int j = locator.getIndex();

        if (j == heap.size() - 1) {
            heap.remove(heap.size() - 1);
        } else {
            swap(j, heap.size() - 1);
            heap.remove(heap.size() - 1);
            bubble(j);
        }
    }

    protected void bubble(int j) {
        if (j > 0 && compare(heap.get(j), heap.get(parent(j))) < 0) {
            upHeap(j);
        } else {
            downHeap(j);
        }
    }

    public void replaceKey(Entry<K, V> entry, K key) {
        AdaptablePQEntry<K, V> locator = validate(entry);
        checkKey(key);
        locator.setKey(key);
        bubble(locator.getIndex());
    }

    public void replaceValue(Entry<K, V> entry, V value) {
        AdaptablePQEntry<K, V> locator = validate(entry);
        locator.setValue(value);
    }

}
