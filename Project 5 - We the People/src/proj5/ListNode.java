package proj5;



/**
 * Generic ListNode class.
 * The ListNode class is more data-specific than the LinkedList class.  It
 * details what a single node looks like.  This node has one data field.
 */
public class ListNode<Element extends Comparable<Element>> 
{ 

    public Element data;
    public ListNode<Element> next;

    public ListNode(Element newData) {
        data = newData;
        next = null;
    }
    
    public String toString(){
    	return data.toString();
    }


}

