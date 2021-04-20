import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;



class ResidentCareTest {
	
	Manager m1 = new Manager(112,"Ram",16,'M',"9-5");
	Manager m2 = new Manager(113,"Bhai",15,'M',"9-5");
	
	Doctor d1 = new Doctor(112,"Aman",16,'M',"9-5");
	Doctor d2 = new Doctor(113,"Malhotra",15,'M',"9-5");
	
	Staff<Manager> m = new Staff<Manager>();
	Staff<Doctor> d = new Staff<Doctor>();

	
	@Test
	void addManagerTest() {

		m.addStaff(m1);
		m.addStaff(m2);
		assertEquals("Bhai",m2.displayManagers(m.retStaff()));
	}
	
	
	
	// Test to add a doctor to staff list
	@Test
	void addDoctorTest() {
		
		d.addStaff(d1);
		d.addStaff(d2);
		assertEquals("Malhotra",m2.displayDoctors(d.retStaff()));
	}
	
	
	// Test to add a doctor to staff list

}
