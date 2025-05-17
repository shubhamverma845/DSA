import java.util.Comparator;

public class SplayTreeMap<K, V> extends TreeMap<K, V> {

    public SplayTreeMap() {
    }

    public SplayTreeMap(Comparator<K> comparator) {
        super(comparator);
    }

    private void splay(Position<Entry<K, V>> p) {
        while (!isRoot(p)) {
            Position<Entry<K, V>> parent = parent(p);
            Position<Entry<K, V>> grandparent = parent(parent);

            if (grandparent == null) {
                //zig case
                rotate(p);
            } else if ((parent == left(grandparent)) == (p == left(parent))) {
                //zig-zig case
                rotate(parent);
                rotate(p);
            } else {
                rotate(p);
                rotate(p);
            }
        }
    }

    protected void rebalanceAccess(Position<Entry<K, V>> p) {
        if (isExternal(p)) {
            p = parent(p);
        }

        if (p != null) {
            splay(p);
        }
    }

    protected void rebalanceInsert(Position<Entry<K, V>> p) {
        splay(p);
    }

    protected void rebalanceDelete(Position<Entry<K, V>> p) {
        if (!isRoot(p)) {
            splay(parent(p));
        }
    }
}
