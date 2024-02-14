package proj3;

/**
 * Linked List is a collection of data nodes.  All methods here relate to
 * how one can manipulate those nodes.
 * 
 * @author Neil Daterao
 * @version 2/13/2024
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

    /** insert new String at linked list's head
     * 
     * @param newData the String to be inserted
     */
    public void insertAtHead(String newData)
    {
    	ListNode newnode = new ListNode(newData);
        if (isEmpty()) { firstNode=newnode; }
        else
        {
            newnode.next=firstNode;
            firstNode=newnode;
        }
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


    /** remove and return data at the head of the list 
     * 
     *  @return the String the deleted node contains.  Returns null if list empty.
     */
    public String removeHead()
    {
    	if (!isEmpty())
        {
            ListNode secondNode = firstNode.next;
            String deletedNodeContents = firstNode.data;
            firstNode = secondNode;
            length--;
            return deletedNodeContents;
        }
        else { return null; }
        
    }


    /** insert data at end of list
     * 
     * @param newData new String to be inserted
     */
    public void insertAtTail(String newData)
    {
        if (isEmpty()) { insertAtHead(newData); }

        else
        {
            ListNode newNode = new ListNode(newData);
            ListNode runner = firstNode;

            while (runner.next != null)
            {
                runner = runner.next;
                
            }
            runner.next = newNode; 
            length++; 
        }

    }


    /**
     * search for first occurrence of value and return index where found
     * 
     * @param value string to search for
     * @return index where string occurs (first node is index 0).  Return -1 if value not found.
     */
    public int indexOf(String value)
    {
        if (isEmpty()) { return -1; }

    	ListNode runner = firstNode;
        int index = 0;

        while (runner != null) {
            if (runner.data.equals(value)) { return index; }

            else
            {
                runner = runner.next;
                index++;
            }

        }
        return -1;
    }


    /**
     * returns the ith item in the list (where first item is index 0)
     * @return the data inside the node at given index. Return -1 if not there.
     */
    public String getIthItem(int index){
        ListNode runner = firstNode;
        while (runner!=null){
            if (index==0){
                return runner.data;
            }
            else {
                runner = runner.next;
                index--;
            }
        }
        return null;
    }

    /**
     * Alias for get length, maintain convention in Java Collections. 
     * @return size (amount of items in Linked List)
     */
    public int size() { return getLength(); }



    /**
     * Method to insert data at a specified index. 
     * @param index Index to be inserted at
     * @param newData 
     */
    public void insertAtIndex(int index, String newData) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    
        ListNode newNode = new ListNode(newData);
    
        
        if (index == 0) { insertAtHead(newData); } 
        else if (index == length) { insertAtTail(newData);}
        else {
            ListNode runner = firstNode;
            // Traverse the list to the node before the desired index
            for (int i = 0; i < index - 1; i++) {
                runner = runner.next;
            }
            // Insert the new node after the node at the desired index
            newNode.next = runner.next;
            runner.next = newNode;
        }
    
        length++; 
    }

    /**
     * Return a deepcopy of a linkedlist. 
     */
    public LinkedList clone() {
        LinkedList newList = new LinkedList(); 
        ListNode runner = firstNode; 
        
        while (runner != null) {
            newList.insertAtTail(runner.data); 
            runner = runner.next; 
        }
        
        return newList;
    }

    /**
     * Returns true if two objects are equal, false if not. 
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; 
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; 
        }
        LinkedList otherList = (LinkedList) obj; 
    
        if (this.size() != otherList.size()) {
            return false;
        }
    
        ListNode thisRunner = this.firstNode;
        ListNode otherRunner = otherList.firstNode;
    
        while (thisRunner != null) {          
            if (!thisRunner.data.equals(otherRunner.data)) {
                return false;
            }
            thisRunner = thisRunner.next;
            otherRunner = otherRunner.next;
        }
    
        return true; 
    }

    /**
     * 
     * @param value 
     * @return True if linked list contains value, false if not
     */
    public boolean contains(String value) {
        ListNode runner = firstNode;
        while (runner != null) {
            if (runner.data.equals(value)) {
                return true; 
            }
            runner = runner.next;
        }
        return false; 
    }








    

}


