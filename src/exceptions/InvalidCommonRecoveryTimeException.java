package exceptions;

public class InvalidCommonRecoveryTimeException extends RuntimeException{
	
	public InvalidCommonRecoveryTimeException(double recoveryTime) {
        super("Recovery time " + recoveryTime + " is invalid! Recovery time must be positive");
	
    }
	

}
