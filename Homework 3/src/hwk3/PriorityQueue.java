package hwk3;
/**
 * The priority queue ADT.  
 * 
 */

public class PriorityQueue<Element extends Comparable<Element>>
{
    private int  count;       // number of items in the PQ
    private int  capacity;    // the number of available array positions
    private int  capacityIncrement; // the amount to increase the capacity
                                                 // during array expansion
    private Object[] itemArray;    
    
    /** construct an initially empty PriorityQueue */  
    public PriorityQueue() 
    {  
      count = 0;                  // the empty priority queue has no items
      capacity = 10;                // but there is capacity for ten items
      capacityIncrement = 5;               // and the capacity will expand
      itemArray = new Object[capacity];    // in increments of five
    }                                                    // when necessary

    /**
     * number of items in the PQ
     * @return number of items in the PQ
     */
    public int size() 
    {
      return count;
    }
    
    /** inserts a new item into a priority queue
     * 
     * @param newItem item to be inserted
     */
    public void insert(Element newItem) 
    {
      // if the itemArray does not have enough capacity,
      // expand the itemArray by the capacity increment
         if (size() == capacity) 
         {
           capacity += capacityIncrement;
           Object[] tempArray = new Object[capacity];
           for (int i = 0; i < count; i++) {
             tempArray[i] = itemArray[i];
           }
           itemArray = tempArray;
         }
      
      // insert the newItem at the end of the current sequence of items
      // and increase the priority queue's count by one
           itemArray[count++] = newItem;
    }
  
    /** removes the highest priority item
     * 
     * @return highest priority element in the PQ 
     */
    @SuppressWarnings("unchecked")
    public Element remove() 
    {
      if (count == 0) {                     
        return null;  // if pq empty
      } 
      else {   // find the highest priority item's position
        int maxPosition = 0;
        Element maxItem = (Element)itemArray[0];
        for (int i = 1; i < count; i++) {
          if ( ((Element)itemArray[i]).compareTo(maxItem) > 0 ) {
            maxPosition = i;
            maxItem = (Element)itemArray[i];
          }                                // then move the last item into
        }                                           // the hole created by
        itemArray[maxPosition] = itemArray[--count];       // removing the
        return maxItem;                           // highest priority item
      }                           // and, return the highest priority item
    }
}
