package proj5;

/**
 * Generic Dictionary class.
 * @author Neil Daterao
 * @version 3/09/2024 
 */
public class Dictionary<Element extends Comparable<Element>> {
   
    private BinarySearchTree<Element> contents; 
    
    /**
     * Constructs an empty dictionary.
     */
    public Dictionary() {
        contents = new BinarySearchTree<>();
    }

    /**
     * Adds a word to the dictionary. If dictionary already has word, nothing will happen.
     * @param word the word to be added to the dictionary
     */
    public void add(Element element) {
        contents.insert(element);
    }

    /**
     * Checks if a word is present in the dictionary.
     * @param element to be added
     * @return true if the element is present in the dictionary, false otherwise
     */
    public boolean contains(Element element) {
       return contents.search(element);
    }

    /**
     * Removes a word from the dictionary. If element is not there, do nothing. 
     * @param word the element to be removed from the dictionary
     */
    public void remove(Element element) {
        contents.delete(element);
    }

    /**
     * 
     * @return Size of dictionary
     */
    public int size() { 
        return contents.size();
    }

    /**
     * @return String version of dictionary in ascending order with a word per line. 
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dictionary:").append("\n");
        sb.append(contents.toStringReverseInOrderTraversal()); 
        return sb.toString();
    }


}

