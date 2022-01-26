import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class LetterFormer {

    public void formLetter1(Set<String> keysChanged, Set<String> keysPrev, Set<String> keysNew){
        List<String> lines = Arrays.asList(
                "Здравствуйте, дорогая и.о. секретаря,\nЗа последние сутки во вверенных Вам сайтах произошли следующие изменения:\n",
                "Исчезли следующие страницы: " + String.join(", ", keysPrev),
                "Появились следующие новые страницы: " + String.join(", ", keysNew),
                "Изменились следующие страницы: " + String.join(", ", keysChanged),
                "\nС уважением,\nавтоматизированная система мониторинга.");
        Path letter1Path = Paths.get("letter1.txt");
        try {
            Files.write(letter1Path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void formLetter2(Set<String> keysChanged, Set<String> keysPrev, Set<String> keysNew) {
        List<String> lines = Arrays.asList(
                "Здравствуйте, дорогая и.о. секретаря,\nЗа последние сутки во вверенных Вам сайтах произошли следующие изменения:\n",
                "Исчезли следующие страницы: " + String.join(", ", keysPrev),
                "Появились следующие новые страницы: " + String.join(", ", keysNew),
                "Изменились следующие страницы: " + String.join(", ", keysChanged),
                "\nС уважением,\nавтоматизированная система мониторинга.");
        Path letter2Path = Paths.get("letter2.txt");
        try {
            Files.write(letter2Path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
