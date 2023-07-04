import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        char letters[] = {'A', 'B'};

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (char ch : letters) {
            StringBuilder builder = new StringBuilder();
            executorService.execute(() -> {

                for (int num = 1; num < 11; num++) {
                    builder.append(ch);
                    builder.append(num);
                    builder.append(" ");
                    builder.append(Thread.currentThread().getName());
                    builder.append("\n");
                }
            });

            PrintWriter writer = null;
            try {
                writer =  new PrintWriter("res/numbers" + Thread.currentThread().getId() + ".txt");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            writer.write(builder.toString());
            writer.flush();
            writer.close();
            executorService.shutdown();

        }
    }
}
