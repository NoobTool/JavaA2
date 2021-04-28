package main;

import CommonSnippets.CommonCodes;

public class Login {
	
	CommonCodes c = new CommonCodes();
	Manager manager = new Manager();
	
	public Manager managerLogin() {
		
		long id = c.inputLong("Enter your id. ");
		String password;
		for(Manager m: manager.retManagerList()) {
			if(m.retId()==id) {
				password = c.inputString("Enter password. ");
				if(password.matches(m.retPass()))
					return m;
				else
					break;
			}
		}
		
		System.out.println("Id or password is wrong, try again! ");
		return new Manager();
	}
	
	public Doctor doctorLogin() {
		long id = c.inputLong("Enter your id. ");
		String password;
		for(Doctor d: manager.retDoctorList()) {
			if(d.retId()==id) {
				password = c.inputString("Enter password. ");
				if(password.matches(d.retPass()))
					return d;
				else
					break;
			}
		}
		
		System.out.println("Id or password is wrong, try again! ");
		return new Doctor();
	}
	
	public Nurse nurseLogin() {

		long id = c.inputLong("Enter your id. ");
		String password;
		for(Nurse n: manager.retNurseList()) {
			if(n.retId()==id) {
				password = c.inputString("Enter password. ");
				if(password.matches(n.retPass()))
					return n;
				else
					break;
			}
		}
		
		System.out.println("Id or password is wrong, try again! ");
		return new Nurse();
	}
}
	
