package com.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")
public class Login extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String uname=request.getParameter("loginname");
		String pass = request.getParameter("loginpass");
		
		LoginAccess la = new LoginAccess();
		
		if(la.checkAccount(uname, pass)) {
			int id = la.getId(uname,pass);
		
			HttpSession session = request.getSession();
			session.setAttribute("rid", id);
			session.setAttribute("username", uname);
			response.sendRedirect("userpage.jsp");
		}
		else {
			out.println("<script> alert('Invalid Uname and pass');  window.location='index.html'; </script>");
			
		}
	}

}
