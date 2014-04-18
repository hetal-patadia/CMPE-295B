<%@page import="operation.DbOperation"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%  
    String user = request.getParameter("uname");   
    String pwd = request.getParameter("pass");
    String fname = request.getParameter("fname");
    String lname = request.getParameter("lname");
    String email = request.getParameter("email");
    String domain1 = request.getParameter("domain1");
    String domain2 = request.getParameter("domain2");
    String domain3 = request.getParameter("domain3");
    String interests = domain1 + ", " + domain2 + ", " + domain3;
    
    int i = DbOperation.insertRecordIntoUserTable(fname, lname, email, user, pwd, domain1, domain2, domain3, interests);
    /*
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbname",
            "root", "dbpass");
    Statement st = con.createStatement();
    //ResultSet rs;
    int i = st.executeUpdate("insert into members(first_name, last_name, email, uname, pass, regdate) values ('" + fname + "','" + lname + "','" + email + "','" + user + "','" + pwd + "', CURDATE())");
    */
    if (i == 1) {
        //session.setAttribute("userid", user);
        response.sendRedirect("welcome.jsp");
       // out.print("Registration Successfull!"+"<a href='index.jsp'>Go to Login</a>");
    } else {
        response.sendRedirect("index.jsp");
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>