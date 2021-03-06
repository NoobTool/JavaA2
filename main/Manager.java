package main;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import CommonSnippets.DisplayMenu;
import CustomExceptions.*;
import CommonSnippets.CommonCodes;
import ward.Ward;
import ward.WardDetails;
import Actions.*;
import javafx.util.Pair;
import application.CommonOperations;
import application.WardMap;
import java.io.*;
import database.*;

public class Manager extends Employee implements Serializable{
	DBClass db = new DBClass();
	final static int NO_OF_WARDS = 2;
	final static int MAX_SHIFTS = 2;
	CommonCodes c = new CommonCodes();
	
	static Staff<Manager> managerList = new Staff<Manager>();
	static Staff<Doctor> doctorList = new Staff<Doctor>();
	static Staff<Nurse> nurseList = new Staff<Nurse>();
	static Staff<Patient> patientList = new Staff<Patient>();
	static Staff<Patient> oldPatientList = new Staff<Patient>();
	static Ward wards[] = new Ward[NO_OF_WARDS];
	
	static ActionList a = new ActionList(); 

	InputValidation i = new InputValidation();
	DisplayMenu dm = new DisplayMenu();
	
	public Manager(){
		
		
		//Restoring variables
		db.createTables();
		a.initActionList(db.retActions());
		managerList.setStaff(db.retManagerList());
		nurseList.setStaff(db.retNurseList());
		doctorList.setStaff(db.retDoctorList());
		
		// Initializing wards
		for(int i=0;i<NO_OF_WARDS;i++)
			wards[i]= new Ward();
		
		String startTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:00"));
		managerList.addStaff(new Manager((long)7730000,"Ram",21,'M',startTime+"-"
				+LocalTime.parse(startTime).plusHours(6),"1234"));
		doctorList.addStaff(new Doctor((long)6830000,"Babu",16,'M',startTime+"-"
				+LocalTime.parse(startTime).plusHours(1),"1234"));
		Nurse nurse1= new Nurse((long)7830000,"Elisa",18,'F',"10:00-16:00","1234");
		nurse1.setShifts(startTime+"-"
				+LocalTime.parse(startTime).plusHours(8));
		nurseList.addStaff(nurse1);
		
		
		WardMap wm = new WardMap();
		
		try {
//			new FileOutputStream("patients").close();
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("patients.txt"));
			patientList.setStaff((ArrayList<Patient>)input.readObject());
			for(Patient patient: retPatientList())
				setPatient(patient,patient.retBedNumber(),patient.retRoomNumber(),patient.retWardNumber());
			input.close();
			
			ObjectInputStream input2 = new ObjectInputStream(new FileInputStream("administers"));
			Nurse n = new Nurse();
			n.setAdministerMedicines((ArrayList<AdministerMedicine>)input.readObject());
			input2.close();
			
		}catch(Exception e) {}
		
