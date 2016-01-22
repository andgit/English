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
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String parameterUserId = request.getParameter("userId");
		String parameterUserEmail = request.getParameter("userEmail");
		String parameterUserPassword = request.getParameter("userPassword");
		
		/*GlobalFunctions.DEBUG("parameterUserId=" + parameterUserId);
		GlobalFunctions.DEBUG("parameterUserEmail=" + parameterUserEmail);
		GlobalFunctions.DEBUG("parameterUserPassword=" + parameterUserPassword);*/
		
		if(parameterUserId != null && parameterUserEmail != null && parameterUserPassword != null) {
			
			UsersWorker.UpdateUser(Integer.parseInt(parameterUserId), parameterUserEmail, parameterUserPassword);
    		request.setAttribute("user_id", parameterUserId);
    		request.setAttribute("user_email", parameterUserEmail);
    		request.setAttribute("user_password", parameterUserPassword);
    		getServletContext().getRequestDispatcher("/Index.jsp?subpage=6").forward(request, response);
		}
	}

}
