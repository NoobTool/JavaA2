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
		System.out.println("\nMedicines: ");
		
		for(int k=0;k<medicines.size();k++) {
			if(k!=medicines.size()-1)
				System.out.print(medicines.get(k)+", ");
			else
				System.out.print(medicines.get(k));
		}
			
		
		System.out.println();
		for(int i=0;i<30;i++)
			System.out.print('-');
	}
	
	
	public LocalDate retDate() {
		return this.date;
	}
	
	public LocalTime retTime() {
		return this.time;
	}
}
