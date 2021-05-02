package main;
import java.util.*;
import java.time.LocalTime;
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
	
	public void printMedicineBlock() {
		
		System.out.println("\n\nDate: "+date+"\nTime: "+time);
		
		for(int k=0;k<medicines.size();k++) {
			medicines.get(k).printMedicineDose();
			
			System.out.println();
			for(int j=0;j<30;j++)
				System.out.print('-');
		}
						
		System.out.println();
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
