package proj4;

/**
 * Class for Right Parenthesis Token.
 * 
 * @author Neil Daterao
 * @version 2/24/2024
 */
public class RightParen implements Token {
 
    public String toString() { return ")"; }

    public String handle(Stack<Token> s) { 
        StringBuilder output = new StringBuilder();
        while (!(s.peek() instanceof LeftParen)) {
            output.append(s.pop().toString());
        }
        s.pop(); // Discard the left parenthesis
        return output.toString();
    }
    
}