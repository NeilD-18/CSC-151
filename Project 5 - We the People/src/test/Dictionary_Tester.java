package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import proj5.Dictionary;


/**
 * Tests for Dictionary class.
 * @author Neil Daterao
 * @version 03/12/2024
 */
public class Dictionary_Tester {
    
    @Rule
    public Timeout timeout = Timeout.millis(100);

    private Dictionary dictionary;
   
    
    @Before
    public void setUp() throws Exception {
        dictionary = new Dictionary();
    }

    @After
    public void tearDown() throws Exception {
        dictionary = null; 
    }

    @Test
    public void testAdd() {
        dictionary.add("apple");
        assertTrue(dictionary.contains("apple"));
    }

    @Test
    public void testAddDuplicate() {
        dictionary.add("apple");
        dictionary.add("apple");
        assertTrue(dictionary.contains("apple"));
        assertEquals(1, dictionary.size());
    }

    @Test
    public void testAddMultiple() {
        dictionary.add("apple");
        dictionary.add("banana");
        assertTrue(dictionary.contains("apple"));
        assertTrue(dictionary.contains("banana"));
        assertEquals(2, dictionary.size());
    }

    @Test
    public void testEmptyDictionary() {
        assertTrue(dictionary.isEmpty());
    }

    @Test
    public void testNonEmptyDictionary() {
        dictionary.add("apple");
        assertFalse(dictionary.isEmpty());
    }

    @Test
    public void testToStringEmpty() {
        assertEquals("Dictionary:\n", dictionary.toString());
    }

    @Test
    public void testToStringSingleWord() {
        dictionary.add("apple");
        assertEquals("Dictionary:\napple\n", dictionary.toString());
    }

    @Test
    public void testToStringMultipleWords() {
        dictionary.add("apple");
        dictionary.add("banana");
        dictionary.add("cherry");
        assertEquals("Dictionary:\napple\nbanana\ncherry\n", dictionary.toString());
    }

    @Test
    public void testToStringWithDuplicates() {
        dictionary.add("apple");
        dictionary.add("apple");
        dictionary.add("banana");
        assertEquals("Dictionary:\napple\nbanana\n", dictionary.toString());
    }



}
