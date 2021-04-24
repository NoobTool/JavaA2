package JUnitTests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.*;

import CustomExceptions.InputValidation;
import main.*;


class ResidentCareTest {
	
	Manager m = new Manager();
	
	@Test
	void idTest() {
		assertEquals(6830012,m.allotId("Doctor"));
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
		m.addStaff("Doctor");
		assertEquals(6830001,m.displayDoctors());
	}
	
	
	@Test
	void addNurseTest() {
		
		m.addStaff("Nurse");
		assertEquals((long)7830675,m.displayNurses());
	}
	
	// Test to add a patient to patientList
	@Test
	void addPatient() {
		fail("Not yet implemented!");
	}
	
}
