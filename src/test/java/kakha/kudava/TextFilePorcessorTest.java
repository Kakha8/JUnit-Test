package kakha.kudava;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TextFilePorcessorTest {
    @TempDir
    Path tempDir;

    @Test
    void trimAndUpperCase() throws Exception {
        Path inputPath = tempDir.resolve("input.txt");
        Path outputPath = tempDir.resolve("output.txt");

        //creating file
        Files.writeString(inputPath, "  hello  \nworld\n");

        TextFileProcessor.Result result =
                TextFileProcessor.process(inputPath.toFile(), outputPath.toFile());

        String outputText = Files.readString(outputPath);
        String nl = System.lineSeparator();

        assertEquals("HELLO" + nl + "WORLD" + nl, outputText);
        assertEquals(2, result.lines);
    }

    @Test
    void missingInputFile() {
        Path missing = tempDir.resolve("missing.txt");
        Path outputPath = tempDir.resolve("output.txt");

        assertThrows(java.io.FileNotFoundException.class, () ->
                TextFileProcessor.process(missing.toFile(), outputPath.toFile())
        );
    }

    @Test
    void realLineCount() throws Exception {
        Path inputPath = tempDir.resolve("input.txt");
        Path outputPath = tempDir.resolve("output.txt");

        Files.writeString(inputPath,
                "\n" +
                        " first line \n" +  // valid line
                        "   \t   \n" +
                        "second line\n" +   // valid
                        "\n");

        TextFileProcessor.Result result =
                TextFileProcessor.process(inputPath.toFile(), outputPath.toFile());


        assertEquals(2, result.lines);

        String nl = System.lineSeparator();
        assertEquals(
                "FIRST LINE" + nl +
                        "SECOND LINE" + nl,
                Files.readString(outputPath)
        );
    }

    @Test
    void preserveTabs() throws Exception {
        Path inputPath = tempDir.resolve("input.txt");
        Path outputPath = tempDir.resolve("output.txt");

        Files.writeString(inputPath, " \tHello\t  world   \t\n");

        TextFileProcessor.Result result =
                TextFileProcessor.process(inputPath.toFile(), outputPath.toFile());

        String outputText = Files.readString(outputPath);
        String nl = System.lineSeparator();

        assertEquals("HELLO\t  WORLD" + nl, outputText);
        assertEquals(1, result.lines);
    }

    @Test
    void wordCount() throws Exception {
        Path inputPath = tempDir.resolve("input.txt");
        Path outputPath = tempDir.resolve("output.txt");

        Files.writeString(inputPath,
                "hello world\n" +
                        "multiple   spaces here\n" +
                        "tabs\tand\twords\n");

        TextFileProcessor.Result result =
                TextFileProcessor.process(inputPath.toFile(), outputPath.toFile());

        assertEquals(8, result.words);
    }
}
