package proj5;

/**
 * Index class.
 * Indexes will only include words such that there are less than MAXPAGESINPAGELIST occurances of the word
 * in a file on different pages. Additionally, indexes will only contain words longer than MINWORDLENGTH 
 * Pages breaks should be denoted using "#" within file.
 * Words not included in index will be stored in a Dictionary. 
 * 
 * @author Neil Daterao
 * @version 3/09/2024 
 */
public class Index {
    
    private BinarySearchTree<IndexEntry> contents; 
    private FileReader reader; 
    private int currentPage; 
    private Dictionary dictionary; 
    
    public final int MINWORDLENGTH = 2;
    public final int MAXPAGESINPAGELIST = 4;
   

    /**
     * Constructor to initialize an index given a filePath
     * @param filePath
     */
    public Index(String filePath) { 
        dictionary = new Dictionary();
        contents = new BinarySearchTree<>(); 
        currentPage = 1; 
        reader = new FileReader(filePath);
        
    }

    /**
     * Driver code to create index. Will print the to the standard output after being created.
     */
    public void createIndex() { 
        String token; 
		while ((token = reader.nextToken()) != null) { 
            if (token.equals("#")) { nextPage(); }
            else { 
                if (token.length() > MINWORDLENGTH && !getDictionary().contains(token)) { 
                    if (this.contains(token)) { updateIndex(token); } 
                    else { this.add(token);  }
                }
            }

        }
        System.out.println(this.toString());
        System.out.println(dictionary);
    }

    /**
     * Getter for Dictionary
     * @return
     */
    public Dictionary getDictionary() { 
        return dictionary; 
    }

    /**
     * Private helper method to move to next page 
     */
    private void nextPage() { 
        currentPage++; 
    }

    /**
     * Private getter to get the current page
     * @return
     */
    private int getCurrentPage() { 
        return currentPage; 
    }

    /**
     * 
     * @param word
     * @return true if index contains word, false if not. 
     */
    public Boolean contains(String word) { 
        IndexEntry temp = new IndexEntry(word);
        return contents.search(temp); 
    }

    /**
     * 
     * @param word
     * @return Index Entry from index given a word
     */
    public IndexEntry getIndexEntry(String word) { 
        IndexEntry temp = new IndexEntry(word);
        return (IndexEntry)contents.get(temp).data; 
    }

    /**
     * Delete an index entry from an index given a word. 
     * Will also print "Deleting {IndexEntry} from index."
     * @param word 
     */
    public void deleteEntryFromIndex(String word) { 
        if (contains(word)) { 
            System.out.println("Deleting '" + getIndexEntry(word).toString() + "' from index.");
            IndexEntry temp = new IndexEntry(word);
            contents.delete(temp);
        }
    }

    /**
     * Adds a new word to the index with the page number. 
     * Handles merge cases so index is formatted properly
     * with no duplicate words and upper and lowercase versions
     * of the same words. 
     * @param word
     */
    public void add(String word){
        if (!this.contains(word)) { 
            
            if (isUpperCase(word)) { 
                String lowerCaseWord = word.toLowerCase();
                if (this.contains(lowerCaseWord)){
                    handleUpperCaseWordWithLowerCaseInIndex(lowerCaseWord);
                }
                else {  addNewEntry(word); }
            }
            else { 
                String upperCaseWord = capitalizeWord(word);
                if (this.contains(upperCaseWord)) { 
                    handleLowerCaseWordWithUpperCaseInIndex(upperCaseWord, word);
                }
                else { addNewEntry(word); }
            }
        }
    }

    /**
     * String version of Index. Each IndexEntry printed in ASCII Alphabetical order. 
     * Each IndexEntry on new lines. 
     * @return String verison of Index. 
     */
    public String toString() { 
        return contents.toStringReverseInOrderTraversal(); 
    }

    /**
     * Private helper method to check if a word is uppercase. 
     * An uppercase word is uppercase if the first letter is capitalized.
     * @param word
     * @return True if uppercase, false if not 
     */
    private Boolean isUpperCase(String word) { 
        return Character.isUpperCase(word.charAt(0)); 
    }

    /**
     * Private helper method to handle the case when word is lowercase and 
     * the uppercase word exists in the index already, copy the pages from 
     * the upper case word to the lower case pagelist, then add the current page.
     * @param upperCaseWord
     * @param word
     */
    private void handleLowerCaseWordWithUpperCaseInIndex(String upperCaseWord, String word){
        IndexEntry newEntry = new IndexEntry(word);
        PageList pageListForUppercaseWord = this.getIndexEntry(upperCaseWord).getPageList(); 
        PageList pageListForLowerCaseWord = newEntry.getPageList();
        pageListForLowerCaseWord.addPageList(pageListForUppercaseWord);
        contents.insert(newEntry);
        deleteEntryFromIndex(upperCaseWord);
        getDictionary().remove(upperCaseWord); //we don't want the uppercase word to be placed in dictionary
        updateIndex(word); 
    }

    /**
     * Private helper method to handle the case wher if 
     * word is uppercase and the lower case word exists in the index already. 
     * Add the current page to the lowercase pagelist.
     * @param lowerCaseWord
     */
    private void handleUpperCaseWordWithLowerCaseInIndex(String lowerCaseWord) { 
        updateIndex(lowerCaseWord);
    }


    /**
     * Private helper method to capitalize the first letter of a word
     * @param token
     * @return
     */
    private String capitalizeWord(String token) { 
        return Character.toUpperCase(token.charAt(0)) + token.substring(1);
    }

    /**
     * Private helper method to add a new entry to the index given a word
     * @param word
     */
    private void addNewEntry(String word) { 
        IndexEntry newEntry = new IndexEntry(word, getCurrentPage());
        contents.insert(newEntry);
    }

    /**
     * Private helper method to update the index.
     * @param token
     */
    private void updateIndex(String token) { 
        PageList pageListForWord = this.getIndexEntry(token).getPageList(); 
        if (!pageListForWord.containsPage(getCurrentPage())) { 
            if (pageListForWord.size() < MAXPAGESINPAGELIST) { 
                pageListForWord.addPage(getCurrentPage());
            } else { 
                deleteEntryFromIndex(token);
                getDictionary().add(token);
            }  
        }
    }
    
}
