package CustomExceptions;

public class InvalidShiftTimingsException extends Exception{
	String errorMsg;
	
	public InvalidShiftTimingsException(String msg){
		super(msg);
		errorMsg=msg;
	}
	
	public String retMsg() {
		return errorMsg;
	}
	
}