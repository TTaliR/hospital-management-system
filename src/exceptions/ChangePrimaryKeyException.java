package exceptions;

public class ChangePrimaryKeyException extends RuntimeException{
	
	public ChangePrimaryKeyException() {
		super("Can't change a primary key!");
	}


}
