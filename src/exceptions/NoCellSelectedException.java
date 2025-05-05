package exceptions;

public class NoCellSelectedException extends RuntimeException {
	public NoCellSelectedException() {
		super("No cell selected!");
	}

}
