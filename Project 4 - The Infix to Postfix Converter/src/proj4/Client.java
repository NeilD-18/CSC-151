package proj4;
/**
 * Client class that converts test proj4 input file from infix to postfix
 * 
 * @author Neil Daterao
 * @version 2/24/2024
 * @Note I affirm that I have carried out the attached academic endeavors with full academic honesty, in
 * accordance with the Union College Honor Code and the course syllabus.
 */

public class Client
{
    /**
     * Main method
     * @param args
     */
    public static void main(String[] args)
    {
       Converter converter = new Converter("src/resources/converter_test.txt");
       converter.convert();
      
    }

   
}
