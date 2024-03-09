package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import proj4.*;

/**
 * 
 * Tests for Tokens. Has 35 tests for various types of tokens and their respective handle methods
 * 
 * @author Neil Daterao
 * @version 2/22/2024
 *
 */
public class TokenTester {
    @Rule
    public Timeout timeout = Timeout.millis(100);
	
    private Stack<Token> stack; 
    
    @Before
    public void setUp() throws Exception {
        stack = new Stack<Token>();
    }

    @After
    public void tearDown() throws Exception {
        stack = null;
    }

      
    @Test
      public void testRightParenToString() {
          Token rightParen = new RightParen();
          assertEquals(")", rightParen.toString());
      }
      
      
    @Test
      public void testLeftParenToString() {
          Token leftParen = new LeftParen();
          assertEquals("(", leftParen.toString());
      }
      
      
    @Test
      public void testExponentToString() {
          Token exponent = new Exponent();
          assertEquals("^", exponent.toString());
      }

    @Test
      public void testPlusToString() { 
          Token plus = new Plus();
          assertEquals("+", plus.toString());
      }

    @Test
      public void testMinusToString() { 
          Token minus = new Minus();
          assertEquals("-", minus.toString());
      }
  
    @Test
      public void testMultiplyToString() { 
          Token multiply = new Multiply();
          assertEquals("*", multiply.toString());
      }
  
    @Test
      public void testDivideToString() { 
          Token divide = new Divide();
          assertEquals("/", divide.toString());
      }
    
    @Test
    public void testSemicolonToString() { 
        Semicolon sc = new Semicolon();
        assertEquals(";", sc.toString());
    }

    @Test
    public void testPlusHandleEmptyStack() {
        Plus plus = new Plus();
        
        assertEquals("Handle should return an empty string", "", plus.handle(stack));
        assertEquals("Handle an empty stack should push the Plus token onto the stack", "+", stack.peek().toString());
    }

    @Test
    public void testPlusHandleHigherPrecedence() {
        Plus plus = new Plus();
        
        stack.push(new Multiply()); // Higher precedence than Plus
        assertEquals("Handle when the top of stack contains elements of higher precedence should pop Multiply and add plus to stack", "*", plus.handle(stack));
        assertEquals("+", stack.peek().toString());
    }

    @Test
    public void testPlusHandleEqualPrecedence() {
        Plus plus = new Plus();
        
        stack.push(new Minus()); // Lower precedence than Plus
        assertEquals("Handle when the stack contains elements of equal precedence should pop minus and add plus to stack", "-", plus.handle(stack));
        assertEquals("+", stack.peek().toString());
    }

    @Test
    public void testPlussHandleLeftParenStopAtParen() {
        Plus plus = new Plus();
        
        stack.push(new LeftParen());
        stack.push(new Minus());
        assertEquals("Handle should not pop elements beyond LeftParen", "-", plus.handle(stack));
        assertEquals("Minus should be pushed onto the stack after LeftParen", "{>+, (}", stack.toString());
    }


   
    @Test
    public void testMinusHandleEmptyStack() {
        Minus minus = new Minus();
        
        assertEquals("Handle should return an empty string", "", minus.handle(stack));
        assertEquals("Handle an empty stack should push the Minus token onto the stack", "-", stack.peek().toString());
    }

    @Test
    public void testMinusHandleHigherPrecedence() {
        Minus minus = new Minus();
        
        stack.push(new Multiply()); // Higher precedence than Minus
        assertEquals("Handle when the top of stack contains elements of higher precedence should pop Multiply and add Minus to stack", "*", minus.handle(stack));
        assertEquals("-", stack.peek().toString());
    }

  
    @Test
    public void testMinusHandleEqualPrecedence() {
        Minus minus = new Minus();
        
        stack.push(new Plus()); // Lower precedence than Minus
        assertEquals("Handle when the stack contains elements of equal precedence should pop Plus and add Minus to stack", "+", minus.handle(stack));
        assertEquals("-", stack.peek().toString());
    }

    @Test
    public void testMinusHandleLeftParenStopAtParen() {
        Minus minus = new Minus();
        
        stack.push(new LeftParen());
        stack.push(new Plus());
        assertEquals("Handle should not pop elements beyond LeftParen", "+", minus.handle(stack));
        assertEquals("Minus should be pushed onto the stack after LeftParen", "{>-, (}", stack.toString());
    }

    @Test
    public void testMultiplyHandleEndUpOnStack() {
        Multiply multiply = new Multiply();
        
        assertEquals("Handle should return an empty string", "", multiply.handle(stack));
        assertEquals("Multiply object should end up on stack", "*", stack.peek().toString());
    }

    @Test
    public void testDivideHandleEndUpOnStack() {
        Divide divide = new Divide();
        
        assertEquals("Handle should return an empty string", "", divide.handle(stack));
        assertEquals("Divide object should end up on stack", "/", stack.peek().toString());
    }

    @Test
    public void testMultiplyHandleHigherPrecedence() {
        Multiply multiply = new Multiply();
        
        stack.push(new Exponent()); // Higher precedence than Multiply
        assertEquals("Handle when the top of stack contains elements of higher precedence should pop Exponent and add Multiply to stack", "^", multiply.handle(stack));
        assertEquals("*", stack.peek().toString());
    }

    @Test
    public void testDivideHandleHigherPrecedence() {
        Divide divide = new Divide();
        
        stack.push(new Exponent()); // Higher precedence than Divide
        assertEquals("Handle when the top of stack contains elements of higher precedence should pop Exponent and add Divide to stack", "^", divide.handle(stack));
        assertEquals("/", stack.peek().toString());
    }

