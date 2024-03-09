package test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import proj4.Converter;


/**
 * Testing class for Converter. Compares outputs based on a testing file and its solutions. 
 */
public class ConverterTester {
    
    @Rule
    public Timeout timeout = Timeout.millis(100);
	
    private Converter converter;
    private final String TESTFILEPATH = "/Users/neil/Desktop/CSC-151/Project 4 - The Infix to Postfix Converter/src/resources/converter_test.txt";
    private final String TESTFILEPATHSOLS = "/Users/neil/Desktop/CSC-151/Project 4 - The Infix to Postfix Converter/src/resources/converter_test_sols.txt";

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    
    
    @Before
    public void setUp() throws Exception {
        converter = new Converter(TESTFILEPATH);
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() throws Exception {
        converter = null; 
        System.setOut(originalOut);
    }


    @Test 
    public void testConvert() { 
        converter.convert();
        String expString = getSolsAsString(TESTFILEPATHSOLS);
        assertEquals("Output printed to console should match expected output", expString.trim(), outContent.toString().trim());
       
    }

    private String getSolsAsString(String FilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FilePath))) {
            StringBuilder expectedOutputBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("EOF")) {
                    break; 
                }
                expectedOutputBuilder.append(line + "\n");
            }
            return expectedOutputBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace(); 
            return ""; 
        }
    }
}
