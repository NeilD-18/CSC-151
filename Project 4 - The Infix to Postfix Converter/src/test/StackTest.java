package test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import proj4.Stack;

/**
 * 
 * Tests for generic stack class. Has tests for a string and integer stack. 
 * 
 * @author Neil Daterao
 * @version 2/22/2024
 *
 */
public class StackTest {

	@Rule
    public Timeout timeout = Timeout.millis(100);
	
    private Stack<String> stringStack;
    private Stack<Integer> intStack; 
    
    @Before
    public void setUp() throws Exception {
        stringStack = new Stack<String>();
        intStack = new Stack<Integer>(); 
    }

    @After
    public void tearDown() throws Exception {
        stringStack = null;
        intStack = null;
    }

    @Test
    public void testStackConstructor_toString () {
        assertEquals ("An empty stack. (> indicates the top of the stack)", "{>}", stringStack.toString());
    }
    
    @Test
    public void testStringStackPushOneOntoEmptyStack () {
        stringStack.push("A");
        assertEquals ("Pushing A onto an empty stack.", "{>A}", stringStack.toString().replaceAll("[ ]+", ""));
    }
    
    @Test
    public void testStringStackPushTwoOntoEmptyStack () {
        stringStack.push("A");
        stringStack.push("B");
        assertEquals ("Pushing first A and then B onto an empty stack.", "{>B,A}", stringStack.toString().replaceAll("[ ]+", ""));
    }
    
    @Test
    public void testStringStackPushThreeOntoEmptyStack () {
        stringStack.push("A");
        stringStack.push("B");
        stringStack.push("C");
        assertEquals ("Pushing first A, then B, then C onto an empty stack.", "{>C,B,A}", stringStack.toString().replaceAll("[ ]+", ""));
    }

    @Test
    public void testIsEmptyOnEmptyIntStack() {
        assertTrue("Expected the stack to be empty", intStack.isEmpty());
    }

    @Test
    public void testIsEmptyOnNonEmptyIntStack() {
        intStack.push(10);
        assertFalse("Expected the stack to be non-empty", intStack.isEmpty());
    }

    @Test
    public void testIsEmptyAfterPushAndPopOnIntStack() {
        intStack.push(10);
        assertFalse("Expected the stack to be non-empty after push", intStack.isEmpty());
        intStack.pop();
        assertTrue("Expected the stack to be empty after pop", intStack.isEmpty());
    }

    @Test
    public void testIsEmptyAfterPushAndPeekOnIntStack() {
        intStack.push(10);
        assertFalse("Expected the stack to be non-empty after push", intStack.isEmpty());
        intStack.peek();
        assertFalse("Expected the stack to be non-empty after peek", intStack.isEmpty());
    }

    @Test
    public void testPushElementOnIntStack() {
        intStack.push(10);
        assertEquals("Stack size should be 1 after pushing one element", 1, intStack.size());
        assertFalse("Stack should not be empty after pushing one element", intStack.isEmpty());
        assertEquals("The top element should be the one pushed", Integer.valueOf(10), intStack.peek());
    }

    @Test(expected = NullPointerException.class)
    public void testPushNullElementOnIntStack() {
        intStack.push(null);
    }

    @Test
    public void testPushMultipleElementsOnIntStack() {
        intStack.push(10);
        intStack.push(20);
        intStack.push(30);
        assertEquals("Stack size should be 3 after pushing multiple elements", 3, intStack.size());
        assertEquals("The top element should be the last one pushed", Integer.valueOf(30), intStack.peek());
    }


    @Test
    public void testPopFromEmptyIntStack() {
        assertNull("Pop should return null when the stack is empty", intStack.pop());
    }

    @Test
    public void testPopFromNonEmptyIntStack() {
        intStack.push(10);
        intStack.push(20);
        intStack.push(30);

        assertEquals("Pop should return the top element when the stack is non-empty", Integer.valueOf(30), intStack.pop());
        assertEquals("Stack size should decrease after pop", 2, intStack.size());
        assertEquals("The new top element should be the one pushed before the popped element", Integer.valueOf(20), intStack.peek());
    }

