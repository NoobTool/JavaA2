package prescription;
import java.util.ArrayList;
import java.time.LocalTime;
import CommonSnippets.*;

public class MedicineDose {
	private String medicineName;
	private int dose;
	private ArrayList<LocalTime> times = new ArrayList<LocalTime>();
	private CommonCodes c = new CommonCodes();
	
	public MedicineDose() {}
	
	public MedicineDose addMedicine(String name, int doseNumber, ArrayList<LocalTime> times) {
		this.medicineName = name;
		this.dose = doseNumber;
		this.times = times;
		return this;
	}
	
	public void printMedicineDose() {
		if(dose>0) {
			System.out.println("Medicine Name: "+this.retName()+"\n");
			
			for(int i=0;i<this.retDose();i++) {
				System.out.println("Dose "+(i+1)+": "+this.retTimes().get(i));
			}
		}			
	}
	
	
	public void printMedicineDose(int dose) {
		System.out.println("Medicine Name: "+this.retName()+"\n");
		System.out.println("Dose: "+this.retTimes().get(dose));			
	}
	
	// Function to input/delete times to be equal to doses
	public void alterTimes() {
		if(dose<times.size()) {
			while(dose!=times.size()) {
				for(int i=0;i<times.size();i++) {
					System.out.println((i+1)+". "+times.get(i));
				}
				try {
					times.remove(c.inputInt("Enter time to be deleted. ")-1);
					alterTimes();
					sortTimes();
				}catch(Exception e) {System.out.println("Incorrect choice! ");alterTimes();}
			}
		}
		else {
			while(dose!=times.size()) {
				for(int i=0;i<times.size();i++) {
					System.out.println((i+1)+". "+times.get(i));
				}
				times.add(c.inputTime("Enter new time. "));
				alterTimes();
				sortTimes();
			}
		}
	}
	
	public void changeTimes() {
		DisplayMenu dm = new DisplayMenu();
		CommonCodes c = new CommonCodes();
		int choice=0;
		do {
			dm.doctorDoseMenu();
			choice = c.inputInt("");
			
			switch(choice) {
				case 1: changeDoseTime();
						sortTimes();
						break;
						
				case 2: break;
						
			}
		}while(choice!=4);
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
	
	public void setDose(int n) {
		CommonCodes c =  new CommonCodes();
		while(n<=0) {
			System.out.println("Number of doses must be greater than 0.");
			n = c.inputInt("Enter again! ");
		}
		dose = n;
		if(dose!=times.size())
			alterTimes();
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
}
