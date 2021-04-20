
public abstract class Person {
	
	private String name;
	private int age;
	private char gender;
	
	Person (String name, int age, char gender){
		this.name=name;
		this.gender=gender;
		this.age=age;
	}
	
	public String retName() {
		return this.name;
	}
	
	public int retAge() {
		return this.age;
	}
	
	public char retGender() {
		return this.gender;
	}
}
