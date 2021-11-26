import java.io.File;
import java.util.*;

class WordSearch {
    public static String searchStr;

    public static void main(String[] args) {
        try {
            String DirPath = "C:/Users/ASUS/Desktop/word";
            Scanner sc = new Scanner(System.in);
            searchStr = sc.next();
            File dir = new File(DirPath);
            Files(dir.listFiles());
            sc.close();
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
}