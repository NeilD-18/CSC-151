package proj5;

/**
 * PageList class. Container of pages that can contain duplicates
 * 
 * @author Neil Daterao
 * @version 3/09/2024
 */
public class PageList {
    
    private LinkedList<Integer> contents; 

    /**
     * Default constructor, creates empty pageList
     */
    public PageList() { 
        contents = new LinkedList<>(); 
    }

    /**
     * @param pageNumber to add to pagelist
     */
    public void addPage(int pageNumber) { 
        contents.insertAtTail(pageNumber); 
    }

    /**
     * 
     * @param pageNumber to remove from pagelist
     * If pageNumber not in pagelist, will print "Page not in PageList"
     */
    public void removePage(int pageNumber) { 
        try { contents.removeAtIndex(contents.indexOf(pageNumber)); } 
        catch(IndexOutOfBoundsException e) { 
            System.out.println("Page not in PageList");
        }
    }

    /**
     * 
     * @param pageNumber
     * @return True if pageNumber in in list and false if not
     */
    public Boolean containsPage(int pageNumber) { 
        return contents.contains(pageNumber); 
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
     * Adds the contents of another pagelist to this pagelist. Does not change what is in other page list. 
     * @param otherPageList
     */
    public void addPageList(PageList otherPageList) { 
        LinkedList<Integer> otherList = otherPageList.contents.clone();
        while (!otherList.isEmpty()) { 
            addPage(otherList.removeHead());
        }
    }
    
    
    /**
     * @return String version of page list. Enclosed in Squiggly Brackets, seperated by commas. If consecutive pages, start and end are seperated by dashes.
     * Ex: {2,4,7,9}
     * Ex: {2-4,7} == {2,3,4,7}
     * 
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
    
        if (contents.size() > 0) {
            int start = contents.getIthItem(0);
            int end = start;
    
            for (int i = 1; i < contents.size(); i++) {
                if (contents.getIthItem(i) == end + 1) {
                    end++;
                } else {
                    if (start == end) {
                        sb.append(start);
                    } else {
                        sb.append(start).append("-").append(end);
                    }
    
                    sb.append(", ");
                    start = contents.getIthItem(i);
                    end = start;
                }
            }
    
            if (start == end) {
                sb.append(start);
            } else {
                sb.append(start).append("-").append(end);
            }
        }
    
        sb.append("}");
        return sb.toString();
    }
}
