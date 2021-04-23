import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;



class ResidentCareTest {
	
	Manager m1 = new Manager(112,"Ram",16,'M',"09:00-05:00");
	Manager m2 = new Manager(113,"Bhai",15,'M',"09:00-05:00");	
	
	@Test
	void addManagerTest() {

		m1.addStaff(m1);
		m2.addStaff(m2);
		assertEquals("Bhai",m2.displayManagers());
	}
	
	// Test to check if shift timings are in proper format
	@Test
	void shiftTimingsTest1() {
		
	}
	
	// Test to check if start_time < end_time
	@Test
	void shiftTimingsTest2() {
		
	}
	
	
	
	// Test to add a doctor to staff list
	@Test
	void addDoctorTest() {
		
		m1.addStaff("Doctor");
		assertEquals("Malhotra",m2.displayDoctors());
	}
	
	

	// Test to add a doctor to staff list
	@Test
	void addNurseTest() {
		
		m2.addStaff("Nurse");
		assertEquals("Aashi",m2.displayNurses());
	}
	
	// Test to add a patient to patientList
	@Test
	void addPatient() {
		fail("Not yet implemented!");
	}
	
	// Age test - float for ages<1 and integer for others
	@Test
	void ageTest() {
		fail("Not yet implemented!");
	}
	
	// Age test - Age>0 and Age<110
	@Test
	void ageTest2() {
		fail("Not yet implemented!");
	}
	
}
