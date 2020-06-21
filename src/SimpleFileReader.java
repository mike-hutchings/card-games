import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SimpleFileReader {

    public static List<String> readFile(Path file) {

        List<String> input = new ArrayList<>();

        try {
            input = Files.readAllLines(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return input;
    }
}
 