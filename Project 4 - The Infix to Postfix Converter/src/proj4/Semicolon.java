package proj4;

/**
 * Class for Right Parenthesis Token.
 * 
 * @author Neil Daterao
 * @version 2/24/2024
 */
public class Semicolon implements Token {

    public String toString() { return ";"; }

    public String handle(Stack<Token> s) { 
        StringBuilder output = new StringBuilder();
        while (!s.isEmpty()) { output.append(s.pop().toString()); }
        return output.toString(); 
    }
    
}