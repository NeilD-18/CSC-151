package proj2;
/**
 * Class modeling a Sequence Abstract Data Type (ADT)
 *
 * Invariant:
 * 1. Number of elements in the sequence is stored in the instance variable manyItems
 * 2. For an empty sequence, we don't care what is stored in data. For a nonempty sequence, the elements of the sequence
 *    are stored from the front to the end in data[0] to data[manyItems-1], we don't care what's stored in the rest of
 *    data
 * 3. If there is a current element, then it lies in data[currentIndex]; if there is no current element, then
 *    currentIndex equals manyItems.
 *
 * @author Neil Daterao
 * @version 01/29/24
 * @Note I affirm that I have carried out the attached academic endeavors with full academic honesty, in
 * accordance with the Union College Honor Code and the course syllabus.
 *
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
        try { data = new String[initialCapacity]; }
        catch (OutOfMemoryError memoryError){
            throw new OutOfMemoryError("Indicates insufficient memory for new Sequence");
        }
        manyItems = INITIALAMOUNTOFITEMS;
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
        if (isCurrent()) {
            shiftRightFromIndex(getCurrentIndex());
            data[getCurrentIndex()] = value;
            increaseItemCount();
        }
        else {
            int startingIndex = 0;
            shiftRightFromIndex(startingIndex);
            data[startingIndex] = value; //added to beginning of sequence
            increaseItemCount();
            setCurrentIndex(startingIndex); //added element becomes current element
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
            shiftRightFromIndex(indexAfterCurrent);
            data[indexAfterCurrent] = value;
            increaseItemCount();
            advance(); //added element becomes the current element
        }
        else {
            int endOfSequenceIndex = size(); // Sequence contents is from data[0] to data[manyItems-1], next available is data[manyItems].
            data[endOfSequenceIndex] = value;
            increaseItemCount();
            setCurrentIndex(endOfSequenceIndex); //added element becomes current element.

        }

    }

    /**
     * Private helper method to shift elements in sequence right from a given index
     * @param indexToShiftFrom
     */
    private void shiftRightFromIndex(int indexToShiftFrom) {
        for (int index = size(); index > indexToShiftFrom; index--) {
            data[index] = data[index - 1];
        }
    }

    
    /**
     * @return true if and only if the sequence has a current element.
     */
    public boolean isCurrent()
    {
        return (getCurrentIndex()< size());
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
        if (isCurrent()) { return data[getCurrentIndex()]; }
        else { return null; }
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
            duplicateElementsToNewArray(newArray);
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
            //we know based on invariant that last element is data[manyItems-1]
            //thus size()-2 ensures we don't pass the last element
            if (getCurrentIndex() < size() - 1 ) { currentIndex++; }
            else { noCurrentElement(); } //if no current element, currentIndex = manyItems, based on invariant.
        }
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
            //add all elements from sequence to cloned sequence
            for (int index = 0; index < size(); index++) {
                clonedSequence.addAfter(data[index]);
            }

            //set currentIndex's equal to each other.
            clonedSequence.start();
            for (int count = 0; count < getCurrentIndex(); count++) { clonedSequence.advance(); }

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
        if (isCurrent()) {
            String[] newArray = new String[getCapacity()];
            int newArrayIndex = 0;
            for (int index = 0; index < size(); index++) {
                if (index != getCurrentIndex()) { newArray[newArrayIndex++] = data[index]; }
            }
            decreaseItemCount();
            data = newArray;

        }

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
        if (!isEmpty()) { setCurrentIndex(0); }
        else { noCurrentElement(); }
    }

    
    /**
     * Reduce the current capacity to its actual size, so that it has
     * capacity to store only the elements currently stored.
     */
    public void trimToSize()
    {
        try {
            String[] newArray = new String[size()];
            duplicateElementsToNewArray(newArray);
            data = newArray;
        }
        catch (OutOfMemoryError memoryError){
            throw new OutOfMemoryError("Indicates insufficient memory for trim");
        }

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
                sb.append(">").append(data[index]);
            } else {
                sb.append(data[index]);
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
        if (this == other) { return true; } //see if objects are the same
        if (other == null) { return false; }

        //see if size and currentIndex are equal before running through each sequence
        if (size() == other.size() && getCurrentIndex() == other.getCurrentIndex() ) {
            Sequence clonedOther = other.clone();
            clonedOther.start();

            for (int index = 0; index < size(); index++) {

                if (!data[index].equals(clonedOther.getCurrent())) { return false; }
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
        return ( size() == 0 );
    }
    
    
    /**
     *  empty the sequence.  There should be no current element.
     */
    public void clear()
    {
        String[] emptyArray = new String[getCapacity()];
        data = emptyArray;
        clearItemCount();
        noCurrentElement(); //no current element.
    }

    /**
     * Private helper method to have no current element in sequence
     */
    private void noCurrentElement() {
        currentIndex = manyItems;
    }

    /**
     *
     * Private helper method to get current index.
     */
    private int getCurrentIndex() {
        return currentIndex;
    }

    /**
     * Private helper method to set current index
     * @param newIndex
     */
    private void setCurrentIndex(int newIndex) {
        currentIndex = newIndex;
    }

    /**
     * Private helper method to count of elements.
     */
    private void increaseItemCount(){
        manyItems++;
    }

    /**
     * Private helper method to count of elements.
     */
    private void decreaseItemCount(){
        manyItems--;
    }

    /**
     * Private helper method to duplicate elements to a new given array
     */
    private void duplicateElementsToNewArray(String[] array) {
        for (int index = 0; index < size(); index++) {
            array[index] = data[index];
        }
    }

    /**
     * Private helper method to clear many item count.
     */
    private void clearItemCount() {
        manyItems = 0;
    }



}