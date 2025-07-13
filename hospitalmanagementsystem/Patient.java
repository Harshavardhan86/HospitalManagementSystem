package hospitalmanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
	
	private Connection connection;
	
	private Scanner scanner;

	public Patient(Connection connection,Scanner scanner) {
		this.connection=connection;
		this.scanner=scanner;
	}	
	
	public void addPatient() {
	    scanner.nextLine(); 
	    
	    System.out.print("Enter Patient name: ");
	    String name = scanner.nextLine();

	    System.out.print("Enter Age: ");
	    while (!scanner.hasNextInt()) {
	        System.out.println("Please enter a valid number for age!");
	        scanner.next();
	    }
	    int age = scanner.nextInt();
	    scanner.nextLine();

	    System.out.print("Enter Gender: ");
	    String gender = scanner.nextLine();

	    try {
	        String query = "INSERT INTO patients(name, age, gender) VALUES(?, ?, ?)";
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, name);
	        preparedStatement.setInt(2, age);
	        preparedStatement.setString(3, gender);
	        
	        int affectedRows = preparedStatement.executeUpdate();
	        
	        if(affectedRows > 0) {
	            System.out.println("Patient details added Successfully");
	        } else {
	            System.out.println("Failed to add Patient details");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void viewPatient() {
		
		String query="select * from patients";
		
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			
			System.out.println("Patients:");
			
			System.out.println("+----+---------------+-----+--------+");
			System.out.println("| id | name          | age | gender |");
			System.out.println("+----+---------------+-----+--------+");
			
			while(resultSet.next()) {
				int id=resultSet.getInt("id");
				String name=resultSet.getString("name");
				int age=resultSet.getInt("age");
				String gender=resultSet.getString("gender");
				System.out.printf("| %-2d | %-13s | %-3d | %-6s |\n", id, name, age, gender);
				System.out.println("+----+---------------+-----+--------+");

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean checkPatient(int id) {
		
		String query="select * from patients WHERE id=? ";
		
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			
		
			preparedStatement.setInt(1, id);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				return true;
			}else {
				return false;
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
}
