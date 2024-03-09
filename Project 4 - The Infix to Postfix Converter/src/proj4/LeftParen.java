package proj4;

/**
 * Class for Left Parenthesis Token
 * 
 * @author Neil Daterao
 * @version 2/24/2024
 */
public class LeftParen implements Token {

    public String toString() { return "("; }

    public String handle(Stack<Token> s) { 
        s.push(this);
        return ""; 
    }
    
}
