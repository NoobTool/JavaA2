package main;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AdministerMedicine {
	private long patientId;
	private long staffId;
	private MedicineDose medicine;
	private LocalTime time;
	private int dose;
	
	AdministerMedicine(long patientId, long staffId, MedicineDose medicine, int dose, LocalTime time){
		this.patientId = patientId;
		this.staffId = staffId;
		this.medicine = medicine;
		this.dose = dose;
		this.time = time;
	}
	
	public void printMedicinesAdministered() {
		System.out.println("Id: "+patientId);
		medicine.printMedicineDose(dose);
		System.out.println("StaffId: "+staffId);
		System.out.println("Time Administered: "+time.format(DateTimeFormatter.ofPattern("HH:mm")));
	}
}
