package proj5;

/**
 * Linked List is a collection of data nodes.  All methods here relate to
 * how one can manipulate those nodes. Generic linked list class that can hold any element that extends the Comparable Interface.
 * 
 * @author Neil Daterao
 * @version 2/13/2024
 */
public class LinkedList<Element extends Comparable<Element>>
{
    private int length;
    private ListNode<Element> firstNode;

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

    /** insert new Element at LL head
     * 
     * @param newData 
     */
    public void insertAtHead(Element newData)
    {
    	ListNode<Element> newnode = new ListNode<>(newData);
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
		ListNode<Element> runner = firstNode;
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
     *  @return the Element the deleted node contains.  Returns null if list empty.
     */
    public Element removeHead()
    {
    	if (!isEmpty())
        {
            ListNode<Element> secondNode = firstNode.next;
            Element deletedNodeContents = firstNode.data;
            firstNode = secondNode;
            length--;
            return deletedNodeContents;
        }
        else { return null; }
        
    }


    /** insert data at end of list
     * 
     * @param newData new Element to be inserted
     */
    public void insertAtTail(Element newData)
    {
        if (isEmpty()) { insertAtHead(newData); }

        else
        {
            ListNode<Element> newNode = new ListNode<>(newData);
            ListNode<Element> runner = firstNode;

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
     * @param value Element to search for
     * @return index where Element occurs (first node is index 0).  Return -1 if value not found.
     */
    public int indexOf(Element value)
    {
        if (isEmpty()) { return -1; }

    	ListNode<Element> runner = firstNode;
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
     * @param index 
     * @return the data inside the node at given index. Return null if not there.
     */
    public Element getIthItem(int index){
        ListNode<Element> runner = firstNode;
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
    public void insertAtIndex(int index, Element newData) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        ListNode<Element> runner = firstNode;
        ListNode<Element> newNode = new ListNode<>(newData);
        
        if (index == 0) { insertAtHead(newData); }
        
        else {
            
            for (int i = 0; i < index - 1; i++) {
                runner = runner.next;
            }
        
            newNode.next = runner.next;
            runner.next = newNode; 
            length++; 
        }
            

    }

    /**
     * Return a deepcopy of a linkedlist. 
     */
    public LinkedList<Element> clone() {
        LinkedList<Element> newList = new LinkedList<>(); 
        ListNode<Element> runner = firstNode; 
        
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
        LinkedList<Element> otherList = (LinkedList<Element>) obj; 
    
        if (this.size() != otherList.size()) {
            return false;
        }
    
        ListNode<Element> thisRunner = this.firstNode;
        ListNode<Element> otherRunner = otherList.firstNode;
    
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
    public boolean contains(Element value) {
        ListNode<Element> runner = firstNode;
        while (runner != null) {
            if (runner.data.equals(value)) {
                return true; 
            }
            runner = runner.next;
        }
        return false; 
    }


    /**
     * Method to remove data at a specified index. 
     * @param index Index to be removed
     */
    public void removeAtIndex(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        if (index == 0) { removeHead(); } 
        else {
    
            ListNode<Element> previousNode = getNodeAtIndex(index - 1);
            ListNode<Element> currentNode = previousNode.next;
            previousNode.next = currentNode.next; //skip over currentNode. 
            length--;
        }

        
    }

    /**
     * Private helper/getter to get the node at a specified index
     * @param index given index
     * @return ListNode at given index. 
     */
    private ListNode<Element> getNodeAtIndex(int index) {
        ListNode<Element> current = firstNode;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    
    /**
     * Empty the LinkedList. 
     */
    public void clear() {
        firstNode = null; 
        length = 0; 
    }









    

}
