package proj4;

/**
 * Converter class which contains a void method convert, that converts an infix expression to a postfix expression.
 * 
 * @author Neil Daterao
 * @version 2/24/2024
 */
public class Converter {
    private FileReader reader;
    private Stack<Token> stack;
	private final String BUFFER = " --> ";

    /**
     * Non-default constructor; Gradescope needs this to run tests
     *
     * @param infile path to the input file
     */
    public Converter(String infile) {
        stack = new Stack<Token>();
        reader = new FileReader(infile);
	}

    /**
     * Converts infix expression to postfix expression, prints to System.out
     */
    public void convert() {
		StringBuilder infixExpression = new StringBuilder();
		StringBuilder postfixExpression = new StringBuilder(); 
		
		String token; 
		while (!(token = reader.nextToken()).equals("EOF")) { 
			Token tokenObj = createToken(token);
			if (tokenObj != null){
				if (!(tokenObj instanceof Semicolon)) { infixExpression.append(token); }
				postfixExpression.append(tokenObj.handle(stack));
				
				if (tokenObj instanceof Semicolon) { 
					printInfixAndPostfix(infixExpression.toString(), postfixExpression.toString());
					postfixExpression.setLength(0); //reset the infix and postfix stringbuilders to handle a new line
                	infixExpression.setLength(0);
				}
			}
			else { 
				infixExpression.append(token); 
				postfixExpression.append(token);  
			}
		}
    }

    /**
     * Creates a Token object based on the input string
     *
     * @param tokenStr input string representing a token
     * @return Token object created from the input string
	 * returns null if an Operand
     */
    private Token createToken(String tokenStr) {
        switch (tokenStr) {
            case "+":
                return new Plus();
            case "-":
                return new Minus();
            case "*":
                return new Multiply();
            case "/":
                return new Divide();
			case "^":
				return new Exponent();
            case "(":
                return new LeftParen();
            case ")":
                return new RightParen();
			case ";":
				return new Semicolon(); 
			default:
				return null;
        }
    }

	/**
	 * Prints both the infix and postfix string with a buffer, " --> "
	 * @param infix Infix String
	 * @param postfix Postfix String
	 */
	private void printInfixAndPostfix(String infix, String postfix) {
		String toPrint = infix + BUFFER + postfix;
		System.out.println(toPrint);
	}
}