package JUnitTests;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import main.*;
import ward.*;

class ResidentCareTest {
	
	static Manager m = new Manager();
	
	@Test
	void managerLoginTest() {
		Login l = new Login();
		// Valid Login id = 7730000
		// Valid Password = 1234
		
		assertEquals(true,l.managerLogin());
	}
	
	
	@Test
	void doctorLoginTest() {
		Login l = new Login();
		// Valid Login id = 6830000
		// Valid Password = 1234
		
		assertEquals(true,l.doctorLogin());
	}
	
	
	@Test
	void nurseLoginTest() {
		Login l = new Login();
		// Valid Login id = 7830000
		// Valid Password = 1234
		
		assertEquals(true,l.nurseLogin());
	}
	
	
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
	
	// Modifying details test
	@Test
	void modifyTest() {
		m.modifyDetails();
		m.displayDoctors();
	}
	
	// Adding patient in room test
	@Test
	void addPatientInRoom() {
		Room r = new Room();
		r.addPatient(new Patient(123,"Radhe",89,'F'));
		r.addPatient(new Patient(124,"Isha",42,'F'));
		r.addPatient(new Patient(123,"Piya",89,'F'));
		r.addPatient(new Patient(124,"Riya",42,'F'));
		r.addPatient(new Patient(123,"Radhe",89,'F'));
		r.addPatient(new Patient(124,"Isha",42,'F'));
		r.printRoomStatus();
	}
	
	// Adding patient in ward test
	@Test
	void addPatientInWard() {
		Ward w = new Ward();
		w.addPatient(new Patient(123,"Piya",89,'F'));
		w.addPatient(new Patient(123,"Riya",89,'F'));
		w.addPatient(new Patient(123,"Radhe",89,'M'));
		w.addPatient(new Patient(123,"Piya",89,'F'));
		w.addPatient(new Patient(123,"Riya",89,'F'));
		w.addPatient(new Patient(123,"Piya",89,'F'));
		w.addPatient(new Patient(123,"Riya",89,'F'));
		w.addPatient(new Patient(123,"Radhe",89,'M'));
		w.addPatient(new Patient(123,"Radhe",89,'M'));
		w.addPatient(new Patient(123,"Radhe",89,'M'));
		w.printWardStatus();
	}
	
	// Adding patient in ward by manager
	@Test
	void addPatientInWard2() {
		Patient p = new Patient(123,"Piya",89,'F');
		Patient p2 = new Patient(123,"Radhe",89,'M');
		m.addWard(p);
		System.out.println("\n\n");
		m.addWard(p2);
		p = new Patient(123,"Piya",89,'F');
		p2 = new Patient(123,"Radhe",89,'M');
		m.addWard(p);
		System.out.println("\n\n");
		m.addWard(p2);
		System.out.println("\n\n");
		p = new Patient(123,"Piya",89,'F');
		p2 = new Patient(123,"Radhe",89,'M');
		m.addWard(p);
		System.out.println("\n\n");
		m.addWard(p2);
		System.out.println("\n\n");
		p = new Patient(123,"Piya",89,'F');
		p2 = new Patient(123,"Radhe",89,'M');
		m.addWard(p);
		System.out.println("\n\n");
		m.addWard(p2);
		System.out.println("\n\n");
		p = new Patient(123,"Piya",89,'F');
		p2 = new Patient(123,"Radhe",89,'M');
		m.addWard(p);
		System.out.println("\n\n");
		m.addWard(p2);
		System.out.println("\n\n");
		p = new Patient(123,"Piya",89,'F');
		p2 = new Patient(123,"Radhe",89,'M');
		m.addWard(p);
		System.out.println("\n\n");
		m.addWard(p2);
		System.out.println("\n\n");
		p = new Patient(123,"Piya",89,'F');
		p2 = new Patient(123,"Radhe",89,'M');
		m.addWard(p);
		System.out.println("\n\n");
		m.addWard(p2);
		System.out.println("\n\n");
		p = new Patient(123,"Piya",89,'F');
		p2 = new Patient(123,"Radhe",89,'M');
		m.addWard(p);
		System.out.println("\n\n");
		m.addWard(p2);
		System.out.println("\n\n");
		p = new Patient(123,"Piya",89,'F');
		p2 = new Patient(123,"Radhe",89,'M');
		m.addWard(p);
		System.out.println("\n\n");
		m.addWard(p2);
		System.out.println("\n\n");
		p = new Patient(123,"Piya",89,'F');
		p2 = new Patient(123,"Radhe",89,'M');
		m.addWard(p);
		System.out.println("\n\n");
		m.addWard(p2);
		System.out.println("\n\n");
		p = new Patient(123,"Piya",89,'F');
		p2 = new Patient(123,"Radhe",89,'M');
		m.addWard(p);
		System.out.println("\n\n");
		m.addWard(p2);
		System.out.println("\n\n");
		p = new Patient(123,"Piya",89,'F');
		p2 = new Patient(123,"Radhe",89,'M');
		m.addWard(p);
		System.out.println("\n\n");
		m.addWard(p2);
		System.out.println("\n\n");
		p = new Patient(123,"Piya",89,'F');
		p2 = new Patient(123,"Radhe",89,'M');
		m.addWard(p);
		System.out.println("\n\n");
		m.addWard(p2);
		System.out.println("\n\n");
		p = new Patient(123,"Piya",89,'F');
		p2 = new Patient(123,"Radhe",89,'M');
		m.addWard(p);
		System.out.println("\n\n");
		m.addWard(p2);
		System.out.println("\n\n");
		p = new Patient(123,"Piya",89,'F');
		p2 = new Patient(123,"Radhe",89,'M');
		m.addWard(p);
		System.out.println("\n\n");
		m.addWard(p2);
		System.out.println("\n\n");
		p = new Patient(123,"Piya",89,'F');
		p2 = new Patient(123,"Radhe",89,'M');
		m.addWard(p);
		System.out.println("\n\n");
		m.addWard(p2);
		System.out.println("\n\n");
		p = new Patient(123,"Piya",89,'F');
		p2 = new Patient(123,"Radhe",89,'M');
		m.addWard(p);
		System.out.println("\n\n");
		m.addWard(p2);
		System.out.println("\n\n");
		
	}

	// Printing patient by bed details
	@Test
	void printPatientInBedTest() {
		Manager m = new Manager();
		m.addStaff("Patient");
		m.displayPatientInBed(1, 1, 1);
	}
	
}
