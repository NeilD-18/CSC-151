package proj5;

/** 
 * Generic BinarySearchTree Node class. 
 * 
 * @author Neil Daterao
 * @version 3/08/2024
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
