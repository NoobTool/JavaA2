package prescription;
import java.util.ArrayList;
import java.io.Serializable;
import java.time.LocalTime;
import CommonSnippets.*;
 
public class MedicineDose implements Serializable{
	private String medicineName;
	private int dose;
	private ArrayList<LocalTime> times = new ArrayList<LocalTime>();
	
	public MedicineDose() {}
	
	public MedicineDose (String name, int doseNumber, ArrayList<LocalTime> times) {
		this.medicineName = name;
		this.dose = doseNumber;
		this.times = times;
	}
	
	public String printMedicineDose() {
		String returnValue="";
		if(dose>0) {
			returnValue+="\nMedicine Name: "+this.retName()+"\n";
			
			for(int i=0;i<this.retDose();i++) {
				returnValue+="\nDose "+(i+1)+": "+this.retTimes().get(i);
			}
		}
		return returnValue;
	}
	
	
	public void printMedicineDose(int dose) {
		System.out.println("Medicine Name: "+this.retName()+"\n");
		System.out.println("Dose: "+this.retTimes().get(dose));			
	}
	
	public void sortTimes()
	{
	    int i, j,n=times.size();
	    LocalTime key;
	    for (i=1;i<n;i++)
	    {
	        key = times.get(i);
	        j = i-1;

	        while (j >= 0 && times.get(j).isAfter(key))
	        {
	            times.set(j+1, times.get(j));
	            j = j - 1;
	        }
	        times.set(j+1,key);
	    }
	}
	
	
	public void changeDoseTime() {
		int choice=0,last_iteration;
		CommonCodes c = new CommonCodes();
		do{
			last_iteration = times.size()+1;
			for(int i=0;i<last_iteration-1;i++) {
				System.out.println((i+1)+". "+times.get(i));
			}
				
			choice = c.inputInt(last_iteration+". Exit. ");
			if(choice<last_iteration) {
				times.set(choice-1, c.inputTime("Enter the new time. "));
				sortTimes();
			}
			else if(choice==last_iteration){
				System.out.println("Exiting... ");
			}
			
			else {
				System.out.println("Wrong choice! ");
			}
		}while(choice!=last_iteration);
		
		
		
	}
	
	public void setName(String medicineName) {
		this.medicineName = medicineName;
	}
	
//	public void setDose(int n) {
//		CommonCodes c =  new CommonCodes();
//		while(n<=0) {
//			System.out.println("Number of doses must be greater than 0.");
//			n = c.inputInt("Enter again! ");
//		}
//		dose = n;
//		if(dose!=times.size())
//			alterTimes();
//	}
	
	public void setDose(int n) {
		this.dose = n;
	}
	
	public void setTime(int n,LocalTime time) {
		times.set(n,time);
	}
	
	public String retName() {
		return this.medicineName;
	}
	
	public int retDose() {
		return this.dose;
	}
	
	public ArrayList<LocalTime> retTimes(){
		return this.times;
	}
	
	public void removeTime(ArrayList<LocalTime> time) {
		times.removeAll(time);
	}
	
	public void addTime(ArrayList<LocalTime> time) {
		this.times.addAll(time);
		sortTimes();
	}
	
}
