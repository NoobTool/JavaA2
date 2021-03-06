package main;
import java.time.LocalTime;
import prescription.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class AdministerMedicine implements Serializable{
	private long patientId;
	private long staffId;
	private MedicineDose medicine;
	private LocalDate date;
	private LocalTime time;
	private int dose;
	
	AdministerMedicine(long patientId, long staffId, MedicineDose medicine, int dose,LocalDate date ,LocalTime time){
		this.patientId = patientId;
		this.staffId = staffId;
		this.medicine = medicine;
		this.dose = dose;
		this.date = date;
		this.time = time;
	}
	
	public void printMedicinesAdministered() {
		System.out.println("Id: "+patientId);
		medicine.printMedicineDose(dose);
		System.out.println("StaffId: "+staffId);
		System.out.println("Date Administered: "+date.format(DateTimeFormatter.ofPattern("dd-MM-YYYY")));  
		System.out.println("Time Administered: "+time.format(DateTimeFormatter.ofPattern("HH:mm")));
	}
	
	public MedicineDose retMedicine() {
		return medicine;
	}
	
	public LocalDate retDate() {
		return this.date;
	}
	
	public long retPatientId() {
		return this.patientId;
	}
	
}
