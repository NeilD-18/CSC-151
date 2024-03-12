package proj5;

/**
 * Dictionary Class for Strings, doesn't allow for duplicates
 * @author Neil Daterao
 * @version 3/09/2024 
 */
public class Dictionary {
   
    private BinarySearchTree<String> contents; 
    
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
    public void add(String word) {
        contents.insert(word);
    }

    /**
     * Checks if a word is present in the dictionary.
     * @param String to be added
     * @return true if the String is present in the dictionary, false otherwise
     */
    public boolean contains(String word) {
       return contents.search(word);
    }

    /**
     * Removes a word from the dictionary. If String is not there, do nothing. 
     * @param word the String to be removed from the dictionary
     */
    public void remove(String word) {
        contents.delete(word);
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

