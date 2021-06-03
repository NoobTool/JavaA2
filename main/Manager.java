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
import prescription.*;
import javafx.util.Pair;
import application.WardMap;
import javafx.stage.*;

public class Manager extends Employee{
	final static int NO_OF_WARDS = 2;
	final static int MAX_SHIFTS = 2;
	CommonCodes c = new CommonCodes();
	static Staff<Manager> managerList = new Staff<Manager>();
	static Staff<Doctor> doctorList = new Staff<Doctor>();
	static Staff<Nurse> nurseList = new Staff<Nurse>();
	static Staff<Patient> patientList = new Staff<Patient>();
	static Ward wards[] = new Ward[NO_OF_WARDS];
	
	static ActionList a = new ActionList(); 
	
	static ArrayList<Long> idList = new ArrayList<Long>();
	static ArrayList<Long> availableIdList = new ArrayList<Long>();

	InputValidation i = new InputValidation();
	DisplayMenu dm = new DisplayMenu();
	
	public Manager(){
		for(int i=0;i<NO_OF_WARDS;i++)
			wards[i]= new Ward();
			
		
		idList.add((long)7730000);
		idList.add((long)6830000);
		idList.add((long)7830000);
		idList.add((long)8030000);
		String startTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:00"));
		managerList.addStaff(new Manager((long)7730000,"Ram",21,'M',startTime+"-"
				+LocalTime.parse(startTime).plusHours(6),"1234"));
		doctorList.addStaff(new Doctor((long)6830000,"Babu",16,'M',startTime+"-"
				+LocalTime.parse(startTime).plusHours(1),"1234"));
		
		Nurse nurse1= new Nurse((long)7830000,"Elisa",18,'F',"10:00-16:00","1234");
		nurse1.setShifts(startTime+"-"
				+LocalTime.parse(startTime).plusHours(8));
		nurseList.addStaff(nurse1);
		
		admitPatient("Radhe", 89, 'M', "Patient");
		ArrayList<LocalTime> medTimes = new ArrayList<LocalTime>();
		ArrayList<MedicineDose> meds = new ArrayList<MedicineDose>();
		medTimes.add(LocalTime.parse("12:00"));
		medTimes.add(LocalTime.parse("18:00"));
		MedicineDose md = new MedicineDose("Crocin",2,medTimes);
		meds.add(md);
		Prescription prescription = new Prescription(new MedicineBlock(meds));
		patientList.retStaff().get(0).addPrescription(prescription);
		
		availableIdList.add((long)7830675);
		availableIdList.add((long)6830012);
		
		WardMap wm = new WardMap();
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
		
		for(long x: availableIdList) {
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
					return new Pair<Boolean,String>(true,returnValue);
				}
			}
		}
		
		char ans = c.inputChar(" Press 'y' to provide isolation instead. ");
		if(ans=='y'){
			Nurse n = new Nurse();
			n.provideIsolation(p);
		    if(p.retWardDetails()!=null) {
		    	a.addAction(new Action(retId(),p.retId(),"centre isolation",LocalDate.now(),LocalTime.now()));
		    	return new Pair<Boolean,String>(true, "Patient: "+p.retName()+" is isolated at "
				  		+"ward "+p.retWardNumber()+" in room "+wardDetails.retRoomNumber());
			    
		    }
		}
		return new Pair<Boolean,String>(false,"Sorry, no space for you in the care centre! ");
	}
	
	// Hire staff
	public void addPeople(String name, double age, char gender, String shifts, String password, String post){
		
		long id;
		id = allotId(post);
		
		if (post=="Manager") {
			
			if(id==0) {
				Manager m =  new Manager(idList.get(0)+1, name, age, gender, shifts, password);
				idList.set(0,idList.get(0)+1);
				managerList.addStaff(m, retId(), m.retId());				
			}
			
			else {
				Manager m =  new Manager(id, name, age, gender, shifts, password);
				availableIdList.remove(availableIdList.indexOf(id));
				managerList.addStaff(m, retId(), m.retId());
			}			
		}
		
		else if (post=="Doctor") {
			if(id==0) {
				Doctor d =  new Doctor(idList.get(1)+1, name, age, gender, shifts, password);
				idList.set(1,idList.get(1)+1);
				doctorList.addStaff(d, retId(), d.retId());
			}
			
			else {
				Doctor d =  new Doctor(id, name, age, gender, shifts, password);
				availableIdList.remove(availableIdList.indexOf(id));
				doctorList.addStaff(d, retId(), d.retId());
			}
		}
		
		else {
			if(id==0) {
				Nurse n =  new Nurse(idList.get(2)+1, name, age, gender, "08:00-16:00", password);
				n.setShifts("14:00-22:00");
				idList.set(2,idList.get(2)+1);
				nurseList.addStaff(n, retId(), n.retId());
			}
			
			else {
				Nurse n =  new Nurse(id, name, age, gender, "08:00-16:00", password);
				n.setShifts("14:00-22:00");
				availableIdList.remove(availableIdList.indexOf(id));
				nurseList.addStaff(n, retId(), n.retId());
			}
		}
	}
	
	// Admit Patient
	public Pair<Boolean,String> admitPatient(String name, double age, char gender, String post) {
		long id = allotId(post);
		if(id==0) {
			Patient p =  new Patient(idList.get(3)+1, name, age, gender);
			idList.set(3,idList.get(3)+1);
			patientList.addStaff(p);
			return addWard(p);
		}
		 
		else {
			Patient p =  new Patient(id, name, age, gender);
			availableIdList.remove(availableIdList.indexOf(id));
			patientList.addStaff(p);
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
			for (Object o: list) {
				Employee e = (Employee) o;
				if (e.retName().matches(name))
					return e;
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
				
			if(gender!="") {
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
	
	public ArrayList<Action> retActionList(){
		return a.retActionList();
	}

};
