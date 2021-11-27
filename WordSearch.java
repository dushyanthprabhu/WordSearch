import java.io.File;
import java.util.*;

class WordSearch {
    public static String searchStr;

    public static void main(String[] args) {
        try {
            String DirPath = "F:/javacode/WordSearch/word";
            int choice;
            Scanner sc = new Scanner(System.in);
            File dir = new File(DirPath);
            System.out.println("Enter Search String:");
            searchStr = sc.next();
            System.out.println("Method to Search 1-Noraml 2-Parallel 3-Thread");
            choice = sc.nextInt();
            switch (choice) {
                case 1:// normal search
                    for (File file : dir.listFiles()) {
                        Search(file.getAbsolutePath());
                    }
                    break;
                case 2:// parallel process
                    parallelprocess(dir);
                    break;
                case 3:// thread
                    Files(dir.listFiles());
                    sc.close();
                    break;

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void Files(File[] files) {
        try {
            for (File file : files) {
                MultiThread th = new MultiThread();
                th.FilePath = file.getAbsolutePath();
                th.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void Search(String path) {
        try {
            boolean found = false;
            File file = new File(path);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String Line = reader.nextLine();
                String Words[] = Line.split(" ");

                for (String word : Words) {
                    if (word.equalsIgnoreCase(searchStr)) {
                        found = true;
                    }
                }
                if (found == true) {
                    System.out.println(searchStr);
                    break;
                }
            }
            reader.close();
            System.out.println(path + " Closed");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void parallelprocess(File dir) {
        try {
            List<String> Files = getFiles(dir);
            Files.parallelStream().forEach(WordSearch::Search);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static List<String> getFiles(File dir) {
        try {
            List<String> Files = new ArrayList<>();
            for (File file : dir.listFiles()) {
                Files.add(file.getAbsolutePath());
            }
            return Files;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    // Example Code Not Used
    public static void createSampleFiles() {
        try {
            String ogfilepath = "F:/javacode/WordSearch/word/test1.txt";
            for (int i = 0; i < 10; i++) {
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}