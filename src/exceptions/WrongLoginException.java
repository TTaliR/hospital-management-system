package exceptions;


public class WrongLoginException extends RuntimeException{
	
	public WrongLoginException() {
		super("Wrong password or username.");
	}

}
