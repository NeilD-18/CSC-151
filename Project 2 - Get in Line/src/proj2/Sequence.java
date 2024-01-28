package proj2;
/**
 *  Class modeling a Sequence Abstract Data Type (ADT)
 *
 *
 * @author Neil Daterao
 * @version 01/28/24
 * @Note I affirm that I have carried out the attached academic endeavors with full academic honesty, in
 * accordance with the Union College Honor Code and the course syllabus.
 *
 *
 * Invariant:
 * 1. Number of elements in the sequence is stored in the instance variable manyItems
 * 2. For an empty sequence, we don't care what is stored in data. For a nonempty sequence, the elements of the sequence
 *    are stored from the front to the end in data[0] to data[manyItems-1], we don't care what's stroed in the rest of
 *    data
 * 3. If there is a current element, then it lies in data[currentIndex]; if there is no current element, then
 *    currentIndex equals manyItems.
 */


public class Sequence
{
    private String[] data;
    private int manyItems;
    private int currentIndex;



    //Constants to avoid magic numbers
    private final int DEFAULTCAPACITY = 10;
    private final int INITIALAMOUNTOFITEMS = 0;





    /**
     * Creates a new sequence with initial capacity 10.
     */
    public Sequence() {
        try { data = new String[DEFAULTCAPACITY]; }
        catch (OutOfMemoryError memoryError){
            throw new OutOfMemoryError("Indicates insufficient memory for new Sequence");
        }
        manyItems = INITIALAMOUNTOFITEMS;
        currentIndex = manyItems;
    }
    

    /**
     * Creates a new sequence.
     * 
     * @param initialCapacity the initial capacity of the sequence.
     */
    public Sequence(int initialCapacity){
    	if (initialCapacity < 0) {
            throw new IllegalArgumentException("initialCapacity is negative: " + initialCapacity);
        }
        try { data = new String[initialCapacity]; }
        catch (OutOfMemoryError memoryError){
            throw new OutOfMemoryError("Indicates insufficient memory for new Sequence");
        }
        manyItems = INITIALAMOUNTOFITEMS;
        currentIndex = manyItems;


    }
    

