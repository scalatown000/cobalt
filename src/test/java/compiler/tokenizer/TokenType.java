package compiler.tokenizer;

public enum TokenType {

    /**
     * Absolutely nothing.
     */
    EMPTY,

    /**
     * A token. For example, ( ) = ,
     */
    TOKEN,

    /**
     * First character is a letter, any proceeding characters are letters or numbers.
     */
    IDENTIFIER,

    /**
     * Integer.
     */
    INTEGER_LITERAL,

    /**
     * Double
     */
    DOUBLE_LITERAL,

    /**
     * Anything enclosed in double quotes. "Hello" "1"
     */
    STRING_LITERAL,

    /**
     * AddBlock operator
     **/
    ADD_OPERATOR,

    /**
     * SubtractBlock operator
     **/
    SUBTRACT_OPERATOR,

    /**
     * MultiplyBlock operator
     **/
    MULTIPLY_OPERATOR,

    /**
     * DivideBlock operator
     **/
    DIVIDE_OPERATOR,

    /**
     * End a statement
     **/
    END_STATEMENT,

    /**
     * Colon
     **/
    COLON,

    /**
     * Smaller than
     **/
    SMALLER_THAN,

    /**
     * Smaller than or equal to
     **/
    SMALLER_THAN_EQUAL,

    /**
     * Larger than
     **/
    LARGER_THAN,

    /**
     * Larger than or equal to
     **/
    LARGER_THAN_EQUAL,

    /**
     * Equal to
     **/
    EQUAL_TO
}