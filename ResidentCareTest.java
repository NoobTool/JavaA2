import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;



class ResidentCareTest {
	
	Manager m1 = new Manager(112,"Ram",16,'M',"9-5");
	Manager m2 = new Manager(113,"Bhai",15,'M',"9-5");
	
	Doctor d1 = new Doctor(222,"Aman",16,'M',"9-5");
	Doctor d2 = new Doctor(223,"Malhotra",15,'M',"9-5");
	
	Nurse n1 = new Nurse(331,"Shreya",15,'F',"6-9");
	Nurse n2 = new Nurse(332,"Aashi",18,'F',"6-9");
	
	Staff<Manager> m = new Staff<Manager>();
	Staff<Doctor> d = new Staff<Doctor>();
	Staff<Nurse> n = new Staff<Nurse>();

	String name = m1.displayManagers();
	
	
	@Test
	void addManagerTest() {

		m.addStaff(m1);
		m.addStaff(m2);
		m2.displayManagers();
		assertEquals("Bhai",m2.displayManagers());
	}
	
	
	
	// Test to add a doctor to staff list
	@Test
	void addDoctorTest() {
		
		d.addStaff(d1);
		d.addStaff(d2);
		assertEquals("Malhotra",m2.displayDoctors());
	}
	
	

	// Test to add a doctor to staff list
	@Test
	void addNurseTest() {
		
		n.addStaff(n1);
		n.addStaff(n2);
		assertEquals("Aashi",m2.displayNurses());
	}
	
	
}