    @Test
    public void testMultiplyHandleLowerPrecedence() {
        Multiply multiply = new Multiply();
        
        stack.push(new Plus()); //Lower precedence than Multiply
        assertEquals("Handle when the top of stack contains elements of lower precedence should add multiply to stack", "", multiply.handle(stack));
        assertEquals("*", stack.peek().toString());
        assertEquals("{>*, +}", stack.toString());
    }

    @Test
    public void testDivideHandleLowerPrecedence() {
        Divide divide = new Divide();
        
        stack.push(new Plus()); //Lower precedence than Divide
        assertEquals("Handle when the top of stack contains elements of lower precedence should add divide to stack", "", divide.handle(stack));
        assertEquals("/", stack.peek().toString());
        assertEquals("{>/, +}", stack.toString());
    }


    @Test
    public void testMultiplyHandleEqualPrecedence() {
        Multiply multiply = new Multiply();
        
        stack.push(new Divide()); //Lower precedence than Minus
        assertEquals("Handle when the top of stack contains elements of equal precedence should pop Divide and add multiply to stack", "/", multiply.handle(stack));
        assertEquals("*", stack.peek().toString());
        assertEquals("{>*}", stack.toString());
    }

    @Test
    public void testDivideHandleEqualPrecedence() {
        Divide divide = new Divide();
        
        stack.push(new Multiply()); //Lower precedence than Minus
        assertEquals("Handle when the top of stack contains elements of equal precedence should pop Multiply and add divide to stack", "*", divide.handle(stack));
        assertEquals("/", stack.peek().toString());
        assertEquals("{>/}", stack.toString());
    }

    @Test
    public void testMultiplyHandleStopAtLeftParen() {
        Multiply multiply = new Multiply();
        
        stack.push(new LeftParen());
        stack.push(new Exponent());
        assertEquals("Handle should not pop elements beyond LeftParen", "^", multiply.handle(stack));
        assertEquals("Plus should be pushed onto the stack after LeftParen", "{>*, (}", stack.toString());
    }

    @Test
    public void testDivideHandleStopAtLeftParen() {
        Divide divide = new Divide();
        
        stack.push(new LeftParen());
        stack.push(new Exponent());
        assertEquals("Handle should not pop elements beyond LeftParen", "^", divide.handle(stack));
        assertEquals("Plus should be pushed onto the stack after LeftParen", "{>/, (}", stack.toString());
    }

    @Test
    public void testExponentHandleEndUpOnStack() {
        Exponent exponent = new Exponent();
        
        assertEquals("Handle should return an empty string", "", exponent.handle(stack));
        assertEquals("Exponent object should end up on stack", "^", stack.peek().toString());
    }

    @Test
    public void testExponentHandleEqualPrecedence() {
        Exponent exponent = new Exponent();
        
        stack.push(new Exponent()); 
        assertEquals("Handle when the stack contains elements of equal precedence should pop exponent and add exponent to stack", "^", exponent.handle(stack));
        assertEquals("^", stack.peek().toString());
    }

    @Test
    public void testExponentHandleLowerPrecedence() {
        Exponent exponent = new Exponent();
        
        stack.push(new Multiply()); //Lower precedence than Divide
        assertEquals("Handle when the top of stack contains elements of lower precedence should add exponent to stack", "", exponent.handle(stack));
        assertEquals("^", stack.peek().toString());
        assertEquals("{>^, *}", stack.toString());
    }

    @Test
    public void testExponentsHandleLeftParenStopAtParen() {
        Exponent exponent = new Exponent();
        
        stack.push(new LeftParen());
        stack.push(new Exponent());
        assertEquals("Handle should not pop elements beyond LeftParen", "^", exponent.handle(stack));
        assertEquals("Exponent should be pushed onto the stack after LeftParen", "{>^, (}", stack.toString());
    }

    @Test
    public void testLeftParenHandleEmpty() { 
        LeftParen lp = new LeftParen();

        assertEquals("Handle should return empty string", "", lp.handle(stack));
        assertEquals("Left paren should be added to stack", "(", stack.peek().toString());
    }
    
    @Test
    public void testLeftParenHandleAfterFewOperations() { 
        LeftParen lp = new LeftParen();

        stack.push(new Exponent()); 
        stack.push(new LeftParen());
        stack.push(new Plus());
        assertEquals("Handle should return empty string", "", lp.handle(stack));
        assertEquals("Left paren should be added to stack", "(", stack.peek().toString());
        assertEquals("{>(, +, (, ^}", stack.toString());
    }

    @Test
    public void testRightParenHandle() {
        RightParen rightParen = new RightParen();
        
        stack.push(new LeftParen());
        stack.push(new Multiply());
        stack.push(new Plus());
        
        assertEquals("Returned string should be all items on stack down to LeftParen", "+*", rightParen.handle(stack));
        assertEquals("Stack should have all items down to LeftParen popped off", "{>}", stack.toString());
    }

    @Test
    public void testRightParenHandleTwoSetsOfLeftParen() {
        RightParen rightParen = new RightParen();
        
        stack.push(new LeftParen());
        stack.push(new Multiply());
        stack.push(new Plus());
        stack.push(new LeftParen());
        stack.push(new Multiply());
        stack.push(new Plus());
        
        assertEquals("Returned string should be all items on stack down to LeftParen", "+*", rightParen.handle(stack));
        assertEquals("Stack should have all items down to LeftParen popped off", "{>+, *, (}", stack.toString());
    }

    @Test
    public void testSemicolonHandle() {
        Semicolon semicolon = new Semicolon();
      
        stack.push(new Multiply());
        stack.push(new Plus());
        stack.push(new LeftParen());
        assertEquals("Semicolon should pop everything off the stack", "(+*", semicolon.handle(stack));
        assertEquals("Stack should be empty after semicolon handle", true, stack.isEmpty());
    }







}
