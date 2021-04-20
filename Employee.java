
public class Employee extends Person{
	private int id;
	private String shifts;
	
	Employee(int id, String name, int age,  char gender, String shifts){
		super(name,age,gender);
		this.id = id;
		this.shifts = shifts;
		
	}
}
