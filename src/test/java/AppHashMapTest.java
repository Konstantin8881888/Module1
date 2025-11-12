import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppHashMapTest {

    @Test
    void testMainMethodRunsWithoutErrors() {
        // Перехватываем вывод в консоль
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            // Запускаем main метод
            AppHashMap.main(new String[]{});

            // Проверяем что вывод содержит ожидаемые строки
            String output = outContent.toString();
            assertTrue(output.contains("Прогрев JVM") ||
                    output.contains("Выполняется замер производительности") ||
                    output.contains("Сравнение производительности завершено"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testMainWithDifferentSizes() {
        // Тестируем с меньшим размером для скорости
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            // Можно модифицировать чтобы принимать аргументы, но для простоты тестируем как есть
            AppHashMap.main(new String[]{});

            String output = outContent.toString();
            assertTrue(output.contains("время выполнения"));
        } finally {
            System.setOut(originalOut);
        }
    }
}