package hospitalmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HospitalManagementSystem {
	
	private static final String url="jdbc:mysql://localhost:3306/hospital";
	
	private static final String username="root";
	
	private static final String password="harsha";
	
	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Scanner scanner=new Scanner(System.in);
		
		Connection connection;
		
		try {
			connection = DriverManager.getConnection(url,username,password);
			
			Patient patient=new Patient(connection, scanner);
			
			Doctor doctor=new Doctor(connection);
			
			while(true) {
				System.out.println("HOSPITAL MANAGEMENT SYSTEM");
				System.out.println("1.Add Patients");
				System.out.println("2.View Patients");
				System.out.println("3.View Doctor");
				System.out.println("4.Book Appointment");
				System.out.println("5.Exit");
				
				System.out.print("Enter your choice:");
				
				int choice=scanner.nextInt();
				
				switch(choice) {
					
				    case 1:
							//Add Patient
				    		patient.addPatient();
				    		System.out.println();
				    		break;
					case 2:
							//View Patient
							patient.viewPatient();
							System.out.println();
							break;
					case 3:
							//View Doctor
							doctor.viewDoctor();
							System.out.println();
							break;
					case 4:
							//Book Appointment
							bookAppointment(patient, doctor, connection, scanner);
							System.out.println();
							break;
					case 5:
						return ;
				
					default:
						System.out.println("Enter valid option");
						break;
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void bookAppointment(Patient patient, Doctor doctor, Connection connection, Scanner scanner) {
	    System.out.print("Enter Patient id: ");
	    int patientId = scanner.nextInt();
	    
	    System.out.print("Enter Doctor id: ");
	    int doctorId = scanner.nextInt();
	    
	    System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
	    scanner.nextLine();
	    String appointmentDate = scanner.nextLine();
	    
	    if(patient.checkPatient(patientId) && doctor.checkDoctor(doctorId)) {
	        if(checkDocterAvailability(doctorId, appointmentDate, connection)) {
	            String appointmentQuery = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) VALUES(?, ?, ?)";
	            
	            try {
	                PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
	                preparedStatement.setInt(1, patientId);
	                preparedStatement.setInt(2, doctorId);
	                preparedStatement.setString(3, appointmentDate);
	                
	                int rowsAffected = preparedStatement.executeUpdate();
	                
	                if(rowsAffected > 0) {
	                    System.out.println("Appointment Booked!");
	                } else {
	                    System.out.println("Failed to book Appointment");
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } else {
	            System.out.println("Doctor not available on this date!");
	        }
	    } else {
	        System.out.println("Either Patient or Doctor does not Exist");
	    }
	}
	
	public static boolean checkDocterAvailability(int doctorId,String appointmentDate,Connection connection){
		
		String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ?";

		
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			
			preparedStatement.setInt(1, doctorId);
			preparedStatement.setString(2, appointmentDate);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int count=resultSet.getInt(1);
				if(count==0) {
					return true;
				}else {
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
 	
}
