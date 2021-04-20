import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ResidentCareTest {

	@Test
	void test() {
		Staff<Manager> s = new Staff<Manager>();
		s.addStaff(new Manager(112,"Ram",16,"Male"));
		//assertEquals("Ram",s.printStaff());
	}

}
