package main;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import Actions.Action;


public class Staff<P> {
	private ArrayList<P> members = new ArrayList<P>();
	
	// Adding staff when the program is executed for the first time.
	
	public void addStaff(P s){
		members.add(s);
	}
	
	public void addStaff(P s, long performerId, long receiverId){
		Manager manager = new Manager("Object to add actions");
		members.add(s);
		if(receiverId<8030000)
			manager.addAction(new Action(performerId,receiverId,"hiring",LocalDate.now(),LocalTime.now()));
		else
			manager.addAction(new Action(performerId,receiverId,"admission",LocalDate.now(),LocalTime.now()));
	}
	
	public int retSize() {
		return this.members.size();
	}
	
	public ArrayList<P> retStaff(){
		return this.members;
	}
	
}
