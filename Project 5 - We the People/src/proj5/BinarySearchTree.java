package proj5;

/** 
 * Binary Search Tree Abstract Data Type (ADT)
 * 
 * @author Neil Daterao
 * @version 2/29/2024
 */
public class BinarySearchTree<Element extends Comparable<Element>>
{
    private BSTNode<Element> root;
        
    public BinarySearchTree() {
        root=null; 
    }
    
    /**
    * Private helper method to insert a BSTNode recursively
    * 
    * @param subroot inserts into subtree rooted at subroot
    * @param newNode node to insert
    * @return the BST rooted at subroot that has newNode inserted
    */
    private BSTNode<Element> recursiveInsert(BSTNode<Element> subroot, BSTNode<Element> newNode) {
      if (subroot == null) {
        return newNode;
      }
      else if (newNode.data.compareTo(subroot.data) > 0) {
        subroot.rlink = recursiveInsert(subroot.rlink,newNode);
        return subroot;
      }
      else {  // newNode.data smaller than subroot.data, so newNode goes on left
        subroot.llink = recursiveInsert(subroot.llink,newNode);
        return subroot;
      }
    }
    
    /**
    * Inserts a new element into BST
    * 
    * @param newElement to insert
    */
    public void insert(Element newElement){
      BSTNode<Element> newNode = new BSTNode<>(newElement);
      root = recursiveInsert(root, newNode);
    }
    
    
    /**
    * Private recursive function to build the string version of the BST.   
    * 
    * @param subroot subroot of tree to print
    */
    private String print(BSTNode<Element> subroot)
    {   
      StringBuilder builder = new StringBuilder();
      
      if (subroot != null) {
          builder.append("(");
          builder.append(print(subroot.llink));
          builder.append(" " + subroot + " ");
          builder.append(print(subroot.rlink));
          builder.append(")");
      }
      
      return builder.toString();
    }
    
    
    /**
     *  Prints a parenthesized version of this tree that shows
     *  the subtree structure.  Every non-empty subtree is
     *  encased in parentheses. Example: (( A ) B ( C )) means
     *  B is the parent of A (left kid) and C (right kid).
     * @return String version of Binary Search Tree. 
     */
    public String toString() 
    {
      
      return print(root);
      
    }

    /**
     * 
     * @return number of data items (nodes) in the tree 
     */
    public int size() { 
        return size(root); 
    }

    /**
     * Private recursive method to return size of BST 
     * @param subRoot
     * @return 
     */
    private int size(BSTNode<Element> subRoot) { 
      if (subRoot == null) { return 0; }    
      else { return 1 + size(subRoot.llink) + size(subRoot.rlink);  }  
      
    }


    
    /**
     * 
     * @param target value to search for
     * @return true if target is in BST and false if not 
     */
    public boolean search(Element target) { 
        return search(target, root); 
    }

   
    /**
     * Private recursive method to search for target given a subroot in a BST
     * @param target
     * @param subRoot
     * @return true if target is in BST and false if not 
     */
    private boolean search(Element target, BSTNode<Element> subRoot) { 
        if (subRoot == null) { return false; }  
        else if (subRoot.data.equals(target)) { return true; }

        else if (subRoot.data.compareTo(target) > 0 ){ 
            return search(target, subRoot.llink);
        }
        else { return search(target, subRoot.rlink); }
    }

    
    /**
     * Deletes value from a tree, if value is not there, do nothing
     * @param value value to be deleted
     */
    public void delete(Element value) { 
       root = delete(root, value);
    }

    /**
	   * deletes value from tree rooted at subroot
	   *  @param subroot  root of tree to be deleted from
	   * @param value  element to delete
	   * @return pointer to tree rooted at subroot that has value removed
	   *
     */
	  private BSTNode<Element> delete(BSTNode<Element> subroot, Element value) {
        if (isEmpty(subroot)) { return null; }
        else if (subroot.data.compareTo(value) > 0) { 
          subroot.llink = delete(subroot.llink, value); 
          return subroot; 
        }
        else if (subroot.data.compareTo(value) < 0) { 
          subroot.rlink = delete(subroot.rlink, value);
          return subroot; 
        }

        else { 
          // Case 1: Node is a leaf, return null to delete it
          if (isEmpty(subroot.llink) && isEmpty(subroot.rlink)) { return null; }

          // Case 2: Node has only a right subtree, return right subtree
          else if (isEmpty(subroot.llink)) { return subroot.rlink; }

          // Case 3: Node has only a left subtree, return left subtree
          else if (isEmpty(subroot.rlink)) { return subroot.llink; }

          // Case 4: Node has 2 subtrees, locate successor.
          else { 
              BSTNode<Element> minNode = findMin(subroot.rlink);
              subroot.data = minNode.data;
              subroot.rlink = delete(subroot.rlink, subroot.data);
          }

      }

      return subroot; 
      
   
  }

    /**
     * Helper method to find the minimum value node in a subtree
     * @param node
     * @return
     */
    private BSTNode<Element> findMin(BSTNode<Element> subroot) {
      while (subroot.llink != null) {
          subroot = subroot.llink;
      }
      return subroot;
    }

    /**
     * Return true if a given subroot is empty and false if not
     * @param subRoot
     * @return
     */
    private Boolean isEmpty(BSTNode<Element> subRoot) { 
        return subRoot == null;
    }

  
    
}
