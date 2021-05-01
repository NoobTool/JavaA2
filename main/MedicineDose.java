package main;
import java.util.ArrayList;
import java.time.LocalTime;
import CommonSnippets.CommonCodes;
import CustomExceptions.InputValidation;

public class MedicineDose {
	private String medicineName;
	private int dose;
	private ArrayList<LocalTime> times = new ArrayList<LocalTime>();
	
	public MedicineDose() {}
	
	public MedicineDose addMedicine() {
		CommonCodes c = new CommonCodes();
		String name = c.inputString("Enter the name of the medicine. ");
		this.medicineName = name;
		int doseNumber = c.inputInt("Enter the number of doses. ");
		this.dose = doseNumber;
		for(int i=0;i<doseNumber;i++) {
			times.add(c.inputTime("Enter the time for dose "+(i+1)));
		}
		return this;
	}
	
	public void printMedicineDose() {
		System.out.println("Medicine Name: "+this.retName()+"\n");
		
		for(int i=0;i<this.retDose();i++) {
			System.out.println("Dose "+(i+1)+": "+this.retTimes().get(i));
		}
			
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
