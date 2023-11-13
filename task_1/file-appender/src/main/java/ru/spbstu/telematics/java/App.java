package ru.spbstu.telematics.java;

import java.io.FileWriter;
import java.io.BufferedWriter;

public class App {

    public static void main(String args[]) {

        FileUtils fileUtils = new FileUtils();

        //Write a string to a file in append mode.
        String filePath = "output.txt";
        String data = "Writing to a file in append mode!";

        fileUtils.appendToFile(filePath, data);
    }
}

class FileUtils {
    public void appendToFile(String filePath, String data) {
        try {
            // Create a FileWriter with append mode.
            FileWriter file = new FileWriter(filePath, true);

            // Create a BufferedWriter.
            BufferedWriter output = new BufferedWriter(file);

            // Write to file.
            output.write(data);
            output.newLine();

            // Closes the writer
            output.close();
            file.close();
        }

        catch (Exception e) {
            e.getStackTrace();
        }
    }
}
