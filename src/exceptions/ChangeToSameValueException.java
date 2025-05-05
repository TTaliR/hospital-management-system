package exceptions;

public class ChangeToSameValueException extends RuntimeException{
	public ChangeToSameValueException() {
		super("Can't change to the same value!");
	}

}
