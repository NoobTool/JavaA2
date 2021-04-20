import java.util.*;


public class Staff<S> {
	ArrayList<S> members = new ArrayList<S>();
	
	public void addStaff(S s){
		members.add(s);
	}
	
	public void printStaff() {
		for(S x:members) {
			System.out.println(x);
		}
	}
}
