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
        try (FileWriter file = new FileWriter(filePath, true);
             BufferedWriter output = new BufferedWriter(file)){

            // Write to file.
            output.write(data);
            output.newLine();
        }

        catch (Exception e) {
            e.getStackTrace();
        }
    }
}
