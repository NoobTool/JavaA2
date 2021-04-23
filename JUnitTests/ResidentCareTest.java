package JUnitTests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.*;

import CustomExceptions.InputValidation;
import main.*;


class ResidentCareTest {
	
	Manager m = new Manager();
	Manager m1 = new Manager(112,"Ram",16,'M',"09:00-05:00");
	Manager m2 = new Manager(113,"Bhai",15,'M',"09:00-05:00");	
	
	@Test
	void idTest() {
		assertEquals(6830012,m.allotId("Doctor"));
	}
	
	
	@Test
	void addManagerTest() {

		m.addStaff(m1);
		//m2.addStaff(m2);
		assertEquals(113,m1.displayManagers());
	}
	
	// Test to add a doctor to staff list
	@Test
	void addDoctorTest() {
		
		m1.addStaff("Doctor");
		assertEquals(6830012,m2.displayDoctors());
	}
	
	

	// Test to add a doctor to staff list
	@Test
	void addNurseTest() {
		
		m.addStaff("Nurse");
		assertEquals((long)7830001,m2.displayNurses());
	}
	
	// Test to add a patient to patientList
	@Test
	void addPatient() {
		fail("Not yet implemented!");
	}
	
}
