package proj5;

/**
 * Driver for the index maker project
 * 
 * @author Neil Daterao
 * @version 3/12/2024
 * @note I affirm that I have carried out the attached academic endeavors with full academic honesty, in
 * accordance with the Union College Honor Code and the course syllabus.
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
