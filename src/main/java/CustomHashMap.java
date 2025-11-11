import lombok.Getter;

@Getter
public class CustomHashMap<K, V> {
    private final int capacity = 16;
    private final Entry[] table = new Entry[capacity];
    private int size = 0;
    private final float loadFactor = 0.75f;

    private int indexFor(K key) {
        if (key == null) {
            return 0; //Ключ null хранится в бакете 0
        }
        int hash = key.hashCode();
        //Избегаен отрицательных значений.
        //TODO подумать над вычислениями
        return Math.abs(hash % capacity);
    }

    public V put(K key, V value) {
        //Найдём индекс бакета.
        int index = indexFor(key);

        //Посмотрим на ""голову" списка в этом бакете.
        Entry head = table[index];

        //Пробежим по списку и попробуем найти существующий ключ.
        Entry current = head;
        while (current != null) {
            //Null-safe сравнение ключей: если key == null, сравниваем на null, иначе — equals.
            K existingKey = (K) current.getKey();
            if (key == null ? existingKey == null : key.equals(existingKey)) {
                // найден ключ — заменяем значение и возвращаем старое.
                V oldValue = (V) current.getValue();
                current.setValue(value);
                return oldValue;
            }
            current = current.getNext();
        }

        //Если не нашли — создаём новый узел и вставляем его в начало списка бакета.
        Entry newEntry = new Entry<>(key, value, head);
        table[index] = newEntry;
        size++;

        return null;
    }

    public V get(K key) {
        int index = indexFor(key);
        Entry current = table[index];
        while (current != null) {
            K existingKey = (K) current.getKey();
            if (key == null ? existingKey == null : key.equals(existingKey)) {
                return (V) current.getValue();
            }
            current = current.getNext();
        }
        return null;
    }
}