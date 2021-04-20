
public abstract class Person {
	
	private int id;
	private String name;
	private String gender;
	
	Person (int id,String name,String gender){
		this.id=id;
		this.name=name;
		this.gender=gender;
	}
	
	public String retName() {
		return this.name;
	}
}
