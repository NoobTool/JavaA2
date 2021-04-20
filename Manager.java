
public class Manager extends Person{
	
	Staff<Manager> s = new Staff<Manager>();
	
	Manager(int id,String name,String gender){
		super(id,name,gender);
		s.addStaff(this);
	}
	
	public void addStaff(int id, String name, String gender, String post){
		if (post=="Manager") {
			Manager m =  new Manager(id,name,gender);
		}
		
	}
	
	
	public String retName() {
		return super.retName();
	}
			
};
