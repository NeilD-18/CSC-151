package test;

import proj3.LinkedList;
import org.junit.*;
import org.junit.rules.Timeout;
import static org.junit.Assert.*;

/**
 * LinkedList testing class for RemoveHead, InsertAtTail and indexOf
 */

public class LinkedListTester {

    @Rule // a test will fail if it takes longer than 1/10 of a second to run
    public Timeout timeout = Timeout.millis(100);

    @Test
    public void testRemoveHead() {
        LinkedList ll = new LinkedList();
        //Linked list with multiple elements
        ll.insertAtHead("A");
        ll.insertAtHead("B");
        ll.insertAtHead("C");
        ll.insertAtHead("D");
        ll.insertAtHead("E");
        ll.insertAtHead("F");
        ll.insertAtHead("G");

        ll.removeHead();

        //test remove head
        assertEquals("(F, E, D, C, B, A)", ll.toString());
        assertEquals(6, ll.getLength());

    }

    @Test
    public void testRemoveHeadFromListWithOneElement() {
        // Create a linked list with one element
        LinkedList ll = new LinkedList();
        ll.insertAtHead("A");

        // Test removing from a list with one element
        assertEquals("A", ll.removeHead());
        assertTrue(ll.isEmpty());
    }

    @Test
    public void testRemoveHeadFromEmptyList() {
        // Create an empty linked list
        LinkedList ll = new LinkedList();

        // Test removing from an empty list
        assertNull(ll.removeHead());
    }

    @Test
    public void testInsertAtTailInEmptyList() {
        // Create an empty linked list
        LinkedList ll = new LinkedList();

        // Insert at the tail of an empty list
        ll.insertAtTail("A");

        // Verify the list after insertion
        assertEquals("(A)", ll.toString());
        assertEquals(1, ll.getLength());
    }

    @Test
    public void testInsertAtTailInListWithOneElement() {
        // Create a linked list with one element
        LinkedList ll = new LinkedList();
        ll.insertAtHead("A");

        // Insert at the tail of a list with one element
        ll.insertAtTail("B");

        // Verify the list after insertion
        assertEquals("(A, B)", ll.toString());
        assertEquals(2, ll.getLength());
    }

    @Test
    public void testInsertAtTailInListWithMultipleElements() {
        // Create a linked list with multiple elements
        LinkedList ll = new LinkedList();
        ll.insertAtHead("A");
        ll.insertAtTail("B");
        ll.insertAtTail("C");
        ll.insertAtTail("D");
        ll.insertAtTail("E");
        ll.insertAtTail("F");
        ll.insertAtTail("G");

        // Verify the list after insertion
        assertEquals("(A, B, C, D, E, F, G)", ll.toString());
        assertEquals(7,ll.getLength());
    }


    @Test
    public void testIndexOfInEmptyList() {
        // Create an empty linked list
        LinkedList list = new LinkedList();

        // Test indexOf in an empty list
        assertEquals(-1, list.indexOf("A"));
        assertEquals(-1, list.indexOf("B"));
        assertEquals(-1, list.indexOf("C"));
    }

    @Test
    public void testIndexOfInListWithOneElement() {
        // Create a linked list with one element
        LinkedList list = new LinkedList();
        list.insertAtHead("A");

        // Test indexOf in a list with one element
        assertEquals(0, list.indexOf("A"));
        assertEquals(-1, list.indexOf("B"));
        assertEquals(-1, list.indexOf("C"));
    }

    @Test
    public void testIndexOfInListWithMultipleElements() {
        // Create a linked list with multiple elements
        LinkedList list = new LinkedList();
        list.insertAtHead("A");
        list.insertAtTail("B");
        list.insertAtTail("C");
        list.insertAtTail("D");
        list.insertAtTail("E");
        list.insertAtTail("F");
        list.insertAtTail("G");

        // Test indexOf in a list with multiple elements
        assertEquals(0, list.indexOf("A"));
        assertEquals(1, list.indexOf("B"));
        assertEquals(2, list.indexOf("C"));
        assertEquals(3, list.indexOf("D"));
        assertEquals(4, list.indexOf("E"));
        assertEquals(6, list.indexOf("G"));
        assertEquals(-1, list.indexOf("Z"));


    }

    @Test
    public void testIndexOfWithDuplicateValues() {
        // Create a linked list with duplicate values
        LinkedList list = new LinkedList();
        list.insertAtHead("A");
        list.insertAtHead("B");
        list.insertAtHead("A");

        // Test indexOf with duplicate values
        assertEquals(0, list.indexOf("A"));
        assertEquals(1, list.indexOf("B"));
        assertEquals(-1, list.indexOf("C"));
    }

    @Test
    public void testInsertAtIndex() {
        LinkedList list = new LinkedList();
        list.insertAtHead("A");
        list.insertAtHead("B");
        list.insertAtHead("C");
        
        // Test inserting at index 0
        list.insertAtIndex(0, "X");
        assertEquals("(X, C, B, A)", list.toString());
        
        // Test inserting in the middle
        list.insertAtIndex(2, "Y");
        assertEquals("(X, C, Y, B, A)", list.toString());
        
        // Test inserting at the end
        list.insertAtIndex(5, "Z");
        assertEquals("(X, C, Y, B, A, Z)", list.toString());
        
        // Test inserting at index equal to length (append)
        list.insertAtIndex(6, "W");
        assertEquals("(X, C, Y, B, A, Z, W)", list.toString());
    }
    
