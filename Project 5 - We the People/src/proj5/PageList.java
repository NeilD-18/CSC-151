package proj5;

/**
 * PageList class
 * 
 * @author Neil Daterao
 * @version 3/09/2024
 */
public class PageList {
    
    private LinkedList<Integer> contents; 

    public PageList() { 
        contents = new LinkedList<>(); 
    }

    /**
     * 
     * @param pageNumber to add to pagelist
     */
    public void addPage(int pageNumber) { 
        contents.insertAtTail(pageNumber);
    }

    /**
     * 
     * @return Size of page list
     */
    public int size() { 
        return contents.size();
    }

    /**
     * 
     * @return true if pagelist is empty and false if not
     */
    public boolean isEmpty() {
        return contents.isEmpty();
    }
    
    
    
    /**
     * @return String version of page list. Enclosed in Squiggly Brackets, seperated by commas.
     * Ex: {1, 2, 3, 4}
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < contents.size(); i++) {
            sb.append(contents.getIthItem(i));
            if (i < contents.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
