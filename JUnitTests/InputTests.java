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
		fail("Not yet implemented!");
	}
	
	// Test to check if start_time < end_time
	@Test
	void shiftTimingsTest2() {
		fail("Not yet implemented!");
	}

}
