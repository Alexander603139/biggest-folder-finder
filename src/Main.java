import java.io.File;

public class Main {
    public static void main(String[] args) {
        String folderPath = "C:\\Users\\user\\Desktop\\КНИГИ_IT";
        File file = new File(folderPath);

        // размер директории
        System.out.println(getFolderSize(file));
    }

    public static long getFolderSize(File folder) {
        if (folder.isFile()) {
            return folder.length();
        }
        long sum = 0;
        File[] files = folder.listFiles();
        for (File file : files) {
            sum += getFolderSize(file);
        }
        return sum;
    }
}