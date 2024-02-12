package proj3;

/**
 *  Fill this in.  Really.
 */
public class LinkedList
{
    private int length;
    private ListNode firstNode;

    /**
     * default constructor
     */
    public LinkedList()
    {
        length=0;
        firstNode=null;
    }

    /**
     * return number of items in list
     * @return number of items (nodes) in the list
     */
    public int getLength()
    {
        return length;
    }

    /**
     * tells if this linked list is empty or not
     * @return true if this list has no nodes; else false
     */
    public boolean isEmpty() {
        return getLength()==0;
    }

    /**
     * inserts given string at the start of the list
     * @param data string to insert
     */
    public void insertAtHead(String data)
    {
    	ListNode newnode = new ListNode(data);
        newnode.next=firstNode;
        firstNode=newnode;
        length++;
    }

    /**
     * returns printable version of this list
     * @return string version of the list
     */
	public String toString(){ 
		String toReturn = "(";
		ListNode runner = firstNode;
		while(runner != null){
			toReturn = toReturn + runner;
			runner = runner.next;
			if(runner != null){
				toReturn = toReturn + ", ";
			}
		}
		toReturn = toReturn + ")";
		return toReturn;
	}

}


