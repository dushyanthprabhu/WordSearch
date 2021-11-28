import java.util.*;
import java.io.File;
import java.text.SimpleDateFormat;

class WordSearch {
    public static String searchStr;

    public static void main(String[] args) {
        try {
            String DirPath = "F:/javacode/WordSearch/word";
            int choice;
            Date start, end;
            long startTime, endTime;
            Scanner sc = new Scanner(System.in);
            File dir = new File(DirPath);
            System.out.println("Enter Search String:");
            searchStr = sc.next();
            System.out.println("Method to Search 1-Noraml 2-Parallel 3-Thread");
            choice = sc.nextInt();
            start = new Date();
            startTime = System.currentTimeMillis();
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
            end = new Date();
            endTime = System.currentTimeMillis();
            System.out.println("Time Taken : " + (endTime - startTime));
            TimeTaken(start, end);

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

    public static void TimeTaken(Date start, Date end) {
        try {
            Date d1 = null;
            Date d2 = null;
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            d1 = format.parse(format.format(start));
            d2 = format.parse(format.format(end));

            // in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Example Code Not Used
    public static void createSampleFiles() {
        try {
            // String ogfilepath = "F:/javacode/WordSearch/word/test1.txt";
            for (int i = 0; i < 10; i++) {
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
// import java.text.SimpleDateFormat;
// import java.util.Date;

// public class DateDifferentExample {

// public static void main(String[] args) {

// String dateStart = "01/14/2012 09:29:58";
// String dateStop = "01/15/2012 10:31:48";

// // HH converts hour in 24 hours format (0-23), day calculation
// SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

// Date d1 = null;
// Date d2 = null;

// try {
// d1 = format.parse(dateStart);
// d2 = format.parse(dateStop);

// // in milliseconds
// long diff = d2.getTime() - d1.getTime();

// long diffSeconds = diff / 1000 % 60;
// long diffMinutes = diff / (60 * 1000) % 60;
// long diffHours = diff / (60 * 60 * 1000) % 24;
// long diffDays = diff / (24 * 60 * 60 * 1000);

// System.out.print(diffDays + " days, ");
// System.out.print(diffHours + " hours, ");
// System.out.print(diffMinutes + " minutes, ");
// System.out.print(diffSeconds + " seconds.");

// } catch (Exception e) {
// e.printStackTrace();
// }

// }

// }