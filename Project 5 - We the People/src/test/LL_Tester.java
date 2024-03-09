package test;

import proj5.LinkedList;
import org.junit.*;
import org.junit.rules.Timeout;
import static org.junit.Assert.*;

/**
 * LinkedList testing class for all methods in class. Tests generic linked list. 
 * @author Neil Daterao
 * @version 2/29/2024
 */

public class LL_Tester {

    @Rule // a test will fail if it takes longer than 1/10 of a second to run
    public Timeout timeout = Timeout.millis(100);

    private LinkedList<String> stringLL; 
    private LinkedList<Integer> intLL; 
    
    @Before
    public void setUp() throws Exception {
        stringLL = new LinkedList<>();
        intLL = new LinkedList<>();
    }

    @After
    public void tearDown() throws Exception {
      stringLL = null;
      intLL = null; 
    }
    

    
    @Test
    public void testRemoveHead() {
    
        stringLL.insertAtHead("A");
        stringLL.insertAtHead("B");
        stringLL.insertAtHead("C");
        stringLL.insertAtHead("D");
        stringLL.insertAtHead("E");
        stringLL.insertAtHead("F");
        stringLL.insertAtHead("G");

        stringLL.removeHead();

        assertEquals("(F, E, D, C, B, A)", stringLL.toString());
        assertEquals(6, stringLL.getLength());

    }

    @Test
    public void testRemoveHeadFromListWithOneElement() {
        
        stringLL.insertAtHead("A");

        assertEquals("A", stringLL.removeHead());
        assertTrue(stringLL.isEmpty());
    }

    @Test
    public void testRemoveHeadFromEmptyList() {
        // Create an empty linked list
        

        // Test removing from an empty list
        assertNull(stringLL.removeHead());
    }

    @Test
    public void testInsertAtTailInEmptyList() {
        // Create an empty linked list
        

        // Insert at the tail of an empty list
        stringLL.insertAtTail("A");

        // Verify the list after insertion
        assertEquals("(A)", stringLL.toString());
        assertEquals(1, stringLL.getLength());
    }

    @Test
    public void testInsertAtTailInListWithOneElement() {
        stringLL.insertAtHead("A");

        // Insert at the tail of a list with one element
        stringLL.insertAtTail("B");

        // Verify the list after insertion
        assertEquals("(A, B)", stringLL.toString());
        assertEquals(2, stringLL.getLength());
    }

    @Test
    public void testInsertAtTailInListWithMultipleElements() {
        stringLL.insertAtHead("A");
        stringLL.insertAtTail("B");
        stringLL.insertAtTail("C");
        stringLL.insertAtTail("D");
        stringLL.insertAtTail("E");
        stringLL.insertAtTail("F");
        stringLL.insertAtTail("G");

        // Verify the list after insertion
        assertEquals("(A, B, C, D, E, F, G)", stringLL.toString());
        assertEquals(7,stringLL.getLength());
    }


    @Test
    public void testIndexOfInEmptyList() {
        
        assertEquals(-1, intLL.indexOf(1));
        assertEquals(-1, intLL.indexOf(2));
        assertEquals(-1, intLL.indexOf(3));
    }

    @Test
    public void testIndexOfInListWithOneElement() {
        
        intLL.insertAtHead(1);

        assertEquals(0, intLL.indexOf(1));
        assertEquals(-1, intLL.indexOf(2));
        assertEquals(-1, intLL.indexOf(3));
    }

    @Test
    public void testIndexOfInListWithMultipleElements() {
 
        intLL.insertAtHead(1);
        intLL.insertAtTail(2);
        intLL.insertAtTail(3);
        intLL.insertAtTail(4);
        intLL.insertAtTail(5);
        intLL.insertAtTail(6);
        intLL.insertAtTail(7);

        assertEquals(0, intLL.indexOf(1));
        assertEquals(1, intLL.indexOf(2));
        assertEquals(2, intLL.indexOf(3));
        assertEquals(3, intLL.indexOf(4));
        assertEquals(4, intLL.indexOf(5));
        assertEquals(6, intLL.indexOf(7));
        assertEquals(-1, intLL.indexOf(8));


    }

    @Test
    public void testIndexOfWithDuplicateValues() {
    
        intLL.insertAtHead(1);
        intLL.insertAtHead(2);
        intLL.insertAtHead(1);

        // Test indexOf with duplicate values
        assertEquals(0, intLL.indexOf(1));
        assertEquals(1, intLL.indexOf(2));
        assertEquals(-1, intLL.indexOf(3));
    }

