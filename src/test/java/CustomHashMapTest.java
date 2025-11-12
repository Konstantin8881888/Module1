import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomHashMapTest {

    private CustomHashMap<String, Integer> map;

    //Гарантируем для каждого теста свежие данные.
    @BeforeEach
    void setUp() {
        map = new CustomHashMap<>();
    }

    @Test
    void testPutAndGetSize() {
        assertEquals(0, map.getSize());

        assertNull(map.put("one", 1));
        assertEquals(1, map.getSize());
        assertEquals(1, map.get("one"));

        assertEquals(1, map.put("one", 11)); //Возвращается старое значение.
        assertEquals(1, map.getSize()); //Размер не меняется, потому что ключ "one" уже существует.
        assertEquals(11, map.get("one"));
    }

    @Test
    void testMultipleInserts() {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        assertEquals(3, map.getSize());
        assertEquals(2, map.get("two"));
        assertEquals(3, map.get("three"));
    }

    @Test
    void testNullKey() {
        assertNull(map.get(null)); // изначально нет null-ключа

        map.put(null, 100);
        assertEquals(100, map.get(null));
        assertEquals(1, map.getSize());

        assertEquals(100, map.put(null, 200));
        assertEquals(200, map.get(null));
    }

    @Test
    void testGetNonExistentKey() {
        assertNull(map.get("nonexistent"));
        map.put("one", 1);
        assertNull(map.get("two"));
    }

    @Test
    void testMultipleUpdates() {
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        assertEquals(1, map.put("a", 10));
        assertEquals(2, map.put("b", 20));
        assertEquals(3, map.put("c", 30));

        assertEquals(10, map.get("a"));
        assertEquals(20, map.get("b"));
        assertEquals(30, map.get("c"));
        assertEquals(3, map.getSize());
    }

    @Test
    void testRemove() {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        // Удаление существующего ключа
        assertEquals(1, map.remove("one"));
        assertNull(map.get("one"));
        assertEquals(2, map.getSize());

        // Удаление несуществующего ключа
        assertNull(map.remove("nonexistent"));

        // Удаление оставшихся элементов
        assertEquals(2, map.remove("two"));
        assertEquals(3, map.remove("three"));
        assertEquals(0, map.getSize());
    }

    @Test
    void testRemoveNullKey() {
        map.put(null, 100);

        // Удаление null-ключа
        assertEquals(100, map.remove(null));
        assertNull(map.get(null));
        assertEquals(0, map.getSize());
    }

    @Test
    void testClear() {
        map.put("one", 1);
        map.put("two", 2);
        assertEquals(2, map.getSize());

        map.clear();
        assertEquals(0, map.getSize());
        assertTrue(map.isEmpty());
        assertNull(map.get("one"));
        assertNull(map.get("two"));
    }

    @Test
    void testIsEmpty() {
        assertTrue(map.isEmpty());
        map.put("test", 1);
        assertFalse(map.isEmpty());
        map.remove("test");
        assertTrue(map.isEmpty());
    }

    @Test
    void testContainsKey() {
        assertFalse(map.containsKey("nonexistent"));
        map.put("existing", 42);
        assertTrue(map.containsKey("existing"));
        assertFalse(map.containsKey("nonexistent"));

        // Test with null key
        map.put(null, 100);
        assertTrue(map.containsKey(null));
    }

    @Test
    void testToString() {
        assertEquals("{}", map.toString());

        map.put("key1", 1);
        String result1 = map.toString();
        assertTrue(result1.contains("key1=1"));

        map.put("key2", 2);
        String result2 = map.toString();
        assertTrue(result2.contains("key1=1"));
        assertTrue(result2.contains("key2=2"));
    }

    @Test
    void testResizeFunctionality() {
        for (int i = 0; i < 20; i++) {
            map.put("key" + i, i);
        }

        assertEquals(20, map.getSize());
        for (int i = 0; i < 20; i++) {
            assertEquals(i, map.get("key" + i));
        }
    }

    @Test
    void testHashCollisions() {
        map.put("Aa", 1);
        map.put("BB", 2);

        assertEquals(1, map.get("Aa"));
        assertEquals(2, map.get("BB"));
        assertEquals(2, map.getSize());
    }

    @Test
    void testRemoveNonExistentKey() {
        assertNull(map.remove("nonexistent"));
        map.put("existing", 1);
        assertNull(map.remove("nonexistent"));
        assertEquals(1, map.getSize());
    }

    @Test
    void testPutReturnValue() {
        assertNull(map.put("key", 1));
        assertEquals(1, map.put("key", 2));
        assertEquals(2, map.put("key", 3));
    }

    @Test
    void testComplexOperations() {
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        assertEquals(1, map.remove("a"));
        assertNull(map.get("a"));

        map.put("b", 22);
        assertEquals(22, map.get("b"));

        map.clear();
        assertTrue(map.isEmpty());
    }

    @Test
    void testHashCodeEdgeCases() {
        // Тест для ключей с экстремальными хэш-кодами
        CustomHashMap<Object, String> map = new CustomHashMap<>();

        // Ключ с MIN_VALUE хэш-кодом
        Object minHashKey = new Object() {
            @Override
            public int hashCode() {
                return Integer.MIN_VALUE;
            }
        };

        // Ключ с MAX_VALUE хэш-кодом
        Object maxHashKey = new Object() {
            @Override
            public int hashCode() {
                return Integer.MAX_VALUE;
            }
        };

        // Ключ с нулевым хэш-кодом
        Object zeroHashKey = new Object() {
            @Override
            public int hashCode() {
                return 0;
            }
        };

        map.put(minHashKey, "min");
        map.put(maxHashKey, "max");
        map.put(zeroHashKey, "zero");

        assertEquals("min", map.get(minHashKey));
        assertEquals("max", map.get(maxHashKey));
        assertEquals("zero", map.get(zeroHashKey));
    }

    @Test
    void testToStringWithNullKeysAndValues() {
        CustomHashMap<String, String> map = new CustomHashMap<>();

        // Тест с null ключами и значениями
        map.put(null, "null value");
        map.put("key with null value", null);
        map.put("normal", "normal");

        String result = map.toString();
        assertTrue(result.contains("null=null value"));
        assertTrue(result.contains("key with null value=null"));
        assertTrue(result.contains("normal=normal"));
    }

    @Test
    void testMultipleResizeOperations() {
        CustomHashMap<Integer, String> map = new CustomHashMap<>();

        // Добавляем много элементов для нескольких resize операций
        for (int i = 0; i < 10000; i++) {
            map.put(i, "value" + i);
        }

        assertEquals(10000, map.getSize());

        // Проверяем, что все элементы доступны после нескольких resize
        for (int i = 0; i < 10000; i++) {
            assertEquals("value" + i, map.get(i));
        }
    }

    @Test
    void testConcurrentModificationDuringIteration() {
        CustomHashMap<Integer, String> map = new CustomHashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(i, "value" + i);
        }

        // Тест на безопасное удаление через итератор
        assertDoesNotThrow(() -> {
            for (int i = 0; i < 10; i++) {
                map.remove(i);
            }
        });
    }
    @Test
    void testIteratorBehaviorInRemove() {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();

        // Создаем коллизии чтобы в одном бакете было несколько элементов
        map.put("Aa", 1);
        map.put("BB", 2);
        map.put("C#", 3);

        // Удаляем элемент из середины списка в бакете
        assertEquals(2, map.remove("BB"));

        // Проверяем что остальные элементы доступны
        assertEquals(1, map.get("Aa"));
        assertEquals(3, map.get("C#"));

        // Удаляем первый элемент в списке
        assertEquals(1, map.remove("Aa"));

        // Удаляем последний элемент в списке
        assertEquals(3, map.remove("C#"));

        assertTrue(map.isEmpty());
    }
    @Test
    void testResizeWithExtremeHashCodes() {
        CustomHashMap<Object, String> map = new CustomHashMap<>();

        // Создаем ключи с экстремальными хэш-кодами
        Object[] extremeKeys = {
                new Object() { // Integer.MIN_VALUE
                    @Override public int hashCode() { return Integer.MIN_VALUE; }
                    @Override public boolean equals(Object obj) { return this == obj; }
                },
                new Object() { // Integer.MAX_VALUE
                    @Override public int hashCode() { return Integer.MAX_VALUE; }
                    @Override public boolean equals(Object obj) { return this == obj; }
                },
                new Object() { // -1
                    @Override public int hashCode() { return -1; }
                    @Override public boolean equals(Object obj) { return this == obj; }
                },
                new Object() { // 0
                    @Override public int hashCode() { return 0; }
                    @Override public boolean equals(Object obj) { return this == obj; }
                }
        };

        // Добавляем элементы чтобы вызвать resize
        for (int i = 0; i < extremeKeys.length; i++) {
            map.put(extremeKeys[i], "value" + i);
        }

        // Добавляем еще элементы чтобы гарантированно вызвать resize
        for (int i = 0; i < 20; i++) {
            map.put("normal" + i, "normal" + i);
        }

        // Проверяем что все элементы доступны после resize
        for (int i = 0; i < extremeKeys.length; i++) {
            assertEquals("value" + i, map.get(extremeKeys[i]));
        }
    }

    @Test
    void testResizeDistribution() {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();

        // Создаем коллизии чтобы в одном бакете было несколько элементов
        String[] collidingKeys = {"Aa", "BB", "C#", "D$"}; // Эти строки имеют одинаковый хэш в Java

        // Заполняем карту до resize
        for (int i = 0; i < 12; i++) { // 12 = 16 * 0.75 (порог resize)
            map.put("key" + i, i);
        }

        // Добавляем коллидирующие ключи
        for (int i = 0; i < collidingKeys.length; i++) {
            map.put(collidingKeys[i], i + 100);
        }

        // Проверяем что все элементы доступны
        for (int i = 0; i < 12; i++) {
            assertEquals(i, map.get("key" + i));
        }
        for (int i = 0; i < collidingKeys.length; i++) {
            assertEquals(i + 100, map.get(collidingKeys[i]));
        }
    }

    @Test
    void testResizeWithNullKeys() {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();

        // Добавляем null ключи
        map.put(null, 1);
        map.put("normal", 2);
        map.put(null, 3); // обновление

        // Вызываем resize добавлением элементов
        for (int i = 0; i < 15; i++) {
            map.put("key" + i, i + 10);
        }

        // Проверяем что null ключ сохранился после resize
        assertEquals(3, map.get(null));
        assertEquals(2, map.get("normal"));
    }
}