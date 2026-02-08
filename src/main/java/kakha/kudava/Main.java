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
            TextFileProcessor.Result result =
                    TextFileProcessor.process(inputFile, outputFile);

            System.out.println("number of lines processed " + result.lines);
            System.out.println("number of words processed " + result.words);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}