
public abstract class Person {
	
	private int id;
	private String name;
	private int age;
	private String gender;
	
	Person (int id,String name,String gender){
		this.id=id;
		this.name=name;
		this.gender=gender;
	}
	
	public int retId() {
		return this.id;
	}
	
	public String retName() {
		return this.name;
	}
	
	public int retAge() {
		return this.age;
	}
	
	public String retGender() {
		return this.gender;
	}
}
