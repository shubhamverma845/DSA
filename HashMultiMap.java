import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMultiMap<K, V> {
    Map<K, List<V>> map = new HashMap<>();

    int total = 0;

    public HashMultiMap() {

    }

    public int size() {
        return total;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    Iterable<V> get(K key) {
        List<V> secondary = map.get(key);
        if (secondary != null) {
            return secondary;
        }

        return new ArrayList<>();
    }

    void put(K key, V value) {
        List<V> secondary = map.get(key);
        if (secondary == null) {
            secondary = new ArrayList<>();
            map.put(key, secondary);
        }

        secondary.add(value);
        total++;
    }

    boolean remove(K key, V value) {
        boolean wasRemoved = false;
        List<V> secondary = map.get(key);
        if (secondary != null) {
            wasRemoved = secondary.remove(value);
            if (wasRemoved) {
                total--;
                if (secondary.isEmpty()) {
                    map.remove(key);
                }
            }
        }

        return wasRemoved;
    }


    Iterable<V> removeAll(K key) {
        List<V> secondary = map.get(key);
        if (secondary != null) {
            total -= secondary.size();
            map.remove(key);
        } else {
            secondary = new ArrayList<>();
        }

        return secondary;
    }

    Iterable<Map.Entry<K, V>> entries() {
        List<Map.Entry<K, V>> result = new ArrayList<>();
        for (Map.Entry<K, List<V>> secondary : map.entrySet()) {
            K key = secondary.getKey();
            for (V value : secondary.getValue()) {
                result.add(new AbstractMap.SimpleEntry<K, V>(key, value));
            }
        }

        return result;
    }
}
