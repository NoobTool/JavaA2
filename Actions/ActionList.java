package Actions;
import java.util.ArrayList;
import java.io.*;

public class ActionList implements Serializable{
	static ArrayList<Action> actionList = new ArrayList<Action>();
	
	public ActionList() {}
	
	public void addAction(Action a) {
		actionList.add(a);
	}
	
	public void last5Actions() {
		int count=5;
		try {
			for(int i=actionList.size();count>=1;i--) {
				actionList.get(i).printAction();
				count-=1;
			}
		}catch(ArrayIndexOutOfBoundsException e) {}
		
	}
	
	public void first5Actions() {
		int count=5;
		try {
			for(int i=0;count<5;i++) {
				actionList.get(i).printAction();
				count+=1;
			}
		}catch(ArrayIndexOutOfBoundsException e) {}
		
	}
	
	
	public void printSpecificAction(Action a) {
		a.printAction();
	}
	
	public void printActionList() {
		for(Action a: actionList)
			a.printAction();
	}
	
	// Print the actions performed by someone
	public void printPerformerActions(long id) {
		for(Action a: actionList) {
			if(a.retPerformerId()==id)
				a.printAction();
		}
	}
	
	// Print the actions performed on someone
	public void printPerformedActions(long id) {
		for(Action a: actionList) {
			if(a.retPerformerId()==id)
				a.printAction();
		}
	}
	
	// Print the actions performed by someone
	public void printPerformerActions(long id, int n) {
		for(Action a: actionList) {
			if(a.retPerformerId()==id && n>0) {
				a.printAction();
				n--;
			}
		}
	}
		
	// Print the actions performed on someone
	public void printPerformedActions(long id, int n) {
		for(Action a: actionList) {
			if(a.retPerformerId()==id && n>0) {
				a.printAction();
				n--;
			}	
		}
	}

	// Getter
	
	public ArrayList<Action> retActionList() {
		return actionList;
	}
	
	public void initActionList(ArrayList<Action> a) {
		actionList=a;
	}
	
	public int retSize() {
		return actionList.size();
	}
}


