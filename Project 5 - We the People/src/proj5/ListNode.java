package proj5;

/**
 * Generic ListNode class.
 * The ListNode class is more data-specific than the LinkedList class.  It
 * details what a single node looks like.  This node has one data field.
 * @author Neil Daterao
 * @version 3/09/2024
 */
public class ListNode<Element extends Comparable<Element>> 
{ 

    public Element data;
    public ListNode<Element> next;

    /**
     * Constructor for a ListNode given an Element newData
     * @param newData
     */
    public ListNode(Element newData) {
        data = newData;
        next = null;
    }
    
    /**
     * Return a string version of the ListNode. 
     */
    public String toString(){
    	return data.toString();
    }


}

