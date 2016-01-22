<%@page import="java.util.*" %>
<%@page import="com.english.Database.UsersWorker"%>

<b>Members</b>
<%
int searchedUserId = -1;
if(request.getAttribute("user_id") != null) {
	
	searchedUserId = Integer.parseInt((String)request.getAttribute("user_id"));
}

if(searchedUserId != -1) {
	%>
	<table> 
	<%
	 	List<String> userContainer = UsersWorker.GetUser(searchedUserId);
	 	Iterator<String> it = userContainer.iterator();

	 	if(it.hasNext()) {
	
	 		String userId = it.next();
	 		String userEmail = it.next();
	 		String userPassword = it.next();
		 	%>
			 	<tr><td>id</td><td><input style="width: 150px" type="text" name="userId" value="<% out.print(userId); %>"></td></tr>
			 	<tr><td>email</td><td><input style="width: 150px" type="text" name="userEmail" value="<% out.print(userEmail); %>"></td></tr>
			 	<tr><td>password</td><td><input style="width: 150px" type="text" name="userPassword" value="<% out.print(userPassword); %>"></td></tr>
			<%
	 	}
	 %>
	</table>
	<%
} else {
%>
<form action="ProfileServlet" method="post">
<fieldset>
<table>
<tr>
<th>user_id</th>
<th>user_email</th>
<th>user_password</th>
<th>Show</th>
</tr> 
<%
	 List<String> usersContainer = UsersWorker.GetUsers();
	
	 Iterator<String> it = usersContainer.iterator();
	 int buttonIndex = 0;
	
	 while (it.hasNext()) {
	
	     out.print("<tr>");
	     
	     for (int i = 0; i < 2; ++i) {
	   	  
	   	  if(i==0) {
	       		  
	       		  int user_id = Integer.parseInt(it.next());
	       		  String submitButton = "submitButton" + buttonIndex;
	       	      %><td><input style="width: 30px" type="text" name="<% out.print(submitButton); %>" value="<% out.print(user_id); %>" readonly></td><%
	   	  }
	         out.print("<td>");
	         out.print(it.next());
	         out.print("</td>");
	
	         if(i==1) {
	
	         	%><td><input type="submit" value="Show"></td><%
	         }
	  }
	 out.print("</tr>");
	 ++buttonIndex;
	}
}
%>
</table>
</fieldset>
</form>

<b>Search user:</b>
<form action="SearchMemberServlet" method="post">
	<fieldset>
	E-mail: <input type="text" name="user_email"><br>
	<input type="submit" value="Search">
	</fieldset>
</form>