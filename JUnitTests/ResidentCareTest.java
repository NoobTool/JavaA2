package JUnitTests;
import static org.junit.jupiter.api.Assertions.*;
import application.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import main.*;
import ward.*;
import prescription.*;

class ResidentCareTest {
	
	static Manager m = new Manager();
	
	@Test
	void managerLoginTest() {
		Login l = new Login();
		boolean managerReturned=true;
		// Valid Login id = 7730000
		// Valid Password = 1234
	//	if(l.managerLogin().retName()==null)
			managerReturned=false;
		assertEquals(true,managerReturned);
	}
	
	
	@Test
	void doctorLoginTest() {
		Login l = new Login();
		boolean doctorReturned=true;
		// Valid Login id = 6830000
		// Valid Password = 1234
	//	if(l.doctorLogin().retName()==null)
			doctorReturned=false;
		assertEquals(true,doctorReturned);
	}
	
	
	@Test
	void nurseLoginTest() {
		Login l = new Login();
		boolean nurseReturned=true;
		// Valid Login id = 7730000
		// Valid Password = 1234
	//	if(l.nurseLogin().retName()==null)
			nurseReturned=false;
		assertEquals(true,nurseReturned);
	}
	
	
	@Test
	void idTest() {
		assertEquals(0,m.allotId("Doctor"));
	}
	
	@Test
	void addManagerTest() {

		m.addPeople("Manager");
		assertEquals(7730001,m.displayManagers());
	}
		 

	// Test to add a doctor to staff list
	@Test
	void addDoctorTest() {
		m.addPeople("Doctor");
		assertEquals(6830012,m.displayDoctors());
	}
	
	
	@Test
	void addNurseTest() {
		
		m.addPeople("Nurse");
		assertEquals((long)7830675,m.displayNurses());
		m.addPeople("Nurse");
		assertEquals((long)7830001,m.displayNurses());
	}
	

	// Adding a prescription
	@Test
	void prescriptionTest() {
		ArrayList<MedicineDose> meds = new ArrayList<MedicineDose>();
		MedicineDose md = new MedicineDose();
		meds.add(md.addMedicine());
		meds.add(md.addMedicine());
		MedicineBlock b = new MedicineBlock(meds);
		Prescription p = new Prescription(b);
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
	
	// Modifying details test
	@Test
	void modifyTest() {
	//	m.modifyDetails();
		m.displayDoctors();
	}
	
	// Modifying staff's shifts
	@Test
	void shiftsModifyTest() {
	//	m.modifyDetails();
		m.displayDoctors();
	}
	
	// Adding patient in room test
	@Test
	void addPatientInRoom() {
		Room r = new Room();
		for(int i=0;i<4;i++) {
			r.addPatient(new Patient(123,"Radhe",89,'F'));
			r.addPatient(new Patient(124,"Isha",42,'F'));
		}
		r.printRoomStatus();
	}
	
	// Adding patient in ward test
	@Test
	void addPatientInWard() {
		Ward w = new Ward();
		for(int i=0;i<6;i++) {
			w.addPatient(new Patient(123,"Piya",89,'F'));
			w.addPatient(new Patient(123,"Riya",89,'F'));
		}
		
		w.printWardStatus();
	}
	
	// Adding patient in ward by manager
	@Test
	void addPatientInWard2() {
		Patient p = new Patient(123,"Piya",89,'F');
		Patient p2 = new Patient(123,"Radhe",89,'M');
		
		for(int i=0;i<19;i++) {
			m.addWard(p);System.out.println("\n");
			m.addWard(p2);System.out.println("\n");
		}
		
		// Should display "Sorry, no space for you in the care centre! "
	}

	// Printing patient by bed details
	@Test
	void printPatientInBedTest() {
		Manager m = new Manager();
		m.addPeople("Patient");
		Doctor d = new Doctor();
		d.enterPatientBed(false);
	}
	
	
	@Test
	void enterPatientBedTest() {
		Doctor d = new Doctor();
		d.enterPatientBed(false);
		}
	
	//Change bed test
	@Test
	void changeBedNurse() {
		Manager m = new Manager();
		m.addPeople("Patient");
		Nurse n = new Nurse();
		//n.changeBed(n.enterPatientBed(true));
		//n.enterPatientBed(false);
		n.changeWardAutomatically(m.retPatientList().get(0));
		//n.changeWardAutomatically(m.retPatientList().get(0));
		n.enterPatientBed(false);
		n.enterPatientBed(false);
	}
	
	@Test
	void removePatientTest() {
		m.addPeople("Patient");
		Nurse n = new Nurse();
		n.removePatient(1, 1, 1);
	}
	
	// Add new medicine to current medicine block
	
	// Change number of doses/time of current medicine block
	@Test
	void updatePrescriptionTest() {
		Manager m = new Manager();
		m.addPeople("Patient");
		Doctor d = new Doctor();
		d.addPrescription(d.enterPatientBed(true));
		d.updatePrescription();
		d.enterPatientBed(false);
	}
	
	// Add new medicine block
	
	// When user changes number of doses, changeTimes is called without displaying
	// menu
	
	
	
	// Administer Medicine
	@Test
	void administerMedicineTest() {
		Nurse n = new Nurse();
		Manager m = new Manager();
		m.addPeople("Patient");
		Doctor d = new Doctor();
		d.addPrescription(m.retPatientList().get(0));
		n.administerMedicine();
		n.printAdministerations();
	}
		
	// Print Administered Medicines to a patient
	@Test
	void administeredMedicinesToPatient() {
		
	}
	
	
	/*  Patient details to be removed from all places 
		including administeredMedicinies in nurse
	*/
	@Test
	void removePatient() {
		fail("not yet implemented");
	}
	
	
	/*
	 	Archiving Patients details - include medicines administered too
	 */
	@Test
	void archivePatient() {
		fail("not yet implemented");
	}
	
	
	@Test
	void managerStartTest() {
		Main main = new Main();
		main.managerStart();
	}
	
}
