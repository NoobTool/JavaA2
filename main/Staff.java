package main;
import java.util.*;


public class Staff<E> {
	private ArrayList<E> members = new ArrayList<E>();
	
	public void addStaff(E s){
		members.add(s);
	}
	
	public int retSize() {
		return this.members.size();
	}
	
	public ArrayList<E> retStaff(){
		return this.members;
	}
	
}
