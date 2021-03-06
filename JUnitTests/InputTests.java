package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;
import CommonSnippets.*;
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
		assertEquals(true,i.validateName(name,true).getValue().length()>0);
	}
	
	// Check for digits or other characters in name
	@Test
	void nameTest2() {
		System.out.println("\n\nNew name test with numeric digits!");
		String name = "abc123";
		assertEquals(true
				,i.validateName(name,true).getValue().length()>0);
	}
	
	// Another Check for other characters in name
	@Test
	void nameTest3() {
		System.out.println("\n\nNew name test with special chars!");
		String name = "abc@";
		assertEquals(true,i.validateName(name,true).getValue().length()>0);
	}
	
	
	// Age test - float for ages<1 and rounded off integer for others
	@Test
	void ageTest() {
		double age = .5;
		assertEquals("",i.validateAge(age).getValue());
	}
	
	// Age test - Age>0 and Age<110
	@Test
	void ageTest2() {
		double age = 23.45;
		System.out.println(i.validateAge(age));
		assertEquals(23,i.validateAge(age).getKey(),0);
	}
	
	// Gender test - gender belongs to only M or F
	@Test
	void genderTest() {
		char gender = 'G';
		System.out.println("\n\nGender test:-");
		assertEquals(true
				,i.validateGender(gender).length()>0);
	}
	
	// Test to check if shift timings are in proper format
	@Test
	void shiftTimingsTest1() {
		String shifts="abcas";
		System.out.println("\n\nShift tests:-");
		assertEquals(false,i.validateShifts(shifts,"Manager").getKey());
	}
	
	// Test to check if start_time < end_time
	@Test
	void shiftTimingsTest2() {
		String shifts="09:10-09:00";
		System.out.println("\n\nShift tests:-");
		assertEquals(false,i.validateShifts(shifts,"Manager").getKey());
	}
	
	// Test to check shift timings doesn't exceed 6 hours
	@Test
	void shiftTimingsTest3 () {
		String shifts="09:10-15:10";
		System.out.println("\n\nShift tests:-");
		assertEquals(true,i.validateShifts(shifts,"Manager").getKey());
	}
	
	// Test to check shift timings doesn't exceed 1 hours for doctor
		@Test
		void shiftTimingsTest4 () {
			String shifts="09:10-18:20";
			System.out.println("\n\nShift tests:-");
			assertEquals(false,i.validateShifts(shifts,"Doctor").getKey());
		}
	
	// Test to validate the ward number entered
	@Test
	void wardNumberValidationsTest() {
		int n = 7;
		assertEquals(true,i.validateWardNumber(n).length()>0);
	}
	
	@Test
	void roomNumberValidationsTest() {
		int n = 7;
		assertEquals(true,i.validateRoomNumber(n).length()>0);
	}
	
	@Test
	void bedNumberValidationsTest() {
		CommonCodes c = new CommonCodes();
		int n = 5;
		int m = 2;
		assertEquals(true,i.validateBedNumber(5,5).length()>0);
	}
	
	
	

}
