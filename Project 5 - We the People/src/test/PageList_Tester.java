package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import proj5.PageList;

public class PageList_Tester {
    
    @Rule
    public Timeout timeout = Timeout.millis(100);

    private PageList pageList;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
   
    
    @Before
    public void setUp() throws Exception {
        pageList= new PageList();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() throws Exception {
        pageList = null; 
        System.setOut(originalOut);
    }


    @Test
    public void testAddPage() {
        pageList.addPage(1);
        assertTrue(pageList.containsPage(1));
        assertEquals(1, pageList.size());
        pageList.addPage(2);
        assertTrue(pageList.containsPage(2));
        assertEquals(2, pageList.size());
        assertEquals("{1-2}", pageList.toString());
    }

    @Test
    public void testRemovePage() {
        pageList.addPage(1);
        pageList.removePage(1);
        assertFalse(pageList.containsPage(1));
        assertEquals(0, pageList.size());

        pageList.addPage(1);
        pageList.addPage(2);
        pageList.addPage(3);
        pageList.removePage(1);
        assertFalse(pageList.containsPage(1));
        assertEquals(2, pageList.size());
        assertEquals("{2-3}", pageList.toString());
    }

    @Test
    public void testRemovePageNonExisting() {
        pageList.removePage(1);
        assertEquals("Page not in PageList\n", outContent.toString());
    }


    @Test
    public void testContainsPage() {
        assertFalse(pageList.containsPage(1));
        pageList.addPage(1);
        assertTrue(pageList.containsPage(1));
    }

    @Test
    public void testContainsPageMultiple() {
        pageList.addPage(1);
        pageList.addPage(2);
        pageList.addPage(3);
        assertTrue(pageList.containsPage(2));
    }

    @Test
    public void testContainsPageMultipleNotThere() {
        pageList.addPage(1);
        pageList.addPage(2);
        pageList.addPage(3);
        assertFalse(pageList.containsPage(4));
    }

    @Test
    public void testSize() {
        assertEquals(0, pageList.size());
        pageList.addPage(1);
        assertEquals(1, pageList.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(pageList.isEmpty());
        pageList.addPage(1);
        assertFalse(pageList.isEmpty());
    }

    @Test
    public void testAddPageList() {
        PageList otherPageList = new PageList();
        otherPageList.addPage(1);
        otherPageList.addPage(2);
        otherPageList.addPage(3);

        pageList.addPageList(otherPageList);

        assertTrue(pageList.containsPage(1));
        assertTrue(pageList.containsPage(2));
        assertTrue(pageList.containsPage(3));
        assertEquals(3, pageList.size());

        PageList thirdPageList = new PageList();
        thirdPageList.addPageList(otherPageList);
        thirdPageList.addPageList(otherPageList);
        assertEquals(6, thirdPageList.size());
        assertEquals("{1-3, 1-3}", thirdPageList.toString());
    }

    @Test
    public void testAddPageListPageListNotChanged(){ 
        PageList otherPageList = new PageList();
        otherPageList.addPage(1);
        otherPageList.addPage(2);
        otherPageList.addPage(3);

        pageList.addPageList(otherPageList);

        assertTrue(pageList.containsPage(1));
        assertTrue(pageList.containsPage(2));
        assertTrue(pageList.containsPage(3));
        assertEquals(3, pageList.size());


        assertTrue(otherPageList.containsPage(1));
        assertTrue(otherPageList.containsPage(2));
        assertTrue(otherPageList.containsPage(3));
        assertEquals(3, otherPageList.size());   

    }

    @Test
    public void testToString() {
        pageList.addPage(1);
        pageList.addPage(2);
        pageList.addPage(4);
        pageList.addPage(5);
        pageList.addPage(6);
        pageList.removePage(4);

        assertEquals("{1-2, 5-6}", pageList.toString());
    }

    @Test
    public void testToStringNoDashes() {
        pageList.addPage(2);
        pageList.addPage(4);
        pageList.addPage(6);
        pageList.addPage(8);
        pageList.addPage(10);

        assertEquals("{2, 4, 6, 8, 10}", pageList.toString());
    }

}
