import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
public class CustomHashMap<K, V> {
    //Используем список бакетов.
    private final List<LinkedList<Entry<K, V>>> table;
    private int capacity = 16;
    private int size = 0;
    private float loadFactor = 0.75f;

    public CustomHashMap() {
        table = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            table.add(new LinkedList<>());
        }
    }

    private int indexFor(K key) {
        if (key == null) return 0;
        int hash = key.hashCode();
        return Math.abs(hash % capacity);
    }

    public V put(K key, V value) {
        int idx = indexFor(key);
        LinkedList<Entry<K,V>> bucket = table.get(idx);

        for (Entry<K,V> e : bucket) {
            K existingKey = e.getKey();
            if (Objects.equals(key, existingKey)) {
                V old = e.getValue();
                e.setValue(value);
                return old;
            }
        }
        //Не найден — добавляем в начало списка (или в конец).
        bucket.addFirst(new Entry<K, V>(key, value));
        size++;
        return null;
    }

    public V get(K key) {
        int idx = indexFor(key);
        LinkedList<Entry<K,V>> bucket = table.get(idx);
        for (Entry<K,V> e : bucket) {
            K existingKey = e.getKey();
            if (Objects.equals(key, existingKey)) {
                return e.getValue();
            }
        }
        return null;
    }

    public V remove(K key) {
        int idx = indexFor(key);
        LinkedList<Entry<K,V>> bucket = table.get(idx);
        Iterator<Entry<K,V>> it = bucket.iterator();
        while (it.hasNext()) {
            Entry<K,V> e = it.next();
            K existingKey = e.getKey();
            if (Objects.equals(key, existingKey)) {
                V old = e.getValue();
                it.remove();
                size--;
                return old;
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (LinkedList<Entry<K,V>> bucket : table) bucket.clear();
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        boolean first = true;
        for (LinkedList<Entry<K,V>> bucket : table) {
            for (Entry<K,V> e : bucket) {
                if (!first) sb.append(", ");
                sb.append(String.valueOf(e.getKey())).append("=").append(String.valueOf(e.getValue()));
                first = false;
            }
        }
        sb.append("}");
        return sb.toString();
    }

}