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
