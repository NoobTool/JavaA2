package database;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import main.Patient;
import prescription.*;

public class DBClass {
	
	static Connection connection;
	
	static void print(Object o) {
		System.out.println(o);
	}
	
	public DBClass() {}
	
	public void createTables() {
		try {
			
			ArrayList<String> posts = new ArrayList<String>();
			posts.add("MANAGER");
			posts.add("DOCTOR");
			posts.add("NURSE");
			
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/java2", "SA", "");
			Statement statement = connection.createStatement();
			
			String stmt=""; 
			
			for(String s: posts) {
				stmt = "CREATE TABLE IF NOT EXISTS "+s+"("
						+ "ID INT,"
						+ "NAME VARCHAR(50),"
						+ "AGE DOUBLE,"
						+ "GENDER CHAR,"
						+ "SHIFTS VARCHAR(12),"
						+ "PASSWORD VARCHAR(50));";
				statement.executeQuery(stmt);
			}
			
			
			stmt = "CREATE TABLE IF NOT EXISTS PATIENT("
					+ "ID INT PRIMARY KEY,"
					+ "NAME VARCHAR(30),"
					+ "AGE DOUBLE,"
					+ "GENDER CHAR,"
					+ "WARD INT,"
					+ "ROOM INT,"
					+ "BED INT);";
			statement.executeQuery(stmt);
			
			
			stmt = "CREATE TABLE IF NOT EXISTS MEDICINE("
					+ "ID INT,"
					+ "NAME VARCHAR(30),"
					+ "DOSE INT,"
					+ "DOSETIME VARCHAR(50),"
					+ "FOREIGN KEY (ID) REFERENCES PATIENT(ID));";
			
			statement.executeQuery(stmt);
			
			connection.commit();
			connection.close();
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void addStaff(long id, String name, double age, char gender, String shifts, String password, String post) {
		try {
			
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/java2", "SA", "");
			Statement statement = connection.createStatement();
			
			String stmt = "INSERT INTO "+post.toUpperCase()+" VALUES("+id+",'"+name+"',"+age+",'"+gender+"',"
					+ "'"+shifts+"','"+password+"')";
			
			statement.executeQuery(stmt);
			
			connection.commit();
			connection.close();
		}catch(Exception exception) {
			System.out.println(exception);
		}		
	}
	
	public void addPatient(Patient p) {
		try {
			
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/java2", "SA", "");
			Statement statement = connection.createStatement();
			
			String stmt = "INSERT INTO PATIENT VALUES("+p.retId()+",'"+p.retName()+"',"+p.retAge()+",'"
			+p.retGender()+"',"+p.retWardNumber()+","+p.retRoomNumber()+","+p.retBedNumber()+")";
			statement.executeQuery(stmt);
			
			ArrayList<MedicineDose> medicines= p.retPrescription().retMedicineBlock().retMedicines();
			
			for(MedicineDose md: medicines) {
				// Converting time list to a string
				String finalTime = "";
				for(LocalTime t: md.retTimes())
					finalTime+=(t.toString()+",");
				finalTime = finalTime.substring(0,finalTime.length()-1);
				
				// Inserting elements in medicine table
				String stmt2 = "INSERT INTO MEDICINE VALUES("+p.retId()
						+","+md.retName()
						+","+md.retDose()
						+","+finalTime+")";
			}
			
			connection.commit();
			connection.close();
		}catch(Exception exception) {
			System.out.println(exception);
		}
		
	}
	
	public void retPatient() {
		
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		ArrayList<MedicineDose> medicines = new ArrayList<MedicineDose>();
		try {
			
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/java2", "SA", "");
			Statement statement = connection.createStatement();
			
			String stmt = "SELECT * FROM PATIENT";
			
			ResultSet result = statement.executeQuery(stmt);
			
			while(result.next()) {
				
				Patient p = new Patient(result.getInt("ID"),result.getString("NAME"),
						result.getDouble("AGE"),result.getString("GENDER").charAt(0));
				stmt = "SELECT * FROM MEDICINE WHERE ID = "+result.getInt("ID")+";";
				ResultSet medicineDoses = statement.executeQuery(stmt);
				
				while(medicineDoses.next()) {
					ArrayList<LocalTime> times = new ArrayList<LocalTime>();
					for(String s: medicineDoses.getString("DOSETIME").split(","))
						times.add(LocalTime.parse(s));
					
					medicines.add(new MedicineDose(medicineDoses.getString("NAME"),
							medicineDoses.getInt("DOSE"),times));
				}

//				p.addPrescription(prescription);
				
			}
			
			connection.commit();
			connection.close();
		}catch(Exception exception) {
			System.out.println(exception);
		}
		
	}
	
	public void printRows(String tableName) {
		try {
	
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/java2", "SA", "");
			Statement statement = connection.createStatement();
			
			ResultSet result = statement.executeQuery("SELECT * FROM "+tableName.toUpperCase());
			
			while(result.next())
				print(result.getString("NAME"));
			
			connection.commit();
			connection.close();
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void dropTables(String...args) {
		try {
			
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/java2", "SA", "");
			Statement statement = connection.createStatement();
			
			for(String s: args) {
				String stmt = "DROP TABLE "+s.toUpperCase();
				statement.executeUpdate(stmt);
			}
			
			connection.commit();
			connection.close();
			
		}catch(Exception e) {
			System.out.println(e);
		}
	} 
	
//	public static void main(String args[]) {
//		DBClass db = new DBClass();
//		db.dropTables("Manager","Nurse","Doctor","Patient","medicine");
//		db.createTables();
		
//		db.addStaff(7730001,"Pappu", 23, 'M', "09:00-12:00", "1234","Nurse");
//		db.printRows("Manager");
//	}	
}
