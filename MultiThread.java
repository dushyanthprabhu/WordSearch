public class MultiThread extends Thread {
    String FilePath;

    @Override
    public void run() {
        WordSearch.Search(FilePath);
    }
}
