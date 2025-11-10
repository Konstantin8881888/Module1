//Простая структура для хранения пары ключ-значение.
package org.klimtsov.module1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entry<K, V> {
    private K key;
    private V value;
    private Entry<K,V> next; //Ссылка на следующий элемент.
}
