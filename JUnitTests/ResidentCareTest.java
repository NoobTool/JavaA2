package JUnitTests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import main.*;


class ResidentCareTest {
	
	static Manager m = new Manager();
	
	@Test
	void idTest() {
		assertEquals(0,m.allotId("Doctor"));
	}
	
	@Test
	void addManagerTest() {

		m.addStaff("Manager");
		assertEquals(7730001,m.displayManagers());
	}
		 

	// Test to add a doctor to staff list
	@Test
	void addDoctorTest() {
		m.addStaff("Doctor");
		assertEquals(6830012,m.displayDoctors());
	}
	
	
	@Test
	void addNurseTest() {
		
		m.addStaff("Nurse");
		assertEquals((long)7830675,m.displayNurses());
		m.addStaff("Nurse");
		assertEquals((long)7830001,m.displayNurses());
	}
	
	
	// Adding a medicine block
	@Test
	void addMedicineBlockTest() {
		ArrayList<String> meds = new ArrayList<String>();
		meds.add("Paracetamol");
		meds.add("Tetramycin");
		MedicineBlock b = new MedicineBlock(meds);
		b.printMedicineBlock();
	}
	
	// Adding a prescription
	@Test
	void prescriptionTest() {
		ArrayList<String> meds = new ArrayList<String>();
		meds.add("Paracetamol");
		meds.add("Tetramycin");
		MedicineBlock b = new MedicineBlock(meds);
		Prescription p = new Prescription(b);
		
		ArrayList<String> meds2 = new ArrayList<String>();
		meds2.add("Crocin");
		meds2.add("Betadine");
		MedicineBlock b2 = new MedicineBlock(meds2);
		p.addInPrescription(b2);
		p.printPrescription();
		
	}
	
	// Adding a prescription to a patient by a doctor
	@Test
	void addPrescriptionTest() {
		Doctor d = new Doctor();
		Patient p = new Patient(8030000,"Shyam",89,'M');
		d.addPrescription(p);
		p.printPrescription();
	}
	
	// Test to add a patient to patientList
	@Test
	void addPatient() {
		Patient p = new Patient(8030000,"Radhe",89,'M');
		p.displayPatients();
	}
	
	
	
}
