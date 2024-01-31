package hwk2;

import org.junit.*;
import org.junit.rules.Timeout;
import static org.junit.Assert.*;

/**
 * LinkedList testing class for RemoveHead, InsertAtTail and indexOf
 */

public class LinkedListTest {

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
        assertEquals("(F,E,D,C,B,A)", ll.toString());
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
        assertEquals("(A,B)", ll.toString());
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
        assertEquals("(A,B,C,D,E,F,G)", ll.toString());
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
}
