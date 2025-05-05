package exceptions;

public class EmptyFieldsException extends Exception {
    public EmptyFieldsException() {
        super("Please fill out all fields!");
    }
}

