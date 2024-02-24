package proj4;

// Don't forget the Javadocs!
// Notice that the generic type parameter does NOT implement
// the Token interface.  Make sure you understand why it shouldn't
// (and see the StackTest class for a hint.  Or just ask me!)
public class Stack<T>
{
    
    private int count; 
    private int capacity; 
    private int capacityIncrement; 
    private T[] contents; 

    private final int DEFAULTCAPACITY = 10; 
    private final int DEFAULTCAPACITYINCREMENT = 5; 


    /**
     * Default constructor for Stack, initializes an empty stack. 
     */
    public Stack() {
        count = 0; 
        capacity = DEFAULTCAPACITY; 
        capacityIncrement = DEFAULTCAPACITYINCREMENT;
        contents = (T[]) new Object[capacity]; 
    
    }
   
    /**
     * 
     * @return True if stack is empty, false if not
     */
    public boolean isEmpty() {  
    	return (size() == 0);  //erase this line  
    }

    
    /**
     * Pushes an element onto the top of this stack.
     * 
     * @param toPush the element to be pushed onto this stack
     * @throws NullPointerException if the specified element is null and this stack does not permit null elements
     */
    public void push(T toPush) {
        if (toPush == null) {
            throw new NullPointerException("Cannot push null elements onto the stack");
        }
        ensureCapacity();
        contents[count++] = toPush;
     
    }
    
    /**
     * Removes the element at the top of this stack and returns it.
     * 
     * @return the element removed from the top of the stack
     * returns null if empty
     */ 
    public T pop() {
    	if (count == 0 ) { return null; }
        else { return contents[--count]; }
    } 
  
     /**
     * Retrieves, but does not remove, the element at the top of this stack.
     * 
     * @return the element at the top of the stack
     * returns null if empty
     */ 
    public T peek() {
    	if (count == 0 ) { return null; }
        else { return contents[count-1]; }
    } 
    
    /**
     * 
     * @return the size of the stack
     */
    public int size() {
    	return count;    
    }
     
    /**
 * Returns a string representation of this stack. The string representation
 * consists of the elements in the stack, enclosed in curly braces ("{}"),
 * with the top of the stack indicated by ">" and separated by commas.
 * The top element is listed first, followed by the subsequent elements
 * in the order they would be popped off the stack.
 *
 * @return a string representation of this stack
 */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{>");
        for (int i = size()-1; i >= 0; i--) {
            sb.append(contents[i]);
            if (i > 0) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * Private helper to ensure capacity of contents array for stack
     */
    private void ensureCapacity() { 
        if (count == capacity) { 
            capacity += capacityIncrement;
            T[] tempArray = (T[]) new Object[capacity];
            for (int i=0; i < size(); i++) { 
                tempArray[i] = contents[i];
            }
            contents = tempArray; 
        }
    }
    
} 
   

