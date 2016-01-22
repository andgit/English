<%@page import="java.util.*" %>
<%@page import="com.english.Database.UsersWorker"%>

<title>Profile</title>
<b>Profile</b>

<%
	int user_id = Integer.parseInt((String)request.getAttribute("user_id"));
%>

<form action="EditProfileServlet" method="post">
<fieldset>
<table> 
<%
 	List<String> userContainer = UsersWorker.GetUser(user_id);
 	Iterator<String> it = userContainer.iterator();

 	if(it.hasNext()) {

 		String userId = it.next();
 		String userEmail = it.next();
 		String userPassword = it.next();

	 	%>
		 	<tr><td>image</td><td><img src="Images/avatar1.jpg" alt="Can`t open" style="width:50px;height:50px;"/></td></tr>
		 	<tr><td>id</td><td><input style="width: 150px" type="text" name="userId" value="<% out.print(userId); %>"></td></tr>
		 	<tr><td>email</td><td><input style="width: 150px" type="text" name="userEmail" value="<% out.print(userEmail); %>"></td></tr>
		 	<tr><td>password</td><td><input style="width: 150px" type="text" name="userPassword" value="<% out.print(userPassword); %>"></td></tr>
			<tr><td><input type="submit" value="Edit"></td></tr>
		<%
 	}
 %>

</table>
</fieldset>
</form>