    @Test
    public void testInsertAtIndex() {
        
        intLL.insertAtHead(1);
        intLL.insertAtHead(2);
        intLL.insertAtHead(3);
        
        // Test inserting at index 0
        intLL.insertAtIndex(0, 4);
        assertEquals("(4, 3, 2, 1)", intLL.toString());
        
        // Test inserting in the middle
        intLL.insertAtIndex(2, 5);
        assertEquals("(4, 3, 5, 2, 1)", intLL.toString());
        
        // Test inserting at the end
        intLL.insertAtIndex(5, 6);
        assertEquals("(4, 3, 5, 2, 1, 6)", intLL.toString());
        
        // Test inserting at index equal to length (append)
        intLL.insertAtIndex(6, 7);
        assertEquals("(4, 3, 5, 2, 1, 6, 7)", intLL.toString());
    }
    
    @Test
    public void testInsertAtIndexEmptyList() {
        
        intLL.insertAtIndex(0, 1);
        assertEquals("(1)", intLL.toString());
    }
    
    @Test
    public void testInsertAtIndexInvalidIndex() {
        
        intLL.insertAtHead(1);
        intLL.insertAtHead(2);
        
        // Test inserting at index out of bounds
        assertThrows(IndexOutOfBoundsException.class, () -> {
            intLL.insertAtIndex(3, 3);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            intLL.insertAtIndex(-1, 3);
        });


    }

    @Test
    public void testGetIthItem() {
        
        intLL.insertAtHead(1);
        intLL.insertAtHead(2);
        intLL.insertAtHead(3);
        
        // Test getting the first item (index 0)
        assertEquals((Integer)3, intLL.getIthItem(0));
        
        // Test getting an item in the middle
        assertEquals((Integer)2, intLL.getIthItem(1));
        
        // Test getting the last item
        assertEquals((Integer)1, intLL.getIthItem(2));
    }
    
    @Test
    public void testGetIthItemIndexOutOfBounds() {
        
        intLL.insertAtHead(1);
        
        // Test getting item at index out of bounds
        assertNull(intLL.getIthItem(1));
    }
    
    @Test
    public void testGetIthItemEmptyList() {
        
        assertNull(intLL.getIthItem(0));
    }

    @Test
    public void testClone() {
        LinkedList<String> originalList = new LinkedList<>();
        originalList.insertAtHead("A");
        originalList.insertAtHead("B");
        originalList.insertAtHead("C");
        
        // Clone the original list
        LinkedList<String> clonedList = originalList.clone();
        
        // Test that the cloned list is not the same object reference as the original list
        assertNotSame(originalList, clonedList);
        
        // Test that the data of each node in the cloned list matches the data of the corresponding node in the original list
        assertEquals(originalList.toString(), clonedList.toString());

        LinkedList<Integer> emptyList = new LinkedList<>();
        LinkedList<Integer> clonedEmptyList = emptyList.clone();

        assertEquals(emptyList.toString(), clonedEmptyList.toString());
    }

    @Test
    public void testEqualsSameObject() {
        
        intLL.insertAtHead(1);
        intLL.insertAtHead(2);
        intLL.insertAtHead(3);

        assertTrue(intLL.equals(intLL)); // Same object should be equal
    }

    @Test
    public void testEqualsNullObject() {
        
        intLL.insertAtHead(1);
        intLL.insertAtHead(2);
        intLL.insertAtHead(3);

        assertFalse(intLL.equals(null)); // Null object should not be equal
    }

    @Test
    public void testEqualsDifferentClass() {
        
        stringLL.insertAtHead("A");
        stringLL.insertAtHead("B");
        stringLL.insertAtHead("C");

        assertFalse(stringLL.equals("hello")); // Different class should not be equal
    }

    @Test
    public void testEqualsDifferentLists() {
        LinkedList<String> list1 = new LinkedList<>();
        list1.insertAtHead("A");
        list1.insertAtHead("B");
        list1.insertAtHead("C");

        LinkedList<String> list2 = new LinkedList<>();
        list2.insertAtHead("X");
        list2.insertAtHead("Y");
        list2.insertAtHead("Z");

        assertFalse(list1.equals(list2)); // Different lists should not be equal
    }

