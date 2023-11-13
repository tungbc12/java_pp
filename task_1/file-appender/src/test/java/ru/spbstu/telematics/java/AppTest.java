package ru.spbstu.telematics.java;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;

public class AppTest {

    @Test
    public void testAppendToFile() {
	// Set up
	String testFilePath = "test_output.txt";
	String testData = "Test data for appendToFile method.";

	// Call the method to be tested
	FileUtils fileUtils = new FileUtils();
	fileUtils.appendToFile(testFilePath, testData);

	// Read the content of the file to verify the result
	String fileContent = readFileContent(testFilePath);

	// Assert the expected result
	assertTrue(fileContent.endsWith(testData));
    }
    
    //Method for reading content of the file.
    private String readFileContent(String filePath) {
	StringBuilder content = new StringBuilder();
	try (FileReader file = new FileReader(filePath);
             BufferedReader input = new BufferedReader(file)) {

            String line;
	    while ((line = input.readLine()) != null) {
	        content.append(line);
	}

	} catch (Exception e) {
	    e.getStackTrace();
	}
	return content.toString();
    }
}

