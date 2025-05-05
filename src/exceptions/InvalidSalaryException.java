package exceptions;

public class InvalidSalaryException extends RuntimeException {
	public InvalidSalaryException() {
        super("Salary has to be positive!");
    }

}
