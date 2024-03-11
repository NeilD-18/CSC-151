package proj5;

/**
 * Class for Index Entries. An index entry has data and a corresponding page list. 
 * @author Neil Daterao
 * @version 03/09/2024
 */
public class IndexEntry implements Comparable<IndexEntry> {
    
    private String data; 
    private PageList pageList; 

    /**
     * Constructor for a new Index Entry
     * @param data
     */
    public IndexEntry(String data) { 
        this.data = data; 
        pageList = new PageList(); 
    }
    
    /**
     * Constructor to initliaze an index entry with the data and the first page seen. 
     * @param data
     * @param firstPage
     */
    public IndexEntry(String data, int firstPage) { 
        this.data = data; 
        pageList = new PageList(); 
        pageList.addPage(firstPage);
    }


    /**
     * 
     * @return The word keeping track of
     */
    public String getWord() { 
        return data; 
    }

    /**
     * 
     * @return The page list of the word
     */
    public PageList getPageList() { 
        return pageList;
    }

    /**
     * CompareTo method
     * @param otherEntry other index entry
     * @return 1 if this is greater than other
     * -1 if other is greater than this
     *  0 if they are equal. 
     */
    public int compareTo(IndexEntry otherEntry) { 
        return this.getWord().compareTo(otherEntry.getWord());
    }

    /**
     * @return String version of Index Entry
     * example: 
     *  "java {3, 8}"
     */
    public String toString() { 
        return getWord().toString() + " " + pageList.toString();
        
    }
}
