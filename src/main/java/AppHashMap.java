import java.util.HashMap;

public class AppHashMap {
    public static void main(String[] args) {
        int n = 100000;

        // Многократный прогрев JVM (без него цифры были совсем различными, почти в тысячи раз!)
        System.out.println("Прогрев JVM...");
        for (int warmupIteration = 0; warmupIteration < 3; warmupIteration++) {
            CustomHashMap<Integer, String> warmupMap = new CustomHashMap<>();
            HashMap<Integer, String> warmupStdMap = new HashMap<>();

            for (int i = 0; i < 10000; i++) {
                warmupMap.put(i, "Value" + i);
                warmupStdMap.put(i, "Value" + i);
            }
            for (int i = 0; i < 10000; i++) {
                warmupMap.get(i);
                warmupStdMap.get(i);
            }
            for (int i = 0; i < 10000; i++) {
                warmupMap.remove(i);
                warmupStdMap.remove(i);
            }
        }

        System.gc(); //Запрос сборки мусора между прогревом и замерами.
        try {
            Thread.sleep(200);
        } catch (InterruptedException ignored) {

        }

        System.out.println("Выполняется замер производительности...");

        System.out.println("Сравнение времени работы для CustomHashMap");
        CustomHashMap<Integer, String> customMap = new CustomHashMap<>();
        long startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            customMap.put(i, "Value" + i);
        }
        long endTime = System.nanoTime();
        System.out.println("CustomHashMap время выполнения put: " + (endTime - startTime) + " нс.");

        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            customMap.get(i);
        }
        endTime = System.nanoTime();
        System.out.println("CustomHashMap время выполнения get: " + (endTime - startTime) + " нс.");

        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            customMap.remove(i);
        }
        endTime = System.nanoTime();
        System.out.println("CustomHashMap время выполнения remove: " + (endTime - startTime) + " нс.");

        System.out.println("Сравнение времени работы для стандартного HashMap");
        HashMap<Integer, String> hashMap = new HashMap<>();
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            hashMap.put(i, "Value" + i);
        }
        endTime = System.nanoTime();
        System.out.println("HashMap время выполнения put: " + (endTime - startTime) + " нс.");

        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            hashMap.get(i);
        }
        endTime = System.nanoTime();
        System.out.println("HashMap время выполнения get: " + (endTime - startTime) + " нс.");

        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            hashMap.remove(i);
        }
        endTime = System.nanoTime();
        System.out.println("HashMap время выполнения remove: " + (endTime - startTime) + " нс.");

        System.out.println("Сравнение производительности завершено.");
    }
}
