package proj3;  
/**
 *  Class modeling a Sequence Abstract Data Type (ADT). This is a refactored version of the Sequence class from proj2, this version is LinkedList based
 *  
 * Invariant: 
 * 1. The number of elements in the sequence is contents.size(), i.e the size of the LinkedList. 
 * 2. Sequence should not know about ListNodes.
 * 3. For an empty sequence, the contents LinkedList is empty. For a non-empty sequence, the elements of the sequence are linked from the front to the end.  
 * 4. If there is a current element, it lies in contents.getIthItem(currentIndex). If there is no current element, then currentIndex equals the size of contents.
 * 
 * 
 * @author Neil Daterao
 * @version 02/13/24
 * @Note I affirm that I have carried out the attached academic endeavors with full academic honesty, in
 * accordance with the Union College Honor Code and the course syllabus.
 *
 */
public class Sequence
{
	private LinkedList contents;
	private int capacity; 
    private int currentIndex; 

    private final int DEFAULTCAPACITY = 10;
	
   /**
     * Creates a new sequence with initial capacity 10.
     */
    public Sequence() {
        try { contents = new LinkedList(); }
        catch (OutOfMemoryError memoryError){
            throw new OutOfMemoryError("Indicates insufficient memory for new Sequence");
        }
        capacity = DEFAULTCAPACITY; 
        noCurrentElement();
    }
    

    /**
     * Creates a new sequence.
     * 
     * @param initialCapacity the initial capacity of the sequence.
     */
    public Sequence(int initialCapacity){
    	if (initialCapacity < 0) {
            initialCapacity = DEFAULTCAPACITY; //in the event of a negative input
        }
        try { contents = new LinkedList(); }
        catch (OutOfMemoryError memoryError){
            throw new OutOfMemoryError("Indicates insufficient memory for new Sequence");
        }
        capacity = initialCapacity; 
        noCurrentElement();


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
        if (isCurrent()) { contents.insertAtIndex(currentIndex, value); }
        else { 
            int startingIndex = 0;
            contents.insertAtHead(value);
            setCurrentIndex(startingIndex);
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
            int indexAfterCurrent = getCurrentIndex() + 1; 
            contents.insertAtIndex(indexAfterCurrent, value);
            setCurrentIndex(indexAfterCurrent);
        }
        else {
            int endOfSequenceIndex = size(); 
            contents.insertAtTail(value);
            setCurrentIndex(endOfSequenceIndex); //this index is now equal to size()-1, so it is the last valid index.
        }

    }

    
    /**
     * @return true if and only if the sequence has a current element.
     */
    public boolean isCurrent()
    {
        return (getCurrentIndex() < size() );
    }
    
    
    /**
     * @return the capacity of the sequence.
     */
    public int getCapacity()
    {
        return capacity; 
    }

    
    /**
     * @return the element at the current location in the sequence, or
     * null if there is no current element.
     */
    public String getCurrent()
    {
        if (isCurrent()) { return contents.getIthItem(getCurrentIndex()); }
        else { return null; }
    }
    
    
    /**
     * Increase the sequence's capacity to be
     * minCapacity.  Does nothing
     * if current capacity is already >= minCapacity.
     *
     * @param minCapacity the capacity that the sequence
     * should now have.
     */
    public void ensureCapacity(int minCapacity)
    {
        if (getCapacity() < minCapacity) { setCapacity(minCapacity); }
    }

    
    /**
     * Places the contents of another sequence at the end of this sequence.
     *
     * If adding all elements of the other sequence would exceed the
     * capacity of this sequence, the capacity is changed to make (just enough) room for
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
        ensureCapacity(size() + another.size());
        Sequence clonedAnother = another.clone();

        int currentIndexHolder = getCurrentIndex();
        boolean noCurrentBeforeAddAll = false;
        if (!isCurrent())  { noCurrentBeforeAddAll = true; }
        setCurrentIndex(size()-1);

        clonedAnother.start();
        for (int count = 0; count < clonedAnother.size(); count++) {
            addAfter(clonedAnother.getCurrent());
            clonedAnother.advance();
        }
        if (noCurrentBeforeAddAll) { noCurrentElement(); } //reset current index to prior to method call
        else { setCurrentIndex(currentIndexHolder); } //reset current index to prior to method call

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
        if (isCurrent()) { 
            if (getCurrentIndex() < size()) { currentIndex++; }
        }
        else { noCurrentElement(); }
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
        try {
            Sequence clonedSequence = new Sequence(getCapacity()); 
            LinkedList clonedContents = contents.clone(); 
            clonedSequence.contents = clonedContents; 
            clonedSequence.setCurrentIndex(getCurrentIndex());

            return clonedSequence;

        }
        catch (OutOfMemoryError memoryError){
            throw new OutOfMemoryError("Indicates insufficient memory for Cloned Sequence");
        }

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
        if (isCurrent()) { contents.removeAtIndex(getCurrentIndex());}
    }

    
    /**
     * @return the number of elements stored in the sequence.
     */
    public int size()
    {
        return contents.size();
    }

    
    /**
     * Sets the current element to the start of the sequence.  If the
     * sequence is empty, the sequence has no current element.
     */
    public void start()
    {
        if (!isEmpty()) { setCurrentIndex(0); }
        else { noCurrentElement(); }
    }

    
    /**
     * Reduce the current capacity to its actual size, so that it has
     * capacity to store only the elements currently stored.
     */
    public void trimToSize()
    {
        setCapacity(size());
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
        StringBuilder sb = new StringBuilder();

        sb.append("{");

        for (int index = 0; index < size(); index++) {
            // Add the current element with a ">" if it matches the current index
            if (index == getCurrentIndex()) {
                sb.append(">").append(contents.getIthItem(index));
            } else {
                sb.append(contents.getIthItem(index));
            }

            // Add a comma if it's not the last element
            if (index < size() - 1) {
                sb.append(", ");
            }
        }

        sb.append("} (capacity = ").append(getCapacity()).append(")");

        return sb.toString();


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
        if (this == other) { return true; } 
        if (other == null) { return false; }

        
        if (size() == other.size() && getCurrentIndex() == other.getCurrentIndex() ) {
            Sequence clonedOther = other.clone();
            
            clonedOther.start();
            for (int index = 0; index < size(); index++) {

                if (!contents.getIthItem(index).equals(clonedOther.getCurrent())) { return false; }
                else { clonedOther.advance(); }
            }
            return true;

        }

        else { return false; }
    }
    
    
    /**
     * 
     * @return true if Sequence empty, else false
     */
    public boolean isEmpty()
    {
        return contents.isEmpty();
    }
    
    
    /**
     *  empty the sequence.  There should be no current element.
     */
    public void clear()
    {
        contents.clear();
        noCurrentElement();
    }


    /**
     * Private helper method to have no current element in sequence
     */
    private void noCurrentElement() {
        currentIndex = contents.size(); 
    }

    /**
     *
     * Private getter method to get current index.
     */
    private int getCurrentIndex() {
        return currentIndex;
    }


     /**
     *
     * Private setter method to update capacity.
     */
    private void setCapacity(int newCapacityValue) { 
        capacity = newCapacityValue; 
    }

    /**
     * Private setter method to set current index
     * @param newIndex
     */
    private void setCurrentIndex(int newIndex) {
        currentIndex = newIndex;
    }



}