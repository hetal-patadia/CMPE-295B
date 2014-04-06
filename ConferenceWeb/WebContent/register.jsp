<%@ page import ="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%ResultSet rs =null;
    try{
    	Class.forName("com.mysql.jdbc.Driver").newInstance();
    	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/conference_search",
                "root", "root");
    	        Statement st = conn.createStatement() ;
    	        rs =st.executeQuery("select topic_name from topics limit 20") ;
    
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
<form method="post" action="registration.jsp">
            <center>
            <table width="30%" cellpadding="5">
                <thead>
                    <tr>
                        <th colspan="2">Enter Information Here</th>
                    </tr>
                </thead>
                <tbody>
                	<tr>
                    </tr>
                    <tr>
                    </tr>
                    <tr>
                    </tr>
                    <tr>
                    </tr>
                    <tr>
                    </tr>
                    <tr>
                    </tr>
                    <tr>
                        <td>First Name</td>
                        <td><input type="text" name="fname" value="" /></td>
                    </tr>
                    <tr>
                        <td>Last Name</td>
                        <td><input type="text" name="lname" value="" /></td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><input type="text" name="email" value="" /></td>
                    </tr>
                    <tr>
                        <td>User Name</td>
                        <td><input type="text" name="uname" value="" /></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="pass" value="" /></td>
                    </tr>
                    <tr>
                        <td>Select domain of interest-1</td>
                        <td>
                        <select name="domain1">
        <%  while(rs.next()){ %>
            <option><%= rs.getString(1)%></option>
        <% } %>
        </select>
        </td>
                    </tr>
                    <tr>
                        <td>Select domain of interest-2</td>
                        <td>
                        <select name="domain2">
        <%  rs =st.executeQuery("select topic_name from topics limit 20") ;
        while(rs.next()){ %>
            <option><%= rs.getString(1)%></option>
        <% } %>
        </select>
        </td>
                    </tr>
                    <tr>
                        <td>Select domain of interest-3</td>
                        <td>
                        <select name="domain3">
        <%  rs =st.executeQuery("select topic_name from topics limit 20") ;
        while(rs.next()){ %>
            <option><%= rs.getString(1)%></option>
        <% } %>
        </select>
        </td>
                    </tr>
                    <%
//**Should I input the codes here?**
        }
        catch(Exception e)
        {
             out.println("wrong entry"+e);
        }
%>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Submit" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="Reset" /></td>
                    </tr>
                    <tr>
                    </tr>
                    <tr>
                    </tr>
                    <tr>
                    </tr>
                    <tr>
                    </tr>
                    <tr>
                        <td colspan="2">Already registered!! <a href="index.jsp">Login Here</a></td>
                    </tr>
                </tbody>
            </table>
            </center>
        </form>
    </body>
</html>