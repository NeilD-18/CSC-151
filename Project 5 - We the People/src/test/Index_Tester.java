package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import proj5.*;

public class Index_Tester {
    
    @Rule
    public Timeout timeout = Timeout.millis(100);

    
    private Index index;
    private final String testTextFile = "/Users/neil/Desktop/CSC-151/Project 5 - We the People/src/resources/test.txt";

    
    @Before
    public void setUp() throws Exception {
       index = new Index(testTextFile);
       index.createIndex();
    }

    @After
    public void tearDown() throws Exception {
        index = null; 
    }

    @Test 
    public void testCreateIndexCourseNotInIndex(){ 
        assertFalse("Index does not contain course",index.contains("course"));
        assertFalse("Index does not contain Course",index.contains("Course"));
        
        assertTrue("Dictionary contains course",index.getDictionary().contains("course"));

    }

    @Test 
    public void testCreateIndexCoursesInIndex() { 
        assertTrue("Index contains courses",index.contains("courses"));
        assertTrue("Courses shown on page 6", index.getIndexEntry("courses").getPageList().containsPage(6));
        assertTrue("Courses shown on page 12", index.getIndexEntry("courses").getPageList().containsPage(6));
    }


    @Test 
    public void testCreateIndexChrisNotInIndex(){ 
        assertTrue("Index contains 'Chris'", index.contains("Chris"));
        assertTrue("Courses shown on page 3", index.getIndexEntry("Chris").getPageList().containsPage(3));
        assertFalse("Dictionary does not contain 'Chris'", index.getDictionary().contains("Chris"));
    }

    @Test 
    public void testCreateIndexComputerInIndex(){ 
        assertTrue("Index contains 'computer'", index.contains("computer"));
        assertTrue("computer shown on page 2", index.getIndexEntry("computer").getPageList().containsPage(2));
        assertTrue("computer shown on page 6", index.getIndexEntry("computer").getPageList().containsPage(6));
        assertTrue("computer shown on page 10", index.getIndexEntry("computer").getPageList().containsPage(10));
        assertFalse("Dictionary does not contain 'computer'", index.getDictionary().contains("computer"));
    }

    @Test 
    public void testCreateIndexTakingInIndex(){ 
        assertTrue("Index contains 'taking'", index.contains("taking"));
        assertFalse("Dictionary does not contains 'taking'", index.getDictionary().contains("taking"));
    }

    @Test
    public void testMergeOfUpperCaseWordWithLowerCaseInIndex() { 
        assertTrue("Index contains 'computer'", index.contains("computer"));
        assertTrue("computer shown on page 6, this was Computer originally", index.getIndexEntry("computer").getPageList().containsPage(6));
        assertFalse("Index does not contain 'Computer'", index.contains("Computer"));
        assertFalse("Dictionary does not contain 'Computer'", index.getDictionary().contains("Computer"));
    }

    @Test public void testMergeOfLowerCaseWordWithUpperCaseInIndex(){
        assertTrue("Index contains 'the'", index.contains("the"));
        assertTrue("the shown on page 4, this was The originally", index.getIndexEntry("the").getPageList().containsPage(4));
        assertFalse("Index does not contain 'The'", index.contains("The"));
        assertFalse("Dictionary does not contain 'The'", index.getDictionary().contains("The"));
    }
    

    @Test
    public void testCreateIndexTheInIndexWithFullPageList(){
        assertTrue("Index contains 'the'", index.contains("the"));
        assertEquals(4, index.getIndexEntry("the").getPageList().size());
    }


    @Test
    public void testIndexAlphabeticalOrder() {
        String previousWord = null;
        String indexContent = index.toString(); 
        
        String[] lines = indexContent.split("\\r?\\n");

        for (String line : lines) {
            String word = line.split("\\s+")[0]; 
            if (previousWord != null) {
                assertTrue("'" + word + "' should come after '" + previousWord + "'",
                           word.compareTo(previousWord) >= 0);
            }
            previousWord = word;
        }
    }


}
