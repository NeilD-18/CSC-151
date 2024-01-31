package hwk2;

/**
 * ListNode is a building block for a linked list of data items
 * 
 * @author Neil Daterao
 * @version 1/31/2024
 */
public class ListNode
{
    public String data;      
    public ListNode next;  
    
    /** Non-default constructor
     * 
     * @param value data for this node
     */
    public ListNode(String value)
    {
        data = value;
        next = null;
    }
    
    /**
     * returns data as a printable string
     */
    public String toString()
    {
        return data;
    }
}
    