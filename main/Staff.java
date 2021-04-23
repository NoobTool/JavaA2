package main;
import java.util.*;


public class Staff<E> {
	ArrayList<E> members = new ArrayList<E>();
	
	public void addStaff(E s){
		members.add(s);
	}
	
	public int retSize() {
		return members.size();
	}
	
	public ArrayList<E> retStaff(){
		return members;
	}
	
}
