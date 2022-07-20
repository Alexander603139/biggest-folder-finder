import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask; // позволяет создавать
// разветвляющиеся потоки которым можно собирать после воедин

public class FolderSizeCalculator extends RecursiveTask<Long> {
    private final File folder;

    public FolderSizeCalculator(File folder) {
        this.folder = folder;
    }

    @Override
    protected Long compute() {
        if (folder.isFile()) {
            return folder.length();
        }

        long sum = 0;
        // формируем список подзадач
        List<FolderSizeCalculator> subTasks = new LinkedList<>();
        File[] files = folder.listFiles();

        for (File file : files) {
            FolderSizeCalculator task = new FolderSizeCalculator(file);
            task.fork(); // запуск подзадачи асинхронно
            subTasks.add(task);

        }
        for (FolderSizeCalculator task : subTasks) {
            sum += task.join(); // дождемся выполнения подзадачи и прибавим результат
        }
        return sum;
    }
}
