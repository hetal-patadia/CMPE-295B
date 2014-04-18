<%@page import="recommender.Recommendation"%>
<%@page import="operation.DbOperation"%>
<%@page import="java.util.*"%>
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
<%	} 
    
    else {
    	//ArrayList for Conference Search
    	ArrayList<String[]> confResult = new ArrayList<String[]>();
    	//fetch username from session attribute
		String uname = (String)session.getAttribute("userid");
    	//check if submit button is clicked or not i.e. form submitted or not
    	
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
<br/>
<br/>
<br/>
    	
    	<%
	if ("POST".equalsIgnoreCase(request.getMethod())) {
	    // Form was submitted
	    //get search query
    	String searchString = request.getParameter("search");
	   
	    
	    //get search results for particular search string
	    confResult = DbOperation.searchConf(searchString);
	    
	    if (confResult == null) {
	    	out.println("NO records found for the keywords " + searchString);
	    			
	    }
	    else {
	    	Iterator<String[]> itr = confResult.iterator();
	
%>

<table border='1'>
<%
while (itr.hasNext()) {
	String[] temp = itr.next();
%>
<tr>
<td><%=temp[0] %></td>
<td><%=temp[1] %></td>
<td><%=temp[2] %></td>
<td><a href="<%=temp[3] %>"><%=temp[3] %></a></td>
<td><%=temp[4] %></td>
<td><%=temp[5] %></td>
</tr>



<%
}
%>
</table>


<br/>
<%
	
	    
	    }
	  	//add the query string to the user profile database
		DbOperation.appendSearchStringToUserProfile(uname, searchString);
		//store recommended conferences to appropriate table
		Recommendation r = new Recommendation();
		r.storeTopRecommendations(uname);
	    }
  %>
  <a href='logout.jsp'>Log out</a>
  <%
	}
%>
</center>
</body>
</html>