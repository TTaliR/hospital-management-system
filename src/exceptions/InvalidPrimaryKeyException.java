package exceptions;

public class InvalidPrimaryKeyException extends RuntimeException {
    public InvalidPrimaryKeyException(String invalidKey) {
        super("The primary key \"" + invalidKey + "\" is invalid! Primary key must be a positive integer");
    }
}
