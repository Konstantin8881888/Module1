import java.util.HashMap;

public class AppHashMap {
    public static void main(String[] args) {
        int n = 100000;
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
