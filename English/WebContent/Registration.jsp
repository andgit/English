<%@page import="java.util.*" %>

<%
Object registrationServletRequestAttribute = request.getAttribute("RegistrationMessageAttribute");

if(registrationServletRequestAttribute != null) {
	
		String registrationMessage = (String) registrationServletRequestAttribute;
		
		if(registrationMessage.equals("successfulRegistration")) { %>
		
			<br>Welcome in "Let`s speak English" society. Perfect registration :-).
			
	 <%	} else { %>
	 
	 		<br>Fail : <%=registrationMessage %>
	 <%	} 
	} else { %>
	
		<b>Join Us:</b>
		<form action="RegistrationServlet" method="post">
			<fieldset>
				<table style="text-align: right;">
					<tr><td>Email: </td><td><input type="email" style="width:220px" name="email" value="" required></td></tr>
					<tr><td>Password: </td><td><input type="password" style="width:220px" name="password" value="" required></td></tr>
					<tr><td>Name: </td><td><input type="text" style="width:220px" name="name" value="" required></td></tr>
					<tr><td>Surname: </td><td><input type="text" style="width:220px" name="surname" value=""></td></tr>
					<tr><td>Country: </td><td><input type="text" style="width:220px" name="country" value="" required></td></tr>
					<tr><td>City: </td><td><input type="text" style="width:220px" name="city" value="" required></td></tr>
					<tr><td>English Level (int): </td><td><input type="number" style="width:220px" name="englishLevel" value="" required></td></tr>
					<tr><td>Skype: </td><td><input type="text" style="width:220px" name="skype" value="" required></td></tr>
					<tr><td><input type="radio" name="sex" value="male">Male</td></tr><!-- sex in DB -->
					<tr><td><input type="radio" name="sex" value="female">Female</td></tr><!-- sex in DB -->
				</table>
				<input type="submit" value="Register"/>
			</fieldset>
		</form>

 <% } %>
