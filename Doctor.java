
public class Doctor extends Employee{
	
	Doctor(int id, String name, int age, char gender,String shifts){
		super(id,name,age,gender,shifts);
	}
	
	public void addPrescription() {
		System.out.println("Doctor Class");
	}
	
	public String retName() {
		return super.retName();
	}
	
}
