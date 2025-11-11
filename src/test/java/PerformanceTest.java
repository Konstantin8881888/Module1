//Проверка работы с большими объёмами данных.
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PerformanceTest {

    @Test
    void testManyInsertions() {
        CustomHashMap<Integer, String> map = new CustomHashMap<>();
        int count = 100;

        for (int i = 0; i < count; i++) {
            assertNull(map.put(i, "Value" + i));
        }

        assertEquals(count, map.getSize());

        for (int i = 0; i < count; i++) {
            assertEquals("Value" + i, map.get(i));
        }
    }

    @Test
    void testUpdatesOnLargeDataset() {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();

        //Добавляем много элементов.
        for (int i = 0; i < 50; i++) {
            map.put("key" + i, i);
        }

        //Обновляем некоторые.
        assertEquals(0, map.put("key0", 100));
        assertEquals(10, map.put("key10", 110));
        assertEquals(49, map.put("key49", 149));

        //Проверяем обновлённые и неизменённые значения.
        assertEquals(100, map.get("key0"));
        assertEquals(110, map.get("key10"));
        assertEquals(149, map.get("key49"));
        assertEquals(25, map.get("key25")); //Не меняли
        assertEquals(50, map.getSize());
    }
}
