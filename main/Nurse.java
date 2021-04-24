package main;


public class Nurse extends Employee{
	
	Nurse(long id, String name, double age, char gender,String shifts){
		super(id,name,age,gender,shifts);
	}
	
	public void addPrescription() {
		System.out.println("Nurse Class");
	}
	
	// Getter functions
	
		public long retId() {
			return super.retId();
		}
		
		public String retName() {
			return super.retName();
		}
		
		
		public double retAge() {
			return super.retAge();
		}
		
		public char retGender() {
			return super.retGender();
		}
		
		public String retShifts() {
			return super.retShifts();
		}
	
}

