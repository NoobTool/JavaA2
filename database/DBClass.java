package database;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import Actions.*;
import main.Manager;
import main.Doctor;
import main.Nurse;;


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
			
			
			stmt = "CREATE TABLE IF NOT EXISTS ACTION("
					+ "PERFORMER INT,"
					+ "RECEIVER INT,"
					+ "NAME VARCHAR(30),"
					+ "DATE VARCHAR(15),"
					+ "TIME VARCHAR(10));";
			statement.executeQuery(stmt);
			
			
			stmt = "CREATE TABLE IF NOT EXISTS AVAILABLE("
					+ "ID INT PRIMARY KEY);";
			statement.executeQuery(stmt);
			
			try {
				stmt = "INSERT INTO AVAILABLE VALUES(6830012);";
				statement.executeQuery(stmt);
				stmt = "INSERT INTO AVAILABLE VALUES(7837645);";
				statement.executeQuery(stmt);
			}catch(Exception e) {
			}
			
			
			stmt = "CREATE TABLE IF NOT EXISTS IDTABLE("
					+ "SNO INT,"
					+ "ID INT,"
					+ "PRIMARY KEY(SNO)"
					+");";
			statement.executeUpdate(stmt);
			
			try {
				stmt = "INSERT INTO IDTABLE VALUES(1,7730000);";
				statement.executeQuery(stmt);
				stmt = "INSERT INTO IDTABLE VALUES(2,6830000);";
				statement.executeQuery(stmt);
				stmt = "INSERT INTO IDTABLE VALUES(3,7830000);";
				statement.executeQuery(stmt);
				stmt = "INSERT INTO IDTABLE VALUES(4,8030000);";
				statement.executeQuery(stmt);
			}catch(Exception e) {
			}
			
			
			connection.commit();
			connection.close();
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void addIdList(int index) {
		try {
			
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/java2", "SA", "");
			Statement statement = connection.createStatement();
			
			String stmt = "SELECT * FROM IDTABLE WHERE SNO = "+index;
			ResultSet result = statement.executeQuery(stmt);
			result.next();
			int newId = result.getInt("ID")+1;
			
			stmt = "UPDATE IDTABLE SET ID = "+newId+""
					+ " WHERE SNO = "+index;
			statement.executeQuery(stmt);
			
			connection.commit();
			connection.close();
		}catch(Exception exception) {
			System.out.println(exception);
		}		
	}
	
	public void appendIdList(long id, int index) {
		try {
			
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/java2", "SA", "");
			Statement statement = connection.createStatement();
			
			String stmt = "UPDATE IDTABLE WHERE ID = "+id+";";
			statement.executeQuery(stmt);
			
			connection.commit();
			connection.close();
		}catch(Exception exception) {
			System.out.println(exception);
		}		
	}
	
	public long retId(int index) {
		try {
			addIdList(index);
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/java2", "SA", "");
			Statement statement = connection.createStatement();
			
			String stmt = "SELECT * FROM IDTABLE WHERE SNO = "+index+";";
			ResultSet result = statement.executeQuery(stmt);
			result.next();
			long id = result.getLong("ID");
			connection.close();
			return id;
			
		}catch(Exception e) {
			return -1;
		}		
	}
	
	// Configuring availableIdList
	public ArrayList<Long> retAvailableId() {
		try {
			
			ArrayList<Long> availableID = new ArrayList<Long>();
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/java2", "SA", "");
			Statement statement = connection.createStatement();
			
			String stmt = "SELECT * FROM AVAILABLE;";
			ResultSet result = statement.executeQuery(stmt);
			
			while(result.next())
				availableID.add((long)result.getInt("ID"));
			connection.commit();
			connection.close();
			return availableID;
		}catch(Exception e) {
			return new ArrayList<Long>();
		}		
	}
	
	public void availableId(long id) {
		try {
			
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/java2", "SA", "");
			Statement statement = connection.createStatement();
			
			String stmt = "INSERT INTO AVAILABLE VALUES("+id+")";
			statement.executeQuery(stmt);
			
			connection.commit();
			connection.close();
		}catch(Exception exception) {
			System.out.println(exception);
		}		
	}
	
	public void removeAvailableId(long id) {
		try {
			
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/java2", "SA", "");
			Statement statement = connection.createStatement();
			
			String stmt = "DELETE FROM AVAILABLE WHERE ID = "+id+";";
			statement.executeQuery(stmt);
			
			connection.commit();
			connection.close();
		}catch(Exception exception) {
			System.out.println(exception);
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
	
	public void deleteStaff(long id, String post, String name) {
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/java2", "SA", "");
			Statement statement = connection.createStatement();
			String stmt="";
			if(id!=-1)
				stmt = "DELETE FROM "+post.toUpperCase()+" WHERE ID = "+id+";";
			else
				stmt = "DELETE FROM "+post.toUpperCase()+" WHERE NAME = '"+name.strip()+"';";
			
			statement.executeQuery(stmt);
			
			connection.commit();
			connection.close();
		}catch(Exception exception) {
			System.out.println(exception);
		}		
	}
	
	public ArrayList<Manager> retManagerList(){
		try {
			
			ArrayList<Manager> managerList = new ArrayList<Manager>();
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/java2", "SA", "");
			Statement statement = connection.createStatement();
			
			String stmt = "SELECT * FROM MANAGER";
			ResultSet result = statement.executeQuery(stmt);
			
			while(result.next()) {
				managerList.add(new Manager(result.getLong("ID"),result.getString("NAME"),
						result.getDouble("AGE"),result.getString("GENDER").charAt(0),
						result.getString("SHIFTS"),result.getString("PASSWORD")));
			}
			connection.commit();
			connection.close();
			
			return managerList;
		}catch(Exception exception) {
			System.out.println(exception);
			return new ArrayList<Manager>();
		}
	}
	
	public ArrayList<Doctor> retDoctorList(){
		try {
			
			ArrayList<Doctor> doctorList = new ArrayList<Doctor>();
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/java2", "SA", "");
			Statement statement = connection.createStatement();
			
			String stmt = "SELECT * FROM DOCTOR";
			ResultSet result = statement.executeQuery(stmt);
			
			while(result.next()) {
				doctorList.add(new Doctor(result.getLong("ID"),result.getString("NAME"),
						result.getDouble("AGE"),result.getString("GENDER").charAt(0),
						result.getString("SHIFTS"),result.getString("PASSWORD")));
			}
			connection.commit();
			connection.close();
			
			return doctorList;
		}catch(Exception exception) {
			System.out.println(exception);
			return new ArrayList<Doctor>();
		}
	}
	
	public ArrayList<Nurse> retNurseList(){
		try {
			
			ArrayList<Nurse> nurseList = new ArrayList<Nurse>();
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/java2", "SA", "");
			Statement statement = connection.createStatement();
			
			String stmt = "SELECT * FROM NURSE";
			ResultSet result = statement.executeQuery(stmt);
			
			while(result.next()) {
				nurseList.add(new Nurse(result.getLong("ID"),result.getString("NAME"),
						result.getDouble("AGE"),result.getString("GENDER").charAt(0),
						result.getString("SHIFTS"),result.getString("PASSWORD")));
			}
			connection.commit();
			connection.close();
			
			return nurseList;
		}catch(Exception exception) {
			System.out.println(exception);
			return new ArrayList<Nurse>();
		}
	}
	
	public void updateStaff(long id, String name, double age,String gender, String shifts,
			String password, String post) {
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/java2", "SA", "");
			Statement statement = connection.createStatement();
			
			if(name!="") {
				String stmt = "UPDATE "+post.toUpperCase()
				+" SET NAME = '"+name
				+"' WHERE ID = "+id;
				statement.executeQuery(stmt);
				connection.commit();
			}
			
			if(age!=-1) {
				String stmt = "UPDATE "+post.toUpperCase()
				+" SET AGE = "+age
				+" WHERE ID = "+id;
				statement.executeQuery(stmt);
				connection.commit();
			}
			
			if(!gender.equals("x")) {
				String stmt = "UPDATE "+post.toUpperCase()
				+" SET GENDER = '"+gender.charAt(0)
				+"' WHERE ID = "+id;
				statement.executeQuery(stmt);
				connection.commit();
			}
			
			if(password!="") {
				String stmt = "UPDATE "+post.toUpperCase()
				+" SET PASSWORD = '"+password
				+"' WHERE ID = "+id;
				statement.executeQuery(stmt);
				connection.commit();
			}
			
			if(shifts!="") {
				String stmt = "UPDATE "+post.toUpperCase()
				+" SET SHIFTS = '"+shifts
				+"' WHERE ID = "+id;
				statement.executeQuery(stmt);
				connection.commit();
			}
			
			connection.close();
		}catch(Exception exception) {
			System.out.println(exception);
		}		
	}
	
	public void addAction(Action a) {
		try {
			
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/java2", "SA", "");
			Statement statement = connection.createStatement();
			
			String stmt = "INSERT INTO ACTION VALUES("+a.retPerformerId()+",'"+a.retReceiverId()+"','"+a.retActionName()+"','"
			+a.retDate().toString()+"','"+a.retTime().toString()+"')";
			statement.executeQuery(stmt);
			
			connection.commit();
			connection.close();
		}catch(Exception exception) {
			System.out.println(exception);
		}
		
	}
	
	public ArrayList<Action> retActions() {
		
		try {
			
			ArrayList<Action> actions = new ArrayList<Action>();
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/java2", "SA", "");
			Statement statement = connection.createStatement();
			
			String stmt = "SELECT * FROM ACTION";
			
			ResultSet result = statement.executeQuery(stmt);
			
			while(result.next()) {
				actions.add(new Action(result.getLong("PERFORMER"),result.getLong("RECEIVER"),
						result.getString("NAME"),LocalDate.parse(result.getString("DATE"))
						,LocalTime.parse(result.getString("TIME"))));
			}
				
				
			
			connection.commit();
			connection.close();
			
			return actions;
		}catch(Exception exception) {
			System.out.println(exception);
			return new ArrayList<Action>();
		}
		
	}
	
	public void printRows(String tableName) {
		try {
	
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/java2", "SA", "");
			Statement statement = connection.createStatement();
			
			ResultSet result = statement.executeQuery("SELECT * FROM "+tableName.toUpperCase());
			
			while(result.next())
				print(result.getString("ID"));
			
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
	
//	Reset the database tables from here	
//	public static void main(String args[]) {
//		DBClass db = new DBClass();
//		db.dropTables("Manager","Nurse","Doctor","Action","IDTABLE","AVAILABLE");
//		db.createTables();
//	}	
}
