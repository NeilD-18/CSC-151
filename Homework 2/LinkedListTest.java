package hwk2;

import org.junit.*;
import org.junit.rules.Timeout;
import static org.junit.Assert.*;

public class LinkedListTest {

    @Rule // a test will fail if it takes longer than 1/10 of a second to run
    public Timeout timeout = Timeout.millis(100);

    @Test
    public void testRemoveHead() {
        LinkedList ll = new LinkedList();
        //Linked list with 3 elements
        ll.insertAtHead("A");
        ll.insertAtHead("B");
        ll.insertAtHead("C");
        ll.insertAtHead("D");
        ll.insertAtHead("E");
        ll.insertAtHead("F");
        ll.insertAtHead("G");


        ll.removeHead();
        String expected = "(F,E,D,C,B,A)";

        //test remove head
        assertEquals(expected, ll.toString());

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
        LinkedList list = new LinkedList();

        // Test removing from an empty list
        assertNull(list.removeHead());
    }

}
