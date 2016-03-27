<%@page import="com.english.GlobalFunctions"%>
<%@page import="java.util.*" %>
<%@page import="com.english.Database.UsersWorker"%>

<b>Members</b>
<%
int searchedUserId = -1;
Object searchMemberServletRequestAttribute = request.getAttribute("account_user_id");

if(searchMemberServletRequestAttribute != null) {//From SearchMemberServlet 
	
	searchedUserId = Integer.parseInt((String)searchMemberServletRequestAttribute);
}

if(searchedUserId != -1) {
	%>
	<table> 
	<%
	 	List<String> userContainer = UsersWorker.GetUserAndAccount(searchedUserId);
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
	 		
		 	%>
			 	<tr><td>id</td><td><input style="width: 150px" type="text" name="userId" value="<% out.print(userId); %>"></td></tr>
			 	<tr><td>name</td><td><input style="width: 150px" type="text" name="userName" value="<% out.print(userName); %>"></td></tr>
			 	<tr><td>surname</td><td><input style="width: 150px" type="text" name="userSurname" value="<% out.print(userSurname); %>"></td></tr>
			 	<tr><td>country</td><td><input style="width: 150px" type="text" name="userCountry" value="<% out.print(userCountry); %>"></td></tr>
			 	<tr><td>city</td><td><input style="width: 150px" type="text" name="userCity" value="<% out.print(userCity); %>"></td></tr>
			 	<tr><td>english level</td><td><input style="width: 150px" type="text" name="userEnglishLevel" value="<% out.print(userEnglishLevel); %>"></td></tr>
			 	<tr><td>skype</td><td><input style="width: 150px" type="text" name="userSkype" value="<% out.print(userSkype); %>"></td></tr>
			 	<tr><td>sex</td><td><input style="width: 150px" type="text" name="userSex" value="<% out.print(userSex); %>"></td></tr>
			<%
	 	}
	 %>
	</table>
	<%
} else {
	
	final int numberOfColumn = 7;	//8 - id column
	final int lastIndexOfColumn = 6;//to add show button on last position
%>

		<fieldset>
			<table>
				<tr>
					<th>user_id</th>
					<th>user_name</th>
					<th>user_surname</th>
					<th>user_coutry</th>
					<th>user_city</th>
					<th>user_english_level</th>
					<th>user_skype</th>
					<th>user_sex</th>
					<th>Show</th>
				</tr> 
				<%
				List<String> usersContainer = UsersWorker.GetAllUsers();
				
				Iterator<String> it = usersContainer.iterator();
				
				while (it.hasNext()) {
				
					%>
					<form action="ProfileServlet" method="post">
					<tr>
					<%
					
					for (int i = 0; i < numberOfColumn; ++i) {
					
						if(i==0) {
						
							int user_id = Integer.parseInt(it.next());
GlobalFunctions.DEBUG("" + user_id);
							%>
								<td>
									<input style="width: 30px" type="text" name="member_user_id" value="<% out.print(user_id); %>" readonly>
								</td>								
							<%
						}
						
						out.print("<td>");
						out.print(it.next());
						out.print("</td>");
						
						if(i==lastIndexOfColumn) {
						
							%>
								<td>
									<input type="submit" value="Show">
								</td>
							<%
						}
					}
					%>
					</tr></form>
					<%
				}
			}
			%>
			</table>
		</fieldset>

<b>Search user:</b>
<form action="SearchMemberServlet" method="post">
	<fieldset>
		E-mail: <input type="text" name="userEmail"><br>
		<input type="submit" value="Search">
	</fieldset>
</form>