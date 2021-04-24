package JUnitTests;
import static org.junit.jupiter.api.Assertions.*;
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
	
	// Test to add a patient to patientList
	@Test
	void addPatient() {
		fail("Not yet implemented!");
	}
	
}
