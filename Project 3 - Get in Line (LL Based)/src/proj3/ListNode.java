package proj3;

/**
 * The ListNode class is more data-specific than the LinkedList class.  It
 * details what a single node looks like.  This node has one data field,
 * holding a pointer to a String object. 
 * 
 * @author Neil Daterao
 * 
 */
public class ListNode
{
    public String data;
    public ListNode next;

    public ListNode(String newData) {
        data = newData;
        next = null;
    }
    
    public String toString(){
    	return data;
    }

}
