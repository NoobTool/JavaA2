package main;
import CommonSnippets.CommonCodes;
import CommonSnippets.OptionSequence;

public class ResidentCare {
	
	public static void main(String args[]) {
		
		// Variable Initialization
		int choice=0;
		CommonCodes c = new CommonCodes();
		Doctor d = new Doctor();
			
		
		// Objects for displaying options
		OptionSequence oq = new OptionSequence();
		
		do {
			System.out.println("1. Login as Manager");
			System.out.println("2. Login as Doctor");
			System.out.println("3. Login as Nurse");
			System.out.println("4. Signup as Patient");
			choice=c.inputInt("Enter your choice!");
			switch(choice) {
				case 1: 
						oq.managerSequence();
						break;
						
				case 2:
						d.doctorAddMedicines();
						break;
						
						
				default: System.out.println("Dobara enter krle :| ");
						
			}
		}while(choice!=5);
		
	}
}
