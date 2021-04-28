package main;
import java.util.*;


public class Staff<Person> {
	private ArrayList<Person> members = new ArrayList<Person>();
	
	public void addStaff(Person s){
		members.add(s);
	}
	
	public int retSize() {
		return this.members.size();
	}
	
	public ArrayList<Person> retStaff(){
		return this.members;
	}
	
}
