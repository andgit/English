<%@page import="java.util.*" %>
<%@page import="com.english.GlobalFunctions" %>
<%@page import="com.english.Database.UsersWorker"%>

<title>My Profile</title>
<b>Profile</b>

<%
String account_email = null;

if(session.getAttribute("logged").equals("online")) {
	
	account_email = (String) session.getAttribute("email");

%>
	<form action="EditProfileServlet" method="post">
	<fieldset>
	<table> 
<%
	 	List<String> userContainer = UsersWorker.GetUserAndAccount(account_email);
	 	Iterator<String> it = userContainer.iterator();
	 	
	 	if(it.hasNext()) {

	 		String userId = it.next();
	 		String userName = it.next();
	 		String userSurname = it.next();
	 		String userCountry = it.next();
	 		String userCity = it.next();
	 		String userEnglishLevel = it.next();
	 		String userSkype = it.next();
	 		String userSex = it.next();
	 		
	 		String accountId = it.next();
	 		String accountEmail = it.next();
	 		String accountPassword = it.next();
	 		String accountRegistrationDate = it.next();

	 		%>
		 		<tr><td>image</td><td><img src="Images/avatar1.jpg" alt="Can`t open" style="width:50px;height:50px;"/></td></tr>
		 		
			 	<tr><td>user id</td><td><input style="width: 150px" type="text" name="userId" value="<% out.print(userId); %>"></td></tr>
			 	<tr><td>name</td><td><input style="width: 150px" type="text" name="userName" value="<% out.print(userName); %>"></td></tr>
			 	<tr><td>surname</td><td><input style="width: 150px" type="text" name="userSurname" value="<% out.print(userSurname); %>"></td></tr>
			 	<tr><td>country</td><td><input style="width: 150px" type="text" name="userCountry" value="<% out.print(userCountry); %>"></td></tr>
			 	<tr><td>city</td><td><input style="width: 150px" type="text" name="userCity" value="<% out.print(userCity); %>"></td></tr>
			 	<tr><td>english level</td><td><input style="width: 150px" type="text" name="userEnglishLevel" value="<% out.print(userEnglishLevel); %>"></td></tr>
			 	<tr><td>skype</td><td><input style="width: 150px" type="text" name="userSkype" value="<% out.print(userSkype); %>"></td></tr>
			 	<tr><td>sex</td><td><input style="width: 150px" type="text" name="userSex" value="<% out.print(userSex); %>"></td></tr>
			 	
			 	<tr><td>account id</td><td><input style="width: 150px" type="text" name="accountId" value="<% out.print(accountId); %>"></td></tr>
			 	<tr><td>email</td><td><input style="width: 150px" type="text" name="accountEmail" value="<% out.print(accountEmail); %>"></td></tr>
			 	<tr><td>password</td><td><input style="width: 150px" type="text" name="accountPassword" value="<% out.print(accountPassword); %>"></td></tr>
			 	<tr><td>registration date</td><td><input style="width: 150px" type="text" name="accountRegistrationDate" value="<% out.print(accountRegistrationDate); %>"></td></tr>
		 		<tr><td><input type="submit" value="Save"></td></tr>
			<%
	 	}
 %>
	</table>
	</fieldset>
	</form>
<%
} else {
	
	out.println("<br><br>You are not logged in.");
}
%>
