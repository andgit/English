<% if(request.getAttribute("action") != null) {
	
		String action = (String) request.getAttribute("action");
		
		if(action.equals("success")) { %>
		
			<br>Welcome in "Let`s speak English" society. Perfect registration :-).
	 <%	} else { %>
	 		<br>Fail : <%=action %>
	 <%	} 
	} else { %>
	
		<b>Join Us:</b>
		<form action="RegistrationServlet" method="post">
			<fieldset>
				<table style="text-align: right;">
					<tr><td>Email: </td><td><input type="text" required="required" style="width:220px" name="email" value=""></td></tr>		
					<tr><td>Password: </td><td><input type="password" required="required" style="width:220px" name="password" value=""/></td></tr>
					<tr><td><input type="radio" name="sex" value="male">Male</td></tr>
					<tr><td><input type="radio" name="sex" value="female">Female</td></tr>
				</table>
				<input type="submit" value="Register"/>
			</fieldset>
		</form>

 <% } %>
