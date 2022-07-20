import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
//        // объявление потоков
//        MyThread thread = new MyThread(1);
//        MyThread thread2 = new MyThread(2);
//        // выполнение потоков
//        thread.start();
//        thread2.start();

        String folderPath = "C:\\Users\\user\\Desktop\\КНИГИ_IT";
        File file = new File(folderPath);
        long start = System.currentTimeMillis(); // время запуска
        FolderSizeCalculator calculator = new FolderSizeCalculator(file);

        // создание пула потоков
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator); // когда все потоки выполнятся ...
        System.out.println(size);

//        System.out.println(getFolderSize(file));// размер директории

        long duration = System.currentTimeMillis() - start; // продолжительность выполнения
        System.out.println(duration); // в миллисекундах


    }

    public static long getFolderSize(File folder) {
        if (folder.isFile()) {
            return folder.length();
        }
        long sum = 0;
        File[] files = folder.listFiles();
        for (File file : files) {
            sum += getFolderSize(file); // рекурсив = вызов само себя
        }
        return sum;
    }
}
