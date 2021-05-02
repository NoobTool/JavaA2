package main;
import java.util.*;


public class Staff<P> {
	private ArrayList<P> members = new ArrayList<P>();
	
	public void addStaff(P s){
		members.add(s);
	}
	
	public int retSize() {
		return this.members.size();
	}
	
	public ArrayList<P> retStaff(){
		return this.members;
	}
	
}
