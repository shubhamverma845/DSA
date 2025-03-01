import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class AbstractTree<E> implements Tree<E> {
    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }

    public boolean isExternal(Position<E> p) {
        return numChildren(p) == 0;
    }

    public boolean isRoot(Position<E> p) {
        return p == root();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int depth(Position<E> p) {
        if (isRoot(p)) {
            return 0;
        }

        return 1 + depth(parent(p));
    }

    public int height(Position<E> p) {
        int h = 0;

        if (isExternal(p)) {
            return 0;
        }

        for (Position<E> c : children(p)) {
            h = Math.max(h, 1 + height(c));
        }

        return h;
    }

    public int heightBad() {
        int h = 0;
        for (Position<E> p : positions()) {
            if (isExternal(p)) {
                h = Math.max(h, depth(p));
            }
        }

        return h;
    }

    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = positions().iterator();

        public boolean hasNext() {
            return posIterator.hasNext();
        }

        public E next() {
            return posIterator.next().getElement();
        }

        public void remove() {
            posIterator.remove();
        }
    }

    public Iterable<Position<E>> positions() {
        return preorder();
    }

    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    public Iterable<Position<E>> preorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            preorderSubTree(root(), snapshot);
        }

        return snapshot;
    }

    private void preorderSubTree(Position<E> p, List<Position<E>> snapshot) {
        snapshot.add(p);
        for (Position<E> c : children(p)) {
            preorderSubTree(c, snapshot);
        }
    }

    private void postorderSubTree(Position<E> p, List<Position<E>> snapshot) {
        for (Position<E> c : children(p)) {
            postorderSubTree(c, snapshot);
        }
        snapshot.add(p);
    }

    public Iterable<Position<E>> postorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            postorderSubTree(root(), snapshot);
        }

        return snapshot;
    }

    public Iterable<Position<E>> breadthfirst() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            Queue<Position<E>> fringe = new LinkedBlockingQueue<>();
            fringe.add(root());
            while (!fringe.isEmpty()) {
                Position<E> p = fringe.remove();
                snapshot.add(p);
                for (Position<E> c : children(p)) {
                    fringe.add(c);
                }
            }
        }

        return snapshot;

    }

    public void printPreOrderIndent(Tree<E> T, Position<E> p, int d) {
        System.out.println(String.format("%s %s", d, p.getElement()));
        for (Position<E> c : T.children(p)) {
            printPreOrderIndent(T, c, d + 1);
        }
    }

    public void printPreorderLabeled(Tree<E> T, Position<E> p, ArrayList<Integer> path) {
        int d = path.size();
        System.out.print(2 * d); //print spaces
        // print indentation, then label
        for (int j = 0; j < d; j++) {
            System.out.print(path.get(j) + (j == d - 1 ? " " : "."));
        }
        System.out.println(p.getElement());
        path.add(1);
        for (Position<E> c : T.children(p)) {
            printPreorderLabeled(T, c, path);
            path.set(d, 1 + path.get(d));
        }
        path.remove(d);
    }


    public int diskSpace(Tree<Integer> T, Position<Integer> p) {
        int subtotal = p.getElement();
        // we assume element represents space usage
        for (Position<Integer> c : T.children(p)) {
            subtotal += diskSpace(T, c);
        }
        return subtotal;
    }


    public void parenthesize(Tree<E> T, Position<E> p) {

        System.out.print(p.getElement());
        if (T.isInternal(p)) {
            boolean firstTime = true;
            for (Position<E> c : T.children(p)) {
                System.out.print((firstTime ? " (" : ", ")); // determine proper punctuation
                firstTime = false;
                parenthesize(T, c);
            }
            System.out.print(")");
        }
    }

}
