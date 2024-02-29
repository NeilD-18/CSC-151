package hwk4;

/** 
 * Binary Search Tree Abstract Data Type (ADT)
 * 
 * @author Neil Daterao
 * @version 2/29/2024
 */
public class BinarySearchTree
{
    private BSTNode root;
        
    public BinarySearchTree() {
      root=null; 
    }
    
    /**
    * inserts recursively.  I include this one so you can
    * make your own trees in the testing class
    * 
    * @param subroot inserts into subtree rooted at subroot
    * @param newNode node to insert
    * @return the BST rooted at subroot that has newNode inserted
    */
    private BSTNode recursiveInsert(BSTNode subroot, BSTNode newNode) {
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
    * inserts recursively. Use this in your JUnit tests to
    * build a starting tree correctly
    * 
    * @param newString String to insert
    */
    public void recursiveInsert(String newString){
      BSTNode newNode = new BSTNode(newString);
      root = recursiveInsert(root, newNode);
    }
    
    
    /**
    * Private recursive function to build the string version of the BST.   
    * 
    * @param subroot subroot of tree to print
    */
    private String print(BSTNode subroot)
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
    private int size(BSTNode subRoot) { 
      if (subRoot == null) { return 0; }    
      else { return 1 + size(subRoot.llink) + size(subRoot.rlink);  }  
      
    }
    

    /**
     * 
     * @param target value to search for
     * @return true if target is in BST and false if not 
     */
    public boolean search(String target) { 
        return search(target, root); 
    }

   
    /**
     * Private recursive method to search for target given a subroot in a BST
     * @param target
     * @param subRoot
     * @return true if target is in BST and false if not 
     */
    private boolean search(String target, BSTNode subRoot) { 
        if (subRoot == null) { return false; }  
        else if (subRoot.data.equals(target)) { return true; }

        else if (subRoot.data.compareTo(target) > 0 ){ 
            return search(target, subRoot.llink);
        }
        else { return search(target, subRoot.rlink); }
    }
    
    
    
    /**
     * Method that inserts value into a Binary Search Tree
     * @param value String value
     */
    public void insert(String value){ 
      BSTNode newNode = new BSTNode(value);   
      if (root == null) { root = newNode;  }
    
      else { 
        
        BSTNode parent = null;
        BSTNode runner = root; 
        
        while (runner != null) { 
          parent = runner; 
          if (runner.data.compareTo(value) > 0) { 
            runner = runner.llink; 
          }
          else { 
            runner = runner.rlink; 
          }
        }

        if (parent.data.compareTo(value) > 0) { parent.llink = newNode;  }
        else { parent.rlink = newNode; }
    
      }

    }

    
    
}
