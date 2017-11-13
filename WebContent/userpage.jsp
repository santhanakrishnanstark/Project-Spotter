<%@page import="com.login.LoginAccess"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>User Zone</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
.navv{ float:right; margin-right:50px;}
</style>
</head>
<body>
<% 
     response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    
%>
 <%
   if(session.getAttribute("username")==null){
	   out.println("No Session");
	  
   }else{
	   String name=session.getAttribute("username").toString();
	    out.println("<script>  alert('welcome "+name+" '); </script>");
	    
	      
   }
       
   %>
<div class="container-fluid text-center" style=" color:white; background-color:rgb(51, 122, 183);">
  <h2>My Spotter ...</h2>
  <p>New way to Remind your friends/family Birthdays!</p>
      <div class="btn-group navv">
           <a href="index.html"><input class="btn btn-primary" type="button" value="Home" ></a>
           <a href="index.html"><input class="btn btn-primary" type="button" value="Login" ></a>
         <form action="Logout" method="post">  <a href="index.html"><input class="btn btn-primary" type="submit" value="Logout"></a>
       </form>
  </div> 
</div>
<div class="container" style="clear:both; margin-top:20px;">
   <div class="row">
       <div class="col-sm-4">
       <form action="StoreEntry" method="post">
         <div class="panel panel-primary">
         
            <div class="panel panel-heading"><h3>Add Entry</h3></div>
          
            <div class="panel-body"><label>Name: </label><input type="text" name="t2" class="form-control"> </div>
            <div class="panel-body"><label>Type: </label><select name="type" class="form-control"><option>Family</option><option>Friends</option></select> </div>
            <div class="panel-body"><label>DOB: </label><input type="date" name="dob" class="form-control"> </div>
            <div class="panel-body"><label>PhoneNumber: </label><input type="number" name="phno" class="form-control"> </div>
            <div class="panel-body"><input class="btn btn-primary" type="submit" name="addbt" class="form-control"> </div>
            </div>
         </form>
       </div>
       <div class="col-sm-8">
           <div class="panel panel-success">
             <div class="panel panel-heading">Tables</div>
              <div class="table-responsive">
               <table class="table">
              <%
              if(session.getAttribute("username")!=null){
           	  
           	  
             
              try {
                  Class.forName("oracle.jdbc.OracleDriver");
         		
         		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","stark","stark");
         		Statement st = con.createStatement();
         		//HttpSession session = request.getSession();
         		String id = session.getAttribute("rid").toString();
         		
         		int rid = Integer.parseInt(id);
         		
         		
         		ResultSet rs = st.executeQuery("select * from entry where regid='"+rid+"' ");
     		while(rs.next()) {
     			
     		%>
     		
                   <tr>
                      
                       <td>
                           <%=rs.getString("name") %>
                       </td>

                       <td>
                          <%=rs.getString("type") %>
                       </td>
                        <td>
                          <%=rs.getDate("dob") %>
                       </td>
                        <td>
                          <%=rs.getString("phno") %>
                       </td>
                   </tr>  
                   
             
               <% } 
     		
     		
     		}catch(Exception e) {
     			System.out.println(e);
     		} }
              %>
                </table>
             </div>
           </div>
       </div>
   </div>
</div>

<div class="panel-footer" style="padding:20px; text-align:center;">Panel Footer Copyright@2017</div>

</body>
</html>
