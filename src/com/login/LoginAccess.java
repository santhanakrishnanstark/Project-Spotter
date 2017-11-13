package com.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpSession;

public class LoginAccess {

	
	public boolean checkAccount(String user,String pas) {
		
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","stark","stark");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from register where username='"+user+"' and password='"+pas+"' ");
		if(rs.next()) {
		
			return true;
			
		}
		
		}catch(Exception e) {
			System.out.println(e);
		}
		return false;
		
	}
	
	public int getId(String unam,String pas) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","stark","stark");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from register where username='"+unam+"' and password='"+pas+"' ");
			if(rs.next()) {
				
				int id=rs.getInt("regid");
				
				return id;
			}
			
			}catch(Exception e) {
				System.out.println(e);
			}
		
		
		return 0;
	}

}
