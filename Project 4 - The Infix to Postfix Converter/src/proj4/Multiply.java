package proj4;

/**
 * Class for Multiply token. 
 * 
 * @author Neil Daterao
 * @version 2/24/2024
 */
public class Multiply implements Token {
    
    public String toString() {
    	return "*";
    }
    
    public String handle(Stack<Token> s)
    {
    	StringBuilder output = new StringBuilder(); 
        //check all 3 contingencies for rule 3 of algorithm
        while (!s.isEmpty() && !(s.peek() instanceof LeftParen) && getTokenPrecedence(this) <= getTokenPrecedence(s.peek())) { 
            output.append(s.pop().toString()); 
        }
        s.push(this); 
        return output.toString(); 
    }

     /**
     * Returns precedence of a token
     * @param token
     * @return 3 if exponent, 2 if * or /, 1 if + or -
     */
    private int getTokenPrecedence(Token token) {
        if (token instanceof Exponent) {
            return EXPONENTPRECEDENCE;
        } else if (token instanceof Multiply || token instanceof Divide) {
            return MULTIPLYDIVIDEPRECEDENCE;
        } else if (token instanceof Plus || token instanceof Minus) {
            return PLUSMINUSPRECEDENCE;
        } else {
            return 0; // Default precedence for unknown tokens
        }
    }

}
