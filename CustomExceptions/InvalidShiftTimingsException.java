package CustomExceptions;

public class InvalidShiftTimingsException extends Exception{
	public InvalidShiftTimingsException(){
		super("Invalid timings!");
	}
}