    /**
     * Adds a string to the sequence in the location before the
     * current element. If the sequence has no current element, the
     * string is added to the beginning of the sequence.
     *
     * The added element becomes the current element.
     *
     * If the sequences's capacity has been reached, the sequence will
     * expand to twice its current capacity plus 1.
     *
     * @param value the string to add.
     */
    public void addBefore(String value)
    {
        if (getCapacity() <= size()) { ensureCapacity(2*getCapacity() + 1); }
        if (isCurrent()) {
            for (int index = manyItems; index > currentIndex; index--) {
                data[index] = data[index-1];
            }
            data[currentIndex] = value;
            manyItems++;
        }
        else {
            for (int index = manyItems; index > 0; index--) {
                data[index] = data[index-1];
            }
            data[0] = value; //added to beginning of sequence
            manyItems++;
            currentIndex = 0; //added element becomes current element
        }

        
    }
    
    
    /**
     * Adds a string to the sequence in the location after the current
     * element. If the sequence has no current element, the string is
     * added to the end of the sequence.
     *
     * The added element becomes the current element.
     *
     * If the sequences's capacity has been reached, the sequence will
     * expand to twice its current capacity plus 1.
     *
     * @param value the string to add.
     */
    public void addAfter(String value)
    {
        if (getCapacity() <= size()) { ensureCapacity(2*getCapacity() + 1); }
        if (isCurrent()) {
            int indexAfterCurrent = currentIndex + 1;
            for (int index = manyItems; index > indexAfterCurrent; index--) {
                data[index] = data[index-1];
            }
            data[indexAfterCurrent] = value;
            manyItems++;
            advance(); //added element becomes the current element
        }
        else {
            for (int index = manyItems; index > 0; index--) {
                data[index] = data[index-1];
            }
            data[0] = value; //added to beginning of sequence
            manyItems++;
            currentIndex = 0; //added element becomes current element
        }

    }

    
    /**
     * @return true if and only if the sequence has a current element.
     */
    public boolean isCurrent()
    {

    }
    
    
    /**
     * @return the capacity of the sequence.
     */
    public int getCapacity()
    {
        return data.length;
    }

    
    /**
     * @return the element at the current location in the sequence, or
     * null if there is no current element.
     */
    public String getCurrent()
    {
        return data[currentIndex];
    }
    
    
    /**
     * Increase the sequence's capacity to be
     * at least minCapacity.  Does nothing
     * if current capacity is already >= minCapacity.
     *
     * @param minCapacity the minimum capacity that the sequence
     * should now have.
     */
    public void ensureCapacity(int minCapacity)
    {
        if (getCapacity() < minCapacity) {
            String[] newArray = new String[minCapacity];
            for (int index = 0; index < size(); index++) {
                newArray[index] = data[index];
            }
            data = newArray;
        }

    }

    
    /**
     * Places the contents of another sequence at the end of this sequence.
     *
     * If adding all elements of the other sequence would exceed the
     * capacity of this sequence, the capacity is changed to make room for
     * all of the elements to be added.
     * 
     * Postcondition: NO SIDE EFFECTS!  the other sequence should be left
     * unchanged.  The current element of both sequences should remain
     * where they are. (When this method ends, the current element
     * should refer to the same element that it did at the time this method
     * started.)
     *
     * @param another the sequence whose contents should be added.
     */
    public void addAll(Sequence another)
    {

    }

    
    /**
     * Move forward in the sequence so that the current element is now
     * the next element in the sequence.
     *
     * If the current element was already the end of the sequence,
     * then advancing causes there to be no current element.
     *
     * If there is no current element to begin with, do nothing.
     */
    public void advance()
    {
    }

    
    /**
     * Make a copy of this sequence.  Subsequence changes to the copy
     * do not affect the current sequence, and vice versa.
     * 
     * Postcondition: NO SIDE EFFECTS!  This sequence's current
     * element should remain unchanged.  The clone's current
     * element will correspond to the same place as in the original.
     *
     * @return the copy of this sequence.
     */
    public Sequence clone()
    {
        return new Sequence();
    }
   
    
    /**
     * Remove the current element from this sequence.  The following
     * element, if there was one, becomes the current element.  If
     * there was no following element (current was at the end of the
     * sequence), the sequence now has no current element.
     *
     * If there is no current element, does nothing.
     */
    public void removeCurrent()
    {

    }

    
    /**
     * @return the number of elements stored in the sequence.
     */
    public int size()
    {
        return manyItems;
    }

    
    /**
     * Sets the current element to the start of the sequence.  If the
     * sequence is empty, the sequence has no current element.
     */
    public void start()
    {
    }

    
    /**
     * Reduce the current capacity to its actual size, so that it has
     * capacity to store only the elements currently stored.
     */
    public void trimToSize()
    {
    }
    
    
    /**
     * Produce a string representation of this sequence.  The current
     * location is indicated by a >.  For example, a sequence with "A"
     * followed by "B", where "B" is the current element, and the
     * capacity is 5, would print as:
     * 
     *    {A, >B} (capacity = 5)
     * 
     * The string you create should be formatted like the above example,
     * with a comma following each element, no comma following the
     * last element, and all on a single line.  An empty sequence
     * should give back "{}" followed by its capacity.
     * 
     * @return a string representation of this sequence.
     */
    public String toString() 
    {
        return "";
    }
    
    /**
     * Checks whether another sequence is equal to this one.  To be
     * considered equal, the other sequence must have the same size
     * as this sequence, have the same elements, in the same
     * order, and with the same element marked
     * current.  The capacity can differ.
     * 
     * Postcondition: NO SIDE EFFECTS!  this sequence and the
     * other sequence should remain unchanged, including the
     * current element.
     * 
     * @param other the other Sequence with which to compare
     * @return true iff the other sequence is equal to this one.
     */
    public boolean equals(Sequence other) 
    {
        return true;
    }
    
    
    /**
     * 
     * @return true if Sequence empty, else false
     */
    public boolean isEmpty()
    {
        return ( manyItems == 0 );
    }
    
    
    /**
     *  empty the sequence.  There should be no current element.
     */
    public void clear()
    {
    }

}