package prescription;
import java.util.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class MedicineBlock {
	LocalDate date = LocalDate.now();
	LocalTime time = LocalTime.now();
	ArrayList<MedicineDose> medicines = new ArrayList<MedicineDose>();
	
	public MedicineBlock() {}
	
	public MedicineBlock(ArrayList<MedicineDose> meds){
		this.date = LocalDate.now();
		this.time = LocalTime.now();
		this.medicines = meds;
	}
	
	public String printMedicineBlock() {
		String returnValue="";
		if(medicines.size()>0) {
			returnValue+="\n\nDate: "+date.format(DateTimeFormatter.ofPattern("dd-MM-YYYY"))+"\nTime: "+
		time.format(DateTimeFormatter.ofPattern("HH:mm"));
			
			for(int k=0;k<medicines.size();k++) {
				returnValue += medicines.get(k).printMedicineDose();
				
				returnValue+="\n";
				for(int j=0;j<20;j++)
					returnValue+="-";
				returnValue+="\n";
			}
		}
		return returnValue;
	}
	
	
	public LocalDate retDate() {
		return this.date;
	}
	
	public LocalTime retTime() {
		return this.time;
	}
	
	public ArrayList<MedicineDose> retMedicines(){
		return this.medicines;
	}
	
	
}
