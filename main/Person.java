package main;

public abstract class Person {
	
	private String name;
	private double age;
	private char gender;
	
	Person(){}
	
	Person (String name, double age, char gender){
		this.name=name;
		this.gender=gender;
		this.age=age;
	}
	
	// Setter functions
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAge(double age) {
		this.age = age;
	}
	
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	
	// Getter Functions
	public String retName() {
		return this.name;
	}
	
	public double retAge() {
		return this.age;
	}
	
	public char retGender() {
		return this.gender;
	}
}
