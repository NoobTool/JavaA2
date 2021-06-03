package database;
import java.sql.*;
import java.util.ArrayList;

public class DBClass {
	
	static Connection connection;
	
	static void print(Object o) {
		System.out.println(o);
	}
	
	public void DBClass() {}
	
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
						+ "NAME VARCHAR(50),"
						+ "AGE DOUBLE,"
						+ "GENDER CHAR,"
						+ "SHIFTS VARCHAR(12),"
						+ "PASSWORD VARCHAR(50));";
				statement.executeQuery(stmt);
			}
			
			connection.commit();
			connection.close();
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void addStaff(String name, double age, char gender, String shifts, String password, String post) {
		try {
			
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/java2", "SA", "");
			Statement statement = connection.createStatement();
			
			String stmt = "INSERT INTO "+post.toUpperCase()+" VALUES('"+name+"',"+age+",'"+gender+"',"
					+ "'"+shifts+"','"+password+"')";
			
			statement.executeQuery(stmt);
			
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
	
	public static void main(String args[]) {
		DBClass db = new DBClass();
		db.dropTables("Manager","Nurse","Doctor");
		db.createTables();
		db.addStaff("Pappu", 23, 'M', "09:00-12:00", "1234","Nurse");
	}	
}
