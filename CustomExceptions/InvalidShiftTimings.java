package CustomExceptions;

public class InvalidShiftTimings extends Exception{
	public InvalidShiftTimings(){
		super("Invalid timings!");
	}
}