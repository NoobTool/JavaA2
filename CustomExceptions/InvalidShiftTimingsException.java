package CustomExceptions;

public class InvalidShiftTimingsException extends Exception{
	public InvalidShiftTimingsException(String msg){
		super(msg);
	}
}