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
}