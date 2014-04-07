<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Home</title>
</head>
<body>
<center>
<%
    if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
%>
You are not logged in<br/>
<a href="index.jsp">Please Login</a>
<%} else {

    String user = request.getParameter("search");
%>
Welcome <%=session.getAttribute("userid")%><br/><br/><br/>

<form method="post" action="success.jsp">
<table width="30%" cellpadding="5">
                <thead>
                    <tr>
                        <th colspan="2">Search for conferences</th>
                    </tr>
                </thead>
                <tbody>
                	<tr>
                	<td>
                	<input type="text" name="search" value="" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	<input type="submit" value="Search" />
                	</td>
                    </tr>
                    </tbody>
                    </table>
                    </form>

<%
if (user != null)
	{
	%>
	<%= user %>
	<%} %>
<br/>
<br/>
<br/>
<a href='logout.jsp'>Log out</a>
<%
    }
%>
</center>
</body>
</html>