    @Test
    public void testEqualsEqualLists() {
        LinkedList<Integer> list1 = new LinkedList<>();
        list1.insertAtHead(1);
        list1.insertAtHead(2);
        list1.insertAtHead(3);

        LinkedList<Integer> list2 = new LinkedList<>();
        list2.insertAtHead(1);
        list2.insertAtHead(2);
        list2.insertAtHead(3);

        assertTrue(list1.equals(list2)); // Equal lists should be equal
    }

    
    @Test
    public void testEqualsDifferentLLTypes(){
        LinkedList<Integer> list1 = new LinkedList<>();
        list1.insertAtHead(1);
        list1.insertAtHead(2);
        list1.insertAtHead(3);

        LinkedList<String> list2 = new LinkedList<>();
        list2.insertAtHead("X");
        list2.insertAtHead("Y");
        list2.insertAtHead("Z");

        assertFalse(list1.equals(list2));

    }
    
    
    
    @Test
    public void testEqualsUnequalLengthLists() {
        LinkedList<String> list1 = new LinkedList<>();
        list1.insertAtHead("A");
        list1.insertAtHead("B");
        list1.insertAtHead("C");

        LinkedList<String> list2 = new LinkedList<>();
        list2.insertAtHead("A");
        list2.insertAtHead("B");

        assertFalse(list1.equals(list2)); // Lists of unequal length should not be equal
    }

    @Test
    public void testEqualsEmptyLists() {
        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();

        assertTrue(list1.equals(list2)); // Empty lists should be equal
    }

    @Test
    public void testContainsEmptyList() {
        
        assertFalse(intLL.contains(1)); // Empty list should not contain any value
    }

    @Test
    public void testContainsSingleElement() {
        
        stringLL.insertAtHead("A");
        assertTrue(stringLL.contains("A")); // stringLL with a single element should contain that value
        assertFalse(stringLL.contains("B")); // stringLL with a single element should not contain other values
    }

    @Test
    public void testContainsMultipleElements() {
        
        intLL.insertAtHead(1);
        intLL.insertAtHead(2);
        intLL.insertAtHead(3);

        assertTrue(intLL.contains(1)); 
        assertTrue(intLL.contains(2)); 
        assertTrue(intLL.contains(3)); 
        assertFalse(intLL.contains(4));
    }

    @Test
    public void testContainsDuplicateElements() {
        
        stringLL.insertAtHead("A");
        stringLL.insertAtHead("A");

        assertTrue(stringLL.contains("A")); // List with duplicate elements should contain "A"
    }


    @Test
    public void testRemoveAtIndexFromEmptyList() {
        
        assertThrows(IndexOutOfBoundsException.class, () -> {
            intLL.removeAtIndex(0);
        });
    }

    @Test
    public void testRemoveAtIndexOutOfBounds() {
        
        intLL.insertAtHead(1);
        intLL.insertAtHead(2);
        intLL.insertAtHead(3);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            intLL.removeAtIndex(3); // Index out of bounds
        });
    }

    @Test
    public void testRemoveAtIndexFirstNode() {
        
        stringLL.insertAtHead("A");
        stringLL.insertAtHead("B");
        stringLL.insertAtHead("C");
        stringLL.removeAtIndex(0);
        assertEquals("(B, A)", stringLL.toString());
    }

    @Test
    public void testRemoveAtIndexLastNode() {
        
        stringLL.insertAtHead("A");
        stringLL.insertAtHead("B");
        stringLL.insertAtHead("C");
        stringLL.removeAtIndex(2);
        assertEquals("(C, B)", stringLL.toString());
    }

    @Test
    public void testRemoveAtIndexMiddleNode() {
        
        intLL.insertAtHead(1);
        intLL.insertAtHead(2);
        intLL.insertAtHead(3);
        intLL.removeAtIndex(1);
        assertEquals("(3, 1)", intLL.toString());
    }


    @Test
    public void testClearEmptyList() {
        
        intLL.clear();
        assertTrue(intLL.isEmpty()); 
        assertEquals(0, intLL.getLength()); 
    }

    @Test
    public void testClearNonEmptyList() {
        
        stringLL.insertAtHead("A");
        stringLL.insertAtHead("B");
        stringLL.insertAtHead("C");
        stringLL.clear();
        assertTrue(stringLL.isEmpty()); 
        assertEquals(0, stringLL.getLength()); 
    }

    @Test
    public void testClearMultipleTimes() {
        
        intLL.insertAtHead(1);
        intLL.insertAtHead(2);
        intLL.clear();
        intLL.clear(); 
        assertTrue(intLL.isEmpty()); 
        assertEquals(0, intLL.getLength()); 
    }









}
