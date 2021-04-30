package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import CustomExceptions.*;

import org.junit.jupiter.api.Test;

class InputTests {
	
	InputValidation i = new InputValidation();
	Scanner scan = new Scanner(System.in);
	
	// Name test - Check must be greater>1
	@Test
	void nameTest() {
		System.out.println("\n\nNew name test with less chars!");
		String name = "";
		name = i.validateName(name);
		assertEquals(name,i.validateName(name));
	}
	
	// Check for digits or other characters in name
	@Test
	void nameTest2() {
		System.out.println("\n\nNew name test with numeric digits!");
		String name = "abc123";
		name = i.validateName(name);
		assertEquals(name,i.validateName(name));
	}
	
	// Another Check for other characters in name
	@Test
	void nameTest3() {
		System.out.println("\n\nNew name test with special chars!");
		String name = "abc@";
		name = i.validateName(name);
		assertEquals(name,i.validateName(name));
	}
	
	
	// Age test - float for ages<1 and rounded off integer for others
	@Test
	void ageTest() {
		double age = .5;
		assertEquals(.5,i.validateAge(age));
	}
	
	// Age test - Age>0 and Age<110
	@Test
	void ageTest2() {
		double age = 23.45;
		assertEquals(23,i.validateAge(age),0);
	}
	
	// Gender test - gender belongs to only M or F
	@Test
	void genderTest() {
		char gender = 'G';
		System.out.println("\n\nGender test:-");
		gender = i.validateGender(gender);
		assertEquals(gender,i.validateGender(gender));
	}
	
	// Test to check if shift timings are in proper format
	@Test
	void shiftTimingsTest1() {
		String shifts="abcas";
		System.out.println("\n\nShift tests:-");
		shifts = i.validateShifts(shifts);
		assertEquals(shifts,i.validateShifts(shifts));
	}
	
	// Test to check if start_time < end_time
	@Test
	void shiftTimingsTest2() {
		String shifts="09:10-09:00";
		System.out.println("\n\nShift tests:-");
		shifts = i.validateShifts(shifts);
		assertEquals(shifts,i.validateShifts(shifts));
	}
	
	// Test to check shift timings doesn't exceed 6 hours
	@Test
	void shiftTimingsTest3 () {
		String shifts="09:10-18:20";
		System.out.println("\n\nShift tests:-");
		shifts = i.validateShifts(shifts);
		assertEquals(shifts,i.validateShifts(shifts));
	}
	
	// Test to validate the ward number entered
	@Test
	void wardNumberValidationsTest() {
		int n = 7;
		n = i.validateWardNumber(n);
		assertEquals(n,i.validateWardNumber(n));
	}
	
	@Test
	void roomNumberValidationsTest() {
		int n = 7;
		n = i.validateRoomNumber(n);
		assertEquals(n,i.validateRoomNumber(n));
	}
	
	@Test
	void bedNumberValidationsTest() {
		int n = 7;
		n = i.validateBedNumber(n);
		assertEquals(n,i.validateBedNumber(n));
	}
	
	
	

}
