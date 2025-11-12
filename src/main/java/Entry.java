//Простая структура для хранения пары ключ-значение.

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Entry<K, V> {
    private K key;
    private V value;
}