		// Add a patient for testing
//		admitPatient("Radhe", 89, 'M', "Patient");
//		ArrayList<LocalTime> medTimes = new ArrayList<LocalTime>();
//		ArrayList<MedicineDose> meds = new ArrayList<MedicineDose>();
//		medTimes.add(LocalTime.parse("12:00"));
//		medTimes.add(LocalTime.parse("18:00"));
//		MedicineDose md = new MedicineDose("Crocin",2,medTimes);
//		meds.add(md);
//		Prescription prescription = new Prescription(new MedicineBlock(meds));
//		patientList.retStaff().get(0).addPrescription(prescription);
	}
	
	public Manager(String msg) {}
	
	public Manager(long id, String name, double age, char gender,String shifts,String password){
		super(id,name,age,gender,shifts,password);
	}
	
	public Manager(InputValidation i) {}
	
	public long allotId(String post) {
		long id=0;
		int asciiCode = (int) post.charAt(0);
		long postId = asciiCode*100000;
		long postLastId = postId+100000-1;
		
		for(long x: db.retAvailableId()) {
			if(x>postId && x<postLastId)
				return x;
		}
		return id;
	}
	
	// Add patient to ward
	public Pair<Boolean,String> addWard(Patient p) {
		Ward w = new Ward();
		WardDetails wardDetails = new WardDetails();
		if(isWardFull()==false) {
			for(int i=0;i<NO_OF_WARDS;i++) {
				w=wards[i];
				wardDetails = w.addPatient(p);
				if(wardDetails.retRoomNumber()!=-1) {
					wardDetails.setWardNumber((i+1));
					p.setWard(wardDetails);
					String returnValue = "Patient: "+p.retName()+" is resting at "
							+"ward "+(i+1)+" in room "+wardDetails.retRoomNumber()
							+" in bed "+wardDetails.retBedNumber()+"";
					a.addAction(new Action(retId(),p.retId(),"centre admission",LocalDate.now(),LocalTime.now()));
					patientList.addStaff(p);
					CommonOperations co = new CommonOperations();
					try {
						co.writePatients(patientList.retStaff());
					}catch(Exception e) {
						System.out.println(e);
					}
					
					return new Pair<Boolean,String>(true,returnValue);
				}
			}
		}
		return new Pair<Boolean,String>(false,"Sorry, no space for you in the care centre! ");
	}
	
	// Hire staff
	public long addPeople(String name, double age, char gender, String shifts, String password, String post){
		
		long id;
		id = allotId(post);
		
		if (post=="Manager") {
			
			if(id==0) {
				Manager m =  new Manager(db.retId(1), name, age, gender, shifts, password);
				managerList.addStaff(m, retId(), m.retId());
				return id;
			}
			
			else {
				Manager m =  new Manager(id, name, age, gender, shifts, password);
				db.removeAvailableId(id);
				managerList.addStaff(m, retId(), m.retId());
				return id;
			}			
		}
		
		else if (post=="Doctor") {
			if(id==0) {
				Doctor d =  new Doctor(db.retId(2), name, age, gender, shifts, password);
				doctorList.addStaff(d, retId(), d.retId());
				return id;
			}
			
			else {
				Doctor d =  new Doctor(id, name, age, gender, shifts, password);
				db.removeAvailableId(id);
				doctorList.addStaff(d, retId(), d.retId());
				return id;
			}
		}
		
		else {
			if(id==0) {
				Nurse n =  new Nurse(db.retId(3), name, age, gender, "08:00-16:00", password);
				n.setShifts("14:00-22:00");
				nurseList.addStaff(n, retId(), n.retId());
				return id;
			}
			
			else {
				Nurse n =  new Nurse(id, name, age, gender, "08:00-16:00", password);
				n.setShifts("14:00-22:00");
				db.removeAvailableId(id);
				nurseList.addStaff(n, retId(), n.retId());
				return id;
			}
		}
	}
	
	// Admit Patient
	public Pair<Boolean,String> admitPatient(String name, double age, char gender, String post) {
		long id = allotId(post);
		if(id==0) {
			Patient p =  new Patient(db.retId(4), name, age, gender);
			return addWard(p);
		}
		 
		else {
			Patient p =  new Patient(id, name, age, gender);
			db.removeAvailableId(id);
			return addWard(p);
		}
	}
	
	public void addStaff(Manager m){
			managerList.addStaff(m,retId(),m.retId());
		}
	
	public Employee modifyDetails(String post, long id, String name) {
		
		if(post.equals("Manager"))
			return managerSearch(post, retManagerList(), id, name);
		
		else if(post.equals("Doctor"))
			return managerSearch(post, retDoctorList(), id, name);
		
		else if(post.equals("Nurse"))
			return managerSearch(post, retNurseList(), id, name);
		
		else
			return managerSearch(post, retPatientList(), id, name);
	}
	
	public Employee managerSearch(String post, ArrayList list, long id, String name) {
		
		if(id!=-1) {
			for (Object o : list) {
				Employee e = (Employee) o;
				if (e.retId()==id)
					return e;
			}
		return new Employee();
		}
			 		
		else {
			Employee emp = new Employee();
			name=name.strip();
			int count=0;
			for (Object o: list) {
				Employee e = (Employee) o;
				if (e.retName().strip().contentEquals(name)) {
					count+=1;
					emp=e;
				}
				if(count==1)
					return emp;
				else
					return new Employee();
			}
			return new Employee();
		}
	}
	
	// Changing details of the employee found
		public void changeDetails(Employee e,  String post, String name, Double age, String gender, String password) {
			if(name!="") {
				e.setName(name);
				addAction(new Action(retId(),e.retId(),"name updation",LocalDate.now(),LocalTime.now()));
			}
				
			if(age!=-1) {
				e.setAge(age);
				addAction(new Action(retId(),e.retId(),"age updation",LocalDate.now(),LocalTime.now()));
			}
				
			if(gender.contentEquals("x")) {
				e.setGender(gender.charAt(0));
				addAction(new Action(retId(),e.retId(),"gender updation",LocalDate.now(),LocalTime.now()));
			}	
			
			if(password!="") {
				e.setPassword(password);
				addAction(new Action(retId(),e.retId(),"password updation",LocalDate.now(),LocalTime.now()));
			}		
		}
		
		public String changeDetails(Employee e, String post, int oldShiftIndex, String newShift, int choice)
		throws TooManyShiftsException{
			String returnValue = changeShifts(e,post,oldShiftIndex,newShift,choice);
			addAction(new Action(retId(),e.retId(),"shift updation",LocalDate.now(),LocalTime.now()));
			return returnValue;
		}
	
	
		public String removeEmployee(long id, String name, String post) {
			name = name.strip();
			// By ID
			if(id!=-1) {
				if(post=="Manager") {
					for(Manager m2: retManagerList()) {
						if(m2.retId()==id) {
							retManagerList().remove(m2);
							a.addAction(new Action(this.retId(),id," performer a deletion",
									LocalDate.now(),LocalTime.now()));
							db.availableId(id);
							return "";
						}
					}return "Manager not found";
				}
				
				else if(post=="Doctor") {
					for(Doctor d2: retDoctorList()) {
						if(d2.retId()==id) {
							retDoctorList().remove(d2);
							a.addAction(new Action(this.retId(),id," performer a deletion",
									LocalDate.now(),LocalTime.now()));
							db.availableId(id);
							return "";
						}
					}return "Doctor not found";
				}
				
				else {
					for(Nurse n2: retNurseList()) {
						if(n2.retId()==id) {
							retNurseList().remove(n2);
							a.addAction(new Action(this.retId(),id," performer a deletion",
									LocalDate.now(),LocalTime.now()));
							db.availableId(id);
							return "";
						}
					}return "Nurse not found";
				}
			} 
			// By Name
			else {
				if(post=="Manager") {
					for(Manager m2: retManagerList()) {
						if(m2.retName().contentEquals(name)) {
							retManagerList().remove(m2);
							a.addAction(new Action(this.retId(),m2.retId()," performer a deletion",
									LocalDate.now(),LocalTime.now()));
							db.availableId(m2.retId());
							return "";
						}
					}return "Manager not found";
				}
				
				else if(post=="Doctor") {
					for(Doctor d2: retDoctorList()) {
						if(d2.retName().strip().contentEquals(name)) {
							retDoctorList().remove(d2);
							a.addAction(new Action(this.retId(),d2.retId()," performer a deletion",
									LocalDate.now(),LocalTime.now()));
							db.availableId(d2.retId());
							return "";
						}
					}return "Doctor not found";
				}
				
				else {
					for(Nurse n2: retNurseList()) {
						if(n2.retName().strip().contentEquals(name)) {
							retNurseList().remove(n2);
							a.addAction(new Action(this.retId(),n2.retId()," performer a deletion",
									LocalDate.now(),LocalTime.now()));
							db.availableId(n2.retId());
							return "";
						}
					}return "Manager not found";
				}
			}
		}
		
	// Print all actions
	
	public void printActionList() {
		a.printActionList();
		addAction(new Action(retId(),retId(),"actions display",LocalDate.now(),LocalTime.now()));
	}
	
	// To check if ward is full or not
	public boolean isWardFull() {
		Ward w = new Ward();
		for(int i=0;i<NO_OF_WARDS;i++) {
			if(w.isFull()==false)
				return false;
		}
		return true;
	}
	
	
	// Setter Functions
	public void addAction(Action action) {
		a.addAction(action);
	}
	
	public void initActionList(ActionList actionList) {
		a = actionList;
	}
	
	public void addAvailableId(long id) {
		DBClass db = new DBClass();
		db.availableId(id);
		db.availableId(id);
	}
	
	
	// Getter functions
	public long retId() {
		return super.retId();
	}
	
	public String retName() {
		return super.retName();
	}
	
	public double retAge() {
		return super.retAge();
	}
	
	public char retGender() {
		return super.retGender();
	}
	
	public ArrayList<String> retShifts() {
		return super.retShifts();
	}
	
	public String retPass() {
		return super.retPass();
	}
	
	public Ward[] retWardList() {
		return wards;
	}
	
	public int retWards() {
		return NO_OF_WARDS;
	}
	
	public int retMaxShifts() {
		return MAX_SHIFTS;
	}
	
	public ArrayList<Manager> retManagerList(){
		return managerList.retStaff();
	}
	public ArrayList<Doctor> retDoctorList(){
		return doctorList.retStaff();
	}
	public ArrayList<Nurse> retNurseList(){
		return nurseList.retStaff();
	}
	public ArrayList<Patient> retPatientList(){
		return patientList.retStaff();
	}
	
	public ArrayList<Patient> retOldPatientList(){
		return oldPatientList.retStaff();
	}
	
	public ArrayList<Action> retActionList(){
		return a.retActionList();
	}

};
