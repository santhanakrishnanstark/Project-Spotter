package com.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/StoreEntry")
public class StoreEntry extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			PrintWriter out = response.getWriter();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","stark","stark");
			Statement st = con.createStatement();
			
			//int no = Integer.parseInt(request.getParameter("t1"));
			String name = request.getParameter("t2");
			String type = request.getParameter("type");
			String dob = request.getParameter("dob");
			String format="yy-MM-dd";
			String phno = request.getParameter("phno");
			HttpSession session = request.getSession();
			String id = session.getAttribute("rid").toString();
     		
     		int rid = Integer.parseInt(id);
			
			int rs = st.executeUpdate("insert into entry values(enid.nextval,'"+name+"','"+type+"',to_date('"+dob+"','"+format+"'),'"+phno+"','"+rid+"') ");
			if(rs>=1) {
				out.println("<script> alert('Entry Added Successfully !');  window.location='userpage.jsp'; </script>");
			}else {
				out.println("<script> alert('Error Occur !');  window.location='userpage.jsp'; </script>");
			}
			
			}catch(Exception e) {
				System.out.println(e);
			}
	}

}
