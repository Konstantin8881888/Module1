import java.util.Random;

public class AppHashMap {
    public static void main(String[] args) {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();

        //Простой пример работы (аналог есть в тестах, но здесь оставлено, чтобы можно было убедиться в работе main-класса.
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        System.out.println("Размер: " + map.getSize());
        System.out.println("Первое значение: " + map.get("one"));
        System.out.println("Второе значение: " + map.get("two"));

        //Пример с вычислением времени (специально на случайных числах, для большей реалистичности).
        Random random = new Random();
        int elementCount = 100000;

        //Замер времени для массового добавления случайных элементов.
        long startTime = System.nanoTime();

        CustomHashMap<Integer, String> largeMap = new CustomHashMap<>();
        int[] randomKeys = new int[elementCount];

        //Генерируем случайные ключи.
        for (int i = 0; i < elementCount; i++) {
            randomKeys[i] = random.nextInt(elementCount);
        }

        // Добавляем элементы со случайными ключами.
        for (int i = 0; i < elementCount; i++) {
            largeMap.put(randomKeys[i], "Value " + randomKeys[i]);
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;

        System.out.println("Добавление " + elementCount + " случайных элементов: " + duration + " мс");
        System.out.println("Размер большой мапы: " + largeMap.getSize());
    }
}
