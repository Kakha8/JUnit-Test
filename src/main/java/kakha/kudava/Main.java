package kakha.kudava;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        File inputFile = new File(input.nextLine());
        while (!inputFile.isFile()) {
            System.out.println("Invalid file path");
            inputFile = new File(input.nextLine());
        }
        File outputFile = new File("./src/output.txt");
        try {
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));
            String s;
            int lineCount = 0;
            int wordCount = 0;
            while ((s = in.readLine()) != null) {
                s = s.trim();
                if(s.isEmpty()){
                    continue;
                }

                String line = s.toUpperCase();
                PrintWriter pw = new PrintWriter(out);
                pw.println(line);
                lineCount++;
                String trimmedLine = line.trim();
                if (!trimmedLine.isEmpty()) {
                    String[] words = trimmedLine.split("\\s+");
                    wordCount += words.length;
                }
            }
            in.close();
            out.close();
            System.out.println("number of lines processed " + lineCount);
            System.out.println("number of words processed " + wordCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}