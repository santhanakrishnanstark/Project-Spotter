package com.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class Register extends HttpServlet {


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			PrintWriter out = response.getWriter();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","stark","stark");
			Statement st = con.createStatement();
			
			String name = request.getParameter("t1");
			String pass = request.getParameter("p1");
			String repass = request.getParameter("p2");
			String email = request.getParameter("email");
			String dob = request.getParameter("dob");
			String format="yy-MM-dd";
			
			
			if(pass.equals(repass)) {
			int res = st.executeUpdate("insert into register values(rid.nextval,'"+name+"','"+pass+"','"+repass+"','"+email+"',to_date('"+dob+"','"+format+"'))");
			//int res1 = st.executeUpdate("insert into login values(lid.nextval ,'"+name+"','"+pass+"')");
			
			  if(res>=1 /* && res1>=1 */) {
				  out.println("<script> alert('Registered Successfully !');  window.location='index.html'; </script>");
			  }
			
			}else {
				out.println("<script> alert('password doesnot match');  window.location='register.html'; </script>");
			}
			}catch(Exception e) {
				System.out.println(e);
			}
	}

}
