package JUnitTests;
import static org.junit.jupiter.api.Assertions.*;
import application.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import main.*;
import ward.*;
import prescription.*;
import javafx.util.Pair;
import CustomExceptions.*;

class ResidentCareTest {
	
	static Manager m = new Manager();
	
	@Test
	void managerLoginTest() {
		try {
			Login l = new Login();
			Manager manager = l.managerLogin(7730000, "1234");
			assertEquals(true,manager.retName()!=null);
		}catch(RestrictedTimingException e) {
		}catch(InvalidCredentialsException e) {}
		
	}
	
	
	@Test
	void doctorLoginTest() {
		try {
			Login l = new Login();
			Doctor doctor = l.doctorLogin(6830000, "1234");
			assertEquals(true,doctor.retName()!=null);
		}catch(RestrictedTimingException e) {
		}catch(InvalidCredentialsException e) {}
	}
	
	
	@Test
	void nurseLoginTest() {
		try {
			Login l = new Login();
			Nurse n = l.nurseLogin(7830000, "1234");
			assertEquals(true,n.retName()!=null);
		}catch(RestrictedTimingException e) {
		}catch(InvalidCredentialsException e) {}
	}

	
	// Adding patient in ward test
	@Test
	void addPatientInWard() {
		Ward w = new Ward();
		for(int i=0;i<6;i++) {
			w.addPatient(new Patient(123,"Piya",89,'F'));
			w.addPatient(new Patient(123,"Riya",89,'F'));
		}
	}
	
	// Adding patient in ward by manager
	@Test
	void addPatientInWard2() {
		Patient p = new Patient(123,"Piya",89,'F');
		Patient p2 = new Patient(123,"Radhe",89,'M');
		
		for(int i=0;i<19;i++) {
			m.addWard(p);System.out.println("\n");
			m.addWard(p2);System.out.println("\n");
		}
		
		// Should display "Sorry, no space for you in the care centre! "
	}
	
	//Change bed test
	@Test
	void changeBedNurse() {
		Manager m = new Manager();
		m.admitPatient("Ya", 34, 'M', "patient");
		Nurse n = new Nurse();
		n.changeBed(m.retPatientList().get(0),1,5,1);
		n.changeWardAutomatically(m.retPatientList().get(0));
		n.changeWardAutomatically(m.retPatientList().get(0));
		
	}
	
	// Add new medicine to current medicine block
	@Test
	void isolationTest() {
		Nurse n = new Nurse();
		Pair<Boolean,String> value = n.provideIsolation(new Patient((long)11,"Stinky",(double)34,'F'));
		value = n.provideIsolation(new Patient((long)11,"Stinky",(double)34,'F'));
		value = n.provideIsolation(new Patient((long)11,"Stinky",(double)34,'F'));
		assertEquals(false,value.getKey());
	}
	
}
