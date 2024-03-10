package proj5;

 public class Index {
    
    private BinarySearchTree<IndexEntry> contents; 
    private FileReader reader; 
    private int currentPage; 
    private final int MINWORDLENGTH = 2;
    private Dictionary<String> dictionary; 

    /**
     * Constructor to make an index given a filePath
     * @param filePath
     */
    public Index(String filePath) { 
        dictionary = new Dictionary<>();
        contents = new BinarySearchTree<>(); 
        currentPage = 1; 
        createIndex(filePath);
        
        
    }

    //come back to this...

    private void createIndex(String filePath) { 
        String token; 
		while (!(token = reader.nextToken()).equals("EOF")) { 
            if (token.equals("#")) { nextPage(); }
            else { 
                if (token.length() > MINWORDLENGTH && !dictionary.contains(token)) { 
                    
                }
            }

        }
    }

    private void nextPage() { 
        currentPage++; 
    }
}
