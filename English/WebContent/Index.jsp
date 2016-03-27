<%@ page import="java.sql.*, com.english.Database.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.english.GlobalData"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>English</title>
</head>
<body>

<a href="Index.jsp?subpage=1">Homepage</a>
<a href="Index.jsp?subpage=2">Members</a>
<a href="Index.jsp?subpage=3">Join Us</a>
<!-- <a href="Index.jsp?subpage=4">Log In</a> -->
<a href="Index.jsp?subpage=5">How to start</a>
<!--<a href="Index.jsp?subpage=5">Profile</a> ZAKOMENTOWANE nie ma tego w glownym menu-dostajemy sie przez Members.jsp-->
<a href="Index.jsp?subpage=7">My Profile</a>
<br>

<%
	Connection conn = DBHandler.connect();
	//out.print(conn);
	DBHandler.close(conn);

	if(request.getParameter("subpage") != null) {
		
		String subpageString = request.getParameter("subpage");
		int subpage = Integer.parseInt(subpageString);
		
		switch(subpage) {
		case 1:
			%>
				<jsp:include page="Homepage.jsp" />
			<%
		break;
		case 2:
			%>
				<jsp:include page="Members.jsp" />
			<%
		break;
		case 3:
			%>
				<jsp:include page="Registration.jsp" />
			<%
		break;
		//case 4:
			
				//<jsp:include page="LogIn.jsp" />
			
		//break;
		case 5:
			%>
				<jsp:include page="HowTo.jsp" />
			<%
		break;
		case 6://tego nie ma w glownym menu-dostajemy sie do tego przez Members.jsp
			%>
				<jsp:include page="Profile.jsp" />
			<%
		break;
		case 7:
			%>
				<jsp:include page="MyProfile.jsp" />
			<%
		break;
		default:
			%>
			<jsp:include page="Homepage.jsp" />
			<%
		}
	} else {
		%>
			<jsp:include page="Homepage.jsp" />
		<%
	}
	%>
<!-- SESSION SECTION -->
		<% if(session.getAttribute("logged")==null || session.getAttribute("logged").equals(GlobalData.sessionOffLine)) { %>
			<form action="LogInServlet" method="post">
				<br>
				email: <input type="text" name="account_email"/>
				password: <input type="password" name="account_password"/>
				<input type="submit" value="Log in"/>
				</br>
			</form>
		<% } else if(session.getAttribute("logged").equals(GlobalData.sessionOnline)) { %>
			<form action="LogOutServlet" method="post">
				<br>Logged as: 
				<% if(session.getAttribute("account_type").equals("customer")) { %>
					<font color='blue'><% out.println(""+(String) session.getAttribute("email")); %></font></br>
				<% } else if(session.getAttribute("account_type").equals("admin")) { %>
					<font color='red'><% out.println(""+(String) session.getAttribute("email")); %></font></br>
				<% } %>
				<br><input type="submit" value="Log out"/>
			</form>
		<% } else if(session.getAttribute("logged").equals("ErrorEmailPass")) {
			session.setAttribute("logged", GlobalData.sessionOffLine);
			session.setAttribute("account_type", GlobalData.sessionOffLine);
			session.setAttribute("email", GlobalData.sessionOffLine);
		%>
			<form action="LogInServlet" method="post">
				<br>Wrong email or password:-(</br>
				<br>
				email: <input type="text" name="account_email"/>
				password: <input type="password" name="account_password"/>
				<input type="submit" value="Log in"/>
				</br>
			</form>
		<% } %>

</body>
</html>