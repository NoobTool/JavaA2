package CustomExceptions;
import java.lang.Math;
import java.util.*;
import main.CommonCodes;

public class InputValidation {
	Scanner scan = new Scanner(System.in);
	CommonCodes c = new CommonCodes();
	public double validateAge(double age) {
		
		if(age<0 || age>110) {
			age = c.inputDouble();
			validateAge(age);
		}
		
		if(age>1)
			age = Math.round(age);
		
		return age;	
	}
}
