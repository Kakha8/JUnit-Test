package kakha.kudava;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TextFilePorcessorTest {
    @TempDir
    Path tempDir;

    @Test
    void trimAndUpperCase() throws Exception {
        Path inputPath = tempDir.resolve("input.txt");
        Path outputPath = tempDir.resolve("output.txt");

        Files.writeString(inputPath, "  hello  \nworld\n");

        TextFileProcessor.Result result =
                TextFileProcessor.process(inputPath.toFile(), outputPath.toFile());

        String outputText = Files.readString(outputPath);
        String nl = System.lineSeparator();

        assertEquals("HELLO" + nl + "WORLD" + nl, outputText);
        assertEquals(2, result.lines);
    }
}
