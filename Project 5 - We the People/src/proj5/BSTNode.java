package proj5;

/** A not-very-reusable node class, since it only holds a String.
 *  But good enough for this hwk.
 * 
 * @author Chris Fernandes
 * @version 2/27/24
 */
public class BSTNode<Element extends Comparable<Element>>{

	public Element data;
	public BSTNode<Element> llink;
	public BSTNode<Element> rlink;
	
	/**
	 * non-default constructor
	 * @param newKey string that node will hold
	 */
	public BSTNode(Element newKey)
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
		return data.toString();
	}
}
