package main;

public class ResidentCare {
	
	public static void main(String args[]) {
		
		// Variable Initialization
		int choice=0;
		CommonCodes c = new CommonCodes();
			
		
		// Objects for displaying options
		OptionSequence oq = new OptionSequence();
		
		System.out.println("1. Login as Manager");
		System.out.println("2. Login as Doctor");
		System.out.println("3. Login as Nurse");
		choice=c.inputInt("Enter your choice!");
		switch(choice) {
			case 1: 
					oq.managerSequence();
					break;
					
			default: System.out.println("Dobara enter krle :| ");
					
		}
	}
}
