package com.english;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.english.Database.UsersWorker;

/**
 * Servlet implementation class EditProfileServlet
 */
@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int parameterUserId = Integer.parseInt(request.getParameter("userId"));
		String parameterUserName = request.getParameter("userName");
		String parameterUserSurname = request.getParameter("userSurname");
		String parameterUserCountry = request.getParameter("userCountry");
		String parameterUserCity = request.getParameter("userCity");
		int parameterUserEnglishLevel = Integer.parseInt(request.getParameter("userEnglishLevel"));
		String parameterUserSkype = request.getParameter("userSkype");
		boolean parameterUserSex = Boolean.parseBoolean(request.getParameter("userSex"));
		
		//int parameterAccountId = Integer.parseInt(request.getParameter("accountId"));
		String parameterAccountEmail = request.getParameter("accountEmail");
		String parameterAccountPassword = request.getParameter("accountPassword");
		//String parameterAccountRegistrationDate = request.getParameter("accountRegistrationDate");
				
		//if(parameterUserId != null && parameterUserEmail != null && parameterUserPassword != null) {
			
			UsersWorker.UpdateUser(parameterUserId, parameterUserName, parameterUserSurname, parameterUserCountry, parameterUserCity, parameterUserEnglishLevel, parameterUserSkype, parameterUserSex, parameterAccountEmail, parameterAccountPassword);
    		getServletContext().getRequestDispatcher("/Index.jsp?subpage=7").forward(request, response);
		//}
	}

}