    @Test
    public void testInsertAtIndexEmptyList() {
        LinkedList list = new LinkedList();
        
        // Test inserting into an empty list
        list.insertAtIndex(0, "X");
        assertEquals("(X)", list.toString());
    }
    
    @Test
    public void testInsertAtIndexInvalidIndex() {
        LinkedList list = new LinkedList();
        list.insertAtHead("A");
        list.insertAtHead("B");
        
        // Test inserting at index out of bounds
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.insertAtIndex(3, "X");
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.insertAtIndex(-1, "X");
        });


    }

    @Test
    public void testGetIthItem() {
        LinkedList list = new LinkedList();
        list.insertAtHead("A");
        list.insertAtHead("B");
        list.insertAtHead("C");
        
        // Test getting the first item (index 0)
        assertEquals("C", list.getIthItem(0));
        
        // Test getting an item in the middle
        assertEquals("B", list.getIthItem(1));
        
        // Test getting the last item
        assertEquals("A", list.getIthItem(2));
    }
    
    @Test
    public void testGetIthItemIndexOutOfBounds() {
        LinkedList list = new LinkedList();
        list.insertAtHead("A");
        
        // Test getting item at index out of bounds
        assertNull(list.getIthItem(1));
    }
    
    @Test
    public void testGetIthItemEmptyList() {
        LinkedList list = new LinkedList();
        
        // Test getting item from an empty list
        assertNull(list.getIthItem(0));
    }

    @Test
    public void testClone() {
        LinkedList originalList = new LinkedList();
        originalList.insertAtHead("A");
        originalList.insertAtHead("B");
        originalList.insertAtHead("C");
        
        // Clone the original list
        LinkedList clonedList = originalList.clone();
        
        // Test that the cloned list is not the same object reference as the original list
        assertNotSame(originalList, clonedList);
        
        // Test that the data of each node in the cloned list matches the data of the corresponding node in the original list
        assertEquals(originalList.toString(), clonedList.toString());

        LinkedList emptyList = new LinkedList();
        LinkedList clonedEmptyList = emptyList.clone();

        assertEquals(emptyList.toString(), clonedEmptyList.toString());
    }

    @Test
    public void testEqualsSameObject() {
        LinkedList list = new LinkedList();
        list.insertAtHead("A");
        list.insertAtHead("B");
        list.insertAtHead("C");

        assertTrue(list.equals(list)); // Same object should be equal
    }

    @Test
    public void testEqualsNullObject() {
        LinkedList list = new LinkedList();
        list.insertAtHead("A");
        list.insertAtHead("B");
        list.insertAtHead("C");

        assertFalse(list.equals(null)); // Null object should not be equal
    }

    @Test
    public void testEqualsDifferentClass() {
        LinkedList list = new LinkedList();
        list.insertAtHead("A");
        list.insertAtHead("B");
        list.insertAtHead("C");

        assertFalse(list.equals("hello")); // Different class should not be equal
    }

    @Test
    public void testEqualsDifferentLists() {
        LinkedList list1 = new LinkedList();
        list1.insertAtHead("A");
        list1.insertAtHead("B");
        list1.insertAtHead("C");

        LinkedList list2 = new LinkedList();
        list2.insertAtHead("X");
        list2.insertAtHead("Y");
        list2.insertAtHead("Z");

        assertFalse(list1.equals(list2)); // Different lists should not be equal
    }

    @Test
    public void testEqualsEqualLists() {
        LinkedList list1 = new LinkedList();
        list1.insertAtHead("A");
        list1.insertAtHead("B");
        list1.insertAtHead("C");

        LinkedList list2 = new LinkedList();
        list2.insertAtHead("A");
        list2.insertAtHead("B");
        list2.insertAtHead("C");

        assertTrue(list1.equals(list2)); // Equal lists should be equal
    }

    @Test
    public void testEqualsUnequalLengthLists() {
        LinkedList list1 = new LinkedList();
        list1.insertAtHead("A");
        list1.insertAtHead("B");
        list1.insertAtHead("C");

        LinkedList list2 = new LinkedList();
        list2.insertAtHead("A");
        list2.insertAtHead("B");

        assertFalse(list1.equals(list2)); // Lists of unequal length should not be equal
    }

    @Test
    public void testEqualsEmptyLists() {
        LinkedList list1 = new LinkedList();
        LinkedList list2 = new LinkedList();

        assertTrue(list1.equals(list2)); // Empty lists should be equal
    }

    @Test
    public void testContainsEmptyList() {
        LinkedList list = new LinkedList();
        assertFalse(list.contains("A")); // Empty list should not contain any value
    }

    @Test
    public void testContainsSingleElement() {
        LinkedList list = new LinkedList();
        list.insertAtHead("A");
        assertTrue(list.contains("A")); // List with a single element should contain that value
        assertFalse(list.contains("B")); // List with a single element should not contain other values
    }

    @Test
    public void testContainsMultipleElements() {
        LinkedList list = new LinkedList();
        list.insertAtHead("A");
        list.insertAtHead("B");
        list.insertAtHead("C");

        assertTrue(list.contains("A")); // List should contain "A"
        assertTrue(list.contains("B")); // List should contain "B"
        assertTrue(list.contains("C")); // List should contain "C"
        assertFalse(list.contains("X")); // List should not contain "X"
    }

    @Test
    public void testContainsDuplicateElements() {
        LinkedList list = new LinkedList();
        list.insertAtHead("A");
        list.insertAtHead("A");

        assertTrue(list.contains("A")); // List with duplicate elements should contain "A"
    }









}
