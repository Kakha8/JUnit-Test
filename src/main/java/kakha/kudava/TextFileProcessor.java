package kakha.kudava;

import java.io.*;

public class TextFileProcessor {
    public static class Result {
        public int lines;
        public int words;

        public Result(int lines, int words) {
            this.lines = lines;
            this.words = words;
        }
    }

    public static Result process(File inputFile, File outputFile) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader(inputFile));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));

        String s;
        int lineCount = 0;
        int wordCount = 0;

        while ((s = in.readLine()) != null) {
            s = s.trim();

            if (s.isEmpty()) {
                continue;
            }

            String line = s.toUpperCase();
            out.println(line);

            lineCount++;

            String trimmedLine = line.trim();
            if (!trimmedLine.isEmpty()) {
                String[] words = trimmedLine.split("\\s+");
                wordCount += words.length;
            }
        }

        in.close();
        out.close();

        return new Result(lineCount, wordCount);
    }
}
