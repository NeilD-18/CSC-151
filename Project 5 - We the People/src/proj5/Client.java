package proj5;

/**
 * Driver for the index maker project
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Client
{
    public static void main(String[] args)
    {
    	makeIndex("src/resources/uscons.txt"); //replace with correct path
    }
    
    /**
     * Makes an index out of fileName. Gradescope needs this function.
     * 
     * @param fileName path to text file that you want to index
     */
    public static void makeIndex(String fileName) {
    	
    	Index index = new Index(fileName); 
        index.createIndex(); 
    }
}
