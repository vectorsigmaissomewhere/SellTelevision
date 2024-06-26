package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mvc.bean.RegisterBean;
import com.mvc.util.DBConnection;
import com.mvc.bean.PasswordEncryptionWithAes;

public class RegisterDao { 
	
	 public String registerUser(RegisterBean registerBean)
	 {
		 String fullName = registerBean.getFullName();
		 String email = registerBean.getEmail();
		 String userName = registerBean.getUserName();
		 String password="";
		try {
			password = PasswordEncryptionWithAes.encryptPassword(registerBean.getPassword(),"U3CdwubLD5yQbUOG92ZnHw==");
		} catch (Exception e) {
			e.printStackTrace();
		}
		 String phonenumber = registerBean.getPhoneNumber();
		 String city = registerBean.getCity();
		 String state = registerBean.getState();
		 String country = registerBean.getCountry();
		 
		 Connection con = null;
		 PreparedStatement preparedStatement = null;		 
		 try
		 {
			 con = DBConnection.createConnection();
			 String query = "INSERT INTO users (fullname, email, username, password, mobileNumber, city, state, country) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
 //Insert user details into the table 'USERS'
			 preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
			 preparedStatement.setString(1, fullName);
			 preparedStatement.setString(2, email);
			 preparedStatement.setString(3, userName);
			 preparedStatement.setString(4, password);
			 preparedStatement.setString(5, phonenumber);
			 preparedStatement.setString(6, city);
			 preparedStatement.setString(7, state);
			 preparedStatement.setString(8,country);
			 
			 int i= preparedStatement.executeUpdate();
			 
			 if (i!=0)  //Just to ensure data has been inserted into the database
			 return "SUCCESS"; 
		 }
		 catch(SQLException e)
		 {
			e.printStackTrace();
		 }		 
		 return "Oops.. Something went wrong there..!";  // On failure, send a message from here.
	 }
}
