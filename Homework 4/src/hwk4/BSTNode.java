package hwk4;

/** A not-very-reusable node class, since it only holds a String.
 *  But good enough for this hwk.
 * 
 * @author Chris Fernandes
 * @version 2/27/24
 */
public class BSTNode {

	public String data;
	public BSTNode llink;
	public BSTNode rlink;
	
	/**
	 * non-default constructor
	 * @param newKey string that node will hold
	 */
	public BSTNode(String newKey)
	{
		data = newKey;
		llink = null;
		rlink = null;
	}
	
	/**
	 * returns key as printable string
	 */
	public String toString()
	{
		return data;
	}
}
