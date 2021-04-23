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
		//m2.addStaff(m2);
		assertEquals(7730000,m.displayManagers());
	}
	
	// Test to add a doctor to staff list
	@Test
	void addDoctorTest() {
		
		m.addStaff("Doctor");
		assertEquals(6830012,m.displayDoctors());
	}
	
	
	@Test
	void addDoctorTest2() {
		
		m1.addStaff("Doctor");
		assertEquals(6830001,m.displayDoctors());
	}
	
	@Test
	void addDoctorTest3() {
		
		m1.addStaff("Doctor");
		assertEquals(6830002,m.displayDoctors());
	}
	
	

	// Test to add a doctor to staff list
	@Test
	void addNurseTest() {
		
		m.addStaff("Nurse");
		assertEquals((long)7830001,m.displayNurses());
	}
	
	// Test to add a patient to patientList
	@Test
	void addPatient() {
		fail("Not yet implemented!");
	}
	
	@Test
	void displayDocs() {
		m.displayDoctors();
	}
	
}