    @Test
    public void testPopUntilEmptyOnIntStack() {
        intStack.push(10);
        intStack.push(20);
        intStack.push(30);

        assertEquals("Pop should return the top element when the stack is non-empty", Integer.valueOf(30), intStack.pop());
        assertEquals("Pop should return the top element when the stack is non-empty", Integer.valueOf(20), intStack.pop());
        assertEquals("Pop should return the top element when the stack is non-empty", Integer.valueOf(10), intStack.pop());
        assertTrue("The stack should be empty after popping all elements", intStack.isEmpty());
    }


    @Test
    public void testPeekOnEmptyIntStack() {
        assertNull("Peek should return null when the stack is empty", intStack.peek());
    }

    @Test
    public void testPeekOnNonEmptyIntStack() {
        intStack.push(10);
        intStack.push(20);
        intStack.push(30);

        assertEquals("Peek should return the top element without removing it", Integer.valueOf(30), intStack.peek());
        assertEquals("Stack size should remain unchanged after peek", 3, intStack.size());
    }

    @Test
    public void testPeekDoesNotAffectIntStack() {
        intStack.push(10);
        intStack.push(20);
        intStack.push(30);

        intStack.peek();

        assertEquals("Stack size should remain unchanged after peek", 3, intStack.size());
        assertFalse("Stack should not be empty after peek", intStack.isEmpty());
    }

    @Test
    public void testPushElementOnStringStack() {
        stringStack.push("one");
        assertEquals("Stack size should be 1 after pushing one element", 1, stringStack.size());
        assertFalse("Stack should not be empty after pushing one element", stringStack.isEmpty());
        assertEquals("The top element should be the one pushed", "one", stringStack.peek());
    }

    @Test(expected = NullPointerException.class)
    public void testPushNullElementOnStringStack() {
        stringStack.push(null);
    }

    @Test
    public void testPushMultipleElementsOnStringStack() {
        stringStack.push("one");
        stringStack.push("two");
        stringStack.push("three");
        assertEquals("Stack size should be 3 after pushing multiple elements", 3, stringStack.size());
        assertEquals("The top element should be the last one pushed", "three", stringStack.peek());
    }

    @Test
    public void testPopFromEmptyStringStack() {
        assertNull("Pop should return null when the stack is empty", stringStack.pop());
    }

    @Test
    public void testPopFromNonEmptyStringStack() {
        stringStack.push("one");
        stringStack.push("two");
        stringStack.push("three");

        assertEquals("Pop should return the top element when the stack is non-empty", "three", stringStack.pop());
        assertEquals("Stack size should decrease after pop", 2, stringStack.size());
        assertEquals("The new top element should be the one pushed before the popped element", "two", stringStack.peek());
    }

    @Test
    public void testPopUntilEmptyOnStringStack() {
        stringStack.push("one");
        stringStack.push("two");
        stringStack.push("three");

        assertEquals("Pop should return the top element when the stack is non-empty", "three", stringStack.pop());
        assertEquals("Pop should return the top element when the stack is non-empty", "two", stringStack.pop());
        assertEquals("Pop should return the top element when the stack is non-empty", "one", stringStack.pop());
        assertTrue("The stack should be empty after popping all elements", stringStack.isEmpty());
    }

    @Test
    public void testPeekOnEmptyStringStack() {
        assertNull("Peek should return null when the stack is empty", stringStack.peek());
    }

    @Test
    public void testPeekOnNonEmptyStringStack() {
        stringStack.push("one");
        stringStack.push("two");
        stringStack.push("three");

        assertEquals("Peek should return the top element without removing it", "three", stringStack.peek());
        assertEquals("Stack size should remain unchanged after peek", 3, stringStack.size());
    }

    @Test
    public void testPeekDoesNotAffectStringStack() {
        stringStack.push("one");
        stringStack.push("two");
        stringStack.push("three");

        stringStack.peek();

        assertEquals("Stack size should remain unchanged after peek", 3, stringStack.size());
        assertFalse("Stack should not be empty after peek", stringStack.isEmpty());
    }


